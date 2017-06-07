#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
void multipleIncOrDecOnly() {
	int i;
	int j;
	int k;

	i = 0 ;

	j = 0 ;

	k = 0 ;
	for ( ;  ; i++, j++, k++) {
	}

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	return 0;
}