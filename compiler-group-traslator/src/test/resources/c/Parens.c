#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int i;
	int j;
	int* a;

	i = (2) ;

	i = 2 ;

	j = (i) ;

	i = (i + 0) ;
	assertTrue(i == 2);

	i = (i * (i + 0)) ;
	assertTrue(i == 4);

	i = (((i) / (checkDivision((2))? (2) : (2))) - (0)) ;
	assertTrue(i == 2);

	a = newArray(vm,"int",&a ,4 * sizeof (int), NULL) ;
	return 0;
}