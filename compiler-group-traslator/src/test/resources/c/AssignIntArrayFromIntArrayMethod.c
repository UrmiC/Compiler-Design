#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int* returnIntArray() {
	int* a;

	a = newArray(vm,"int",&a , 4 * sizeof (int), makeList(vm,&a, 4,1, 2, 3 )) ;
	void* RU = a;
	a = NULL;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	int* i;

	i = returnIntArray() ;
	return 0;
}