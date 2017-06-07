#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int* x;

	x = newArray(vm,"int",&x ,1 * sizeof (int), NULL) ;
	 checkArray(x,0, "x", 1);
	checkNotNull(&x[0]);
	x[0] = 1 ;
	while ((checkArray(x,0, "x", 1) && checkNotNull(&x[0])? x[0] : x[0]) == 1) {
		 checkArray(x,0, "x", 1);
		checkNotNull(&x[0]);
		x[0] = 2 ;
	}
	return 0;
}