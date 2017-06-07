#include<stdio.h>
#include<stdbool.h>
#include<stdarg.h>
#include<stdlib.h>

//#include "ExceptionHandlingFunctionsTest.c"
//#define NULL ((void*) 0)
#define PRINTFLAG 0
typedef enum {
  INT = 1,
  BOOL,
  OBJP,
  INT_ARRAY,
  BOOL_ARRAY,
  OBJP_ARRAY
} ObjectType;


typedef struct sObject {
  struct sObject* next;
  unsigned char marked;
  ObjectType type;
  int size;
  union {
    /* OBJ_INT */
    int integer;
	bool boolean;
	struct sObject* object;
	
	int* intArray;
	bool* boolArray;
	struct sObject** objectArray;
	//sObject object
  };
} Object;


typedef struct st1{
	int i;
	struct st1* next;
}st1;

typedef struct st2{
	struct st1* s;
	bool b;
}st2;
#define MAX_STACK 1024*1024*512


typedef struct RefObjects{
	void* object;
	void* varRef;
	int size;
	struct RefObjects* next;
}RefObject;

typedef struct {
	void* stack;
	void* stackEnd;
	void* headObject;
	void* headRef;
	void* headFree;
	//RefObject** linkedList;
	int linkedListSize;
}VM;

bool gc_checkNotNull(VM* vm, void* arg){
	//printf("nn1");
	//printf("nn2.1");
	int* temp = arg;
	if(*temp && *temp > vm->headObject){
		//printf("nn3 %d\n", *temp);
		return 1;
	}
	//printf("nn4\n");
	return 0;
}



void* allocateObject(VM* vm, int size){
	int amount = ((size/ sizeof (Object)) + 1)* sizeof (Object);//what if size = OBject size exact> we get +1
	//if(freePointer + amount > stack + MAX_STACK){
		//call gc();
	//}
	return vm->headObject - amount;
}

VM* newVM(){
	VM* vm = malloc(MAX_STACK);
	vm->stack = vm + (sizeof (VM));
	vm->stackEnd = vm->stack + MAX_STACK;
	vm->headObject = vm->stackEnd;
	vm->headRef = vm->stack;
	vm->headFree = NULL;
	//vm->linkedList = &vm;
	vm->linkedListSize = 0;
	//Object* null = allocateObject(vm, )
}

void allocateRef(VM* vm, void* var, int size, Object* obj){
	//printf("ref start0\n");
	RefObject* ro = vm->headRef;
	//printf("ref start1\t%p\t %d\n", vm->headRef, (sizeof (RefObject)));
	ro->object = obj;
	//printf("ref before\n");
	ro->varRef = var;
	ro->size = size;
	//printf("ref after\n");
	vm->headRef = vm->headRef + (sizeof (RefObject));
	ro->next = vm->headRef;
	vm->linkedListSize++;
}

void freeObject(VM* vm, Object* object){ //not sure
	if (object == vm->headObject){
		//printf("is head true\n");
		vm->headObject = object->next;
		return;
	}
	if (!gc_checkNotNull(vm, &vm->headFree)){
		object->next = NULL;
	}else{
		object->next = vm->headFree;
	}
	vm->headFree = object;
}
/*
void freeRef(VM* vm, RefObject* object){ //not sure
	printf("%d\t%d\t%d\n",object,object + ((int)sizeof (RefObject)), vm->headRef);
	if ((int)object + (int)(sizeof (RefObject)) == vm->headRef){
		vm->headRef = object;
		printf("true");
		return;
	}
}
*/
bool getFromFreeList(VM* vm,int size,Object* object){
	if(vm->headFree ==NULL || !gc_checkNotNull(vm, vm->headFree))
		return false;
	Object* head = vm->headFree;
	Object* previousObj = NULL;
	while(gc_checkNotNull(vm, head)){
		if (head->size >= size){
			object = head;
			if(gc_checkNotNull(vm, previousObj)){
				previousObj->next = head->next;
			}else{
				vm->headFree = head->next;
			}
			return true;
		}
		previousObj = head;
		head = head->next;
	}
	return false;
}

