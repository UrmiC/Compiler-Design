#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int* returnIntArray() {
	void* RU = NULL;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	int* i;

	i = returnIntArray() ;
	return 0;
}