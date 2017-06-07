#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Bar1 {
	int* f;
}Bar1;

int main(char* args) {
	vm = newVM();
	struct Bar1* b;
	int z;
	int* y;

	y = newArray(vm,"int",&y , 5 * sizeof (int), makeList(vm,&y, 5,7, 8, 9, 10 )) ;

	b = (Bar1*)newObject(vm, &b, sizeof (Bar1)) ;

	b->f = y ;
	return 0;
}