Object* new(VM* vm,void* var, int size, ObjectType type){
	//check if max object exceeded then call gc();
	Object* object = allocateObject(vm, size);
	if ((int*)object < (int*)vm->headRef){
		if (!getFromFreeList(vm, size, object)){
			printf("Stack Overflow");
			exit(0);
		}
	}
	//printf("headObj: %p\n", vm->headObject);
	//printf("new obj: %p\t&obj: %p\tnext: %d\n", object, &object, vm->headObject);
	object->type = type;
	object->marked = 0;
	object->size = size;
	
	object->next = (vm->headObject == vm->stackEnd)? vm->stackEnd : vm->headObject;
	vm->headObject = object;
	//printf("new obj: %p\t&obj: %p\tnext: %d\n", object, &object, object->next);
	//printf("new1\n");
	allocateRef(vm, var, size, object);
	return object;
}
/*
void* initArray(Object* obj, int length, ObjectType type, Object* list){
	int i;
	if (type == INT_ARRAY){
		for(i = 0; i < length; i++){
			obj->intArray = list->intArray;
		}
	}else if(type == BOOL_ARRAY){
		for(i = 0; i < length; i++){
			obj->boolArray = list->boolArray;
		}
	}
	return obj;
}
*/
void* newObjectArray(VM* vm, void* var, int size, Object** list){
	Object* obj = new(vm, var, size, OBJP_ARRAY);
	if (list == NULL){
		return obj;
	}
	size = sizeof (list);// sizeof (list[0]);
	obj->objectArray = list;
	return new(vm, var, size, OBJP_ARRAY);;
}

void* newObject(VM* vm,void* var, int size){
	Object* obj = new(vm, var, size, OBJP);
	return &obj->objectArray;
}



Object* makeList(VM* vm, void* var, int argc, ...){
	Object* objects = new(vm,var, sizeof (Object), OBJP_ARRAY);
	//objects->objectArray = newObjectArray(vm, var, argc, NULL);
	va_list arguments;
	va_start(arguments, argc);
	Object* temp = va_arg(arguments, Object*);
	objects->objectArray = new(vm,var, argc * sizeof (temp), OBJP_ARRAY);
	objects->objectArray[0] = temp; 
	int i;
	for (i = 1; i < argc-1; i++ )        
    {
        objects->objectArray[i] = va_arg(arguments, Object*); 
		//printf("assigning %d:%d\n", i, objects->objectArray[i]);
    }
	va_end ( arguments );
	//printf("Here1.9\n %p", objects);
	return objects;
}

void* newArray(VM* vm, char* type, void* var, int size, Object* list){
	//printf("Here2.0\n");
	if (list == NULL){
		ObjectType ot = OBJP_ARRAY;
		if (type == "int"){ot = INT_ARRAY;}else if (type == "bool"){ot = BOOL_ARRAY;}
		Object* obj = new(vm,var, size, ot);
		return obj;
	}
	//	printf("Here2.1\n");
	//length = sizeof (list)/ sizeof (list[0]);
	//printf("Here2.2\n");
	if (type == "int"){
		list->type = INT_ARRAY;
		return list->intArray;
	}else if (type == "bool"){
		list->type = BOOL_ARRAY;
		return list->objectArray;
	}
	return list->objectArray;
}


void mark(Object* obj){
	if (obj->marked) return;
	obj->marked = 1;
	if (obj->type == OBJP_ARRAY /*|| obj->type == OBJP*/){
		int i = 0;
		while(obj->objectArray[i] != NULL){
			mark(obj->objectArray[i]);
		}
	}
}
void markAll(VM* vm){
	if (PRINTFLAG) printf("****Marking STARTED*****\n");
	RefObject* head = vm->stack;
	while(head != vm->headRef){
		//printf("marking: %d\n", head->varRef );
		if (head->varRef == NULL){
			if (PRINTFLAG) printf("skipped: \n");
		}else if (gc_checkNotNull(vm, head->varRef)){ 
			if (PRINTFLAG) printf("marked: %d\n", head->varRef );
			mark(head->object);
		}else{
			//freeRef(vm, head);
			if (PRINTFLAG) printf("skipped: %d\n", head->varRef );
			}
		head = head->next;
	}
}

void sweep(VM* vm){
	if (PRINTFLAG) printf("****SWEEPING STARTED*****\n");
	Object* previousObj = NULL;
	Object* obj = vm->headObject;
	while(obj){
		//printf("start sweep %d\n", obj);
		if (!obj->marked){
			Object* garbage = obj;
			obj = garbage->next;
			if (gc_checkNotNull(vm, &previousObj))
				previousObj->next = obj;
			//printf("obj %d\t next %d\n", garbage, obj);
			freeObject(vm,garbage);
			if (PRINTFLAG) printf("freed %p\n", garbage);
			//printf("obj %d\t next %d\n", garbage, obj);
			if ((int*)obj < (int*)garbage)
				return;
			//printf("next %d\t end %d\n", obj, vm->stackEnd);
		}else{
			if (PRINTFLAG) printf("skipped %p\n", obj);
			//printf("obj %d\t obj->marked %d\n", obj, obj->marked);
			obj->marked = 0;
			if ((int*)obj->next < (int*)obj)
				return;
			previousObj = obj;
			obj = obj->next;
			//printf("next %d\t end %d\n", obj, vm->stackEnd);
		}
	}
}

