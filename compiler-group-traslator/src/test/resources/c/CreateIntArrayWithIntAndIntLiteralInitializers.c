#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int* I;
	int i;

	i = 0 ;

	I = newArray(vm,"int",&I , 3 * sizeof (int), makeList(vm,&I, 3,i, 1 )) ;
	return 0;
}