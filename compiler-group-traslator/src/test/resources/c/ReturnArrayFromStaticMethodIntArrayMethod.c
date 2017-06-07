#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int* returnArray(int* A2) {
	A2 = newArray(vm,"int",&A2 ,6 * sizeof (int), NULL) ;
	 checkArray(A2,0, "A2", 6);
	checkNotNull(&A2[0]);
	A2[0] = 1 ;
	void* RU = A2;
	return RU;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	int* A1;

	A1 = NULL ;

	A1 = returnArray(A1) ;
	return 0;
}