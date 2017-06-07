#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int* a;
	int* b;
	int* c;

	a = newArray(vm,"int",&a , 7 * sizeof (int), makeList(vm,&a, 7,5, 4, 3, 2, 1, 0 )) ;

	b = newArray(vm,"int",&b , 4 * sizeof (int), makeList(vm,&b, 4,1, 2, 3 )) ;

	c = a ;

	c = b ;
	assertTrue((checkArray(c,0, "c", -1) && checkNotNull(&c[0])? c[0] : c[0]) == 1);
	return 0;
}