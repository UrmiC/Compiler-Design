#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
void missingInit() {
	int i;

	i = 0 ;
	for ( ; i < 10 ; i++) {
	}
	assertTrue(i == 10);

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	missingInit();
	return 0;
}