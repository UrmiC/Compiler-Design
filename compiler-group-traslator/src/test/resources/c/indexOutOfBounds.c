#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int* a;

	a = newArray(vm,"int",&a ,10 * sizeof (int), NULL) ;
	 checkArray(a,0, "a", 10);
	checkNotNull(&a[0]);
	a[0] = (checkArray(a,10, "a", 10) && checkNotNull(&a[10])? a[10] : a[10]) ;
	return 0;
}