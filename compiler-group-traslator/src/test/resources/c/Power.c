#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
int power(int m, int n) {
	int result;
	int i;
	assertTrue(n >= 1);

	result = 1 ;

	i = 0 ;
	while (i < n) {
		result = result * m ;

		i = i + 1 ;
	}

	return result;

	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	power(3, 2);
	return 0;
}