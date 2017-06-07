#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int* I;

	I = newArray(vm,"int",&I ,1 * sizeof (int), NULL) ;
	 checkArray(I,0, "I", 1);
	checkNotNull(&I[0]);
	I[0] = 1 ;
	I[0]++;
	return 0;
}