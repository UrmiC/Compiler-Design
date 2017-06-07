#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int x;
	int y;
	bool b;

	x = 1 ;

	y = 2 ;

	b = x + y > 2 ;
	while (b) {
		b = false ;
	}

	b = x > y ;

	return ;
	return 0;
}