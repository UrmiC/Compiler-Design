#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int a;

	a = 0 ;
	if (true) {
		a = 1 ;

		a = 2 ;
	}else{
	}
	assertTrue(a == 2);
	return 0;
}