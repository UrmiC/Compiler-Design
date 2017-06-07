#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo10 {
	int* a;
}Foo10;
int get(int* arr, int index) {
	return (checkArray(arr,index, "arr", -1) && checkNotNull(&arr[index])? arr[index] : arr[index]);

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	struct Foo10* b;
	b = (Foo10*)newObject(vm, &b, sizeof (Foo10));
	b->a = newArray(vm,"int",&b , 7 * sizeof (int), makeList(vm,&b, 7,5, 4, 3, 2, 1, 0 ));
	assertTrue(get((checkNotNull(&(b->a))? b->a : b->a), 5) == 0);
	return 0;
}