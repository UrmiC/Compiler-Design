#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
void missingIncOrDec() {
	int i;
	for (i = 0 ; i < 10 ; ) {
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	return 0;
}