void gc(VM* vm){
	markAll(vm);
	sweep(vm);
}

st1* testfunc(VM* vm){
	st1* zz = newObject(vm,&zz, sizeof (st1));
	st1* REZA = zz; //add to ST
	zz = NULL;		//add to ST
	gc(vm);			//add to ST
	return REZA;
}
/*
int main(){
	//stackInit(stack);
	VM* vm = newVM();
	printf("%d\t%d\t%d\t%d\t%d\n",vm, vm->stack, vm->stackEnd, vm->headRef, vm->headObject);
	//printf("Hello World");
	printf("Here1\n");
	int a[2] = {0,1};
	printf("Here2\n");
	//bool** b = newArray(vm,"bool", 5, makeList(vm, 5,1,0,1,0,1));
	st1* s0 = newObject(vm,&s0, sizeof (st1));
	st1* s1 = newObject(vm,&s1, sizeof (st1));
	st1* s2 = newObject(vm,&s2, sizeof (st1));
	st1* s3 = newObject(vm,&s3, sizeof (st1));
	st1* s4 = newObject(vm,&s4, sizeof (st1));
	s0->i = 2;
	printf("s0 = %p\t%p\n", s0, &s0->i);
	Object* oo = s0;
	printf("s0 = %p\n", oo );
	printf("Here3\n");
	printf("Here4\n");
	//printf("s0->i = %d\n", s0->i);
	//setNull(s0);
	s0 = NULL;
	s4 = NULL;
	printf("s0: %d\n", gc_checkNotNull(vm, &s0));
	printf("s1: %d\n", gc_checkNotNull(vm, &s1));
	gc(vm);
	st1* s5 = newObject(vm,&s5, sizeof (st1));
	s5 = NULL;
	gc(vm);
	gc(vm);
	
	printf("funtest: %d\n", *testfunc(vm));
	gc(vm);
	printf("SS: %d\n", 7 + sizeof(RefObject));
	printf("headObject: %d\n", vm->headObject);
	st2* z0 = newObject(vm,&z0, sizeof (st2));
	z0->s = newObject(vm,&z0, sizeof (st1)); //get whatever before ->
	gc(vm);
	z0 = NULL;
	gc(vm);
	//st2* z1 = newObject(vm,&z1, sizeof (st2));
	//gc(vm);
	//Object* z = NULL;
	//printf("z: %d\n", gc_checkNotNull(vm, &z));
	//printf("s0->i = %d\n", s0->i);
	
	/*
	int* b = (int*)newArray(vm,"int",  5, makeList(vm, 5,9,8,7,6,5));
	int* c = b;
	
	Object* o = setNull(b);
	//b = NULL;
	printf("add b: %p\n", b);
	printf("add c: %p\n", c);
	printf("add other: %p\n", &vm->headObject);
	printf("add other onr: %p\n", &b);
	//b = {9,8,7,6,5};
	//st1* s = newObjectArray(vm, 5, NULL);
	printf("Here3\n");
	//b[4] = 2;
	printf("Here4\n");
	printf("b[4] = %d\n", c[0]);
	
	//getchar();
	//int y = 0, x = 0, z = 10;
	//int x = 1/check(checkArray(a, y)?a[y]:a[y])?a[y]:a[y];
	//AAA *x = checkNull(y)?y:y;
	//printf("x = %d", getByteSize(x));
	//checkDivision(exp2)?exp2:exp2
	//gc_checkNotNull()
	//Object** stack = malloc(100 * sizeof Object)
	//a[10] = 10;
	//st1* s = newClassObject("s");
	//bool* b = makeList(5,1, 0, 1, 0, 1);
	//Object* b = newBoolArray(5, makeList(5, true, false, true, true, true));
	//int* b = newIntArray(5, makeList(5, 9,8,7,6,5));
	//bool* c = newObjectSet("bool", 5, makeList(2, true, false));
	
	//Object** obj;
	//Object* o = malloc(sizeof (Object));
	//if (obj == NULL){
		//printf("sth");
	//}
	//obj[0] = o;
	
	//printf("Here%d", b[3]);
	//bool** b = makeList(5, true, false, true, true, true);
	//s->arr = newObjectSet("int", 5, makeList(5,4,3,2,1,0));
	//s->arr[0] = 1;
	//s->arr[1] = 2;
	//printf("b[0] = %d\n", b[2]);
	//printf("o.integer = %d\n", newObjects(sizeof (st1), 1));
	//free(stack);
	return 0;
}
*/

