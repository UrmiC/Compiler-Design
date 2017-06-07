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
	if (false) {
		a = 1 ;
	}else{
	}
	assertTrue(a == 0);
	return 0;
}