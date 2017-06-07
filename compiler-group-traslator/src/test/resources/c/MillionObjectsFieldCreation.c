#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Millionobjectclass1 {
	int a;
}Millionobjectclass1;

int main(char* args) {
	vm = newVM();
	int i;
	struct Millionobjectclass1** objects;

	objects = newArray(vm,"struct Millionobjectclass1*",&objects ,1000000 * sizeof (struct Millionobjectclass1*), NULL) ;
	for (
	i = 0  ; i < 1000000 ; i++) {
		 checkArray(objects,i, "objects", 1000000);
		checkNotNull(&objects[i]);
		objects[i] = (Millionobjectclass1*)newObject(vm, &objects, sizeof (Millionobjectclass1)) ;

		(checkArray(objects,i, "objects", 1000000) && checkNotNull(&objects[i])? objects[i] : objects[i])->a = i ;
	}
	return 0;
}