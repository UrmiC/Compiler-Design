#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int i;
	i = -1;
	i = i > 0 ? 2 : 3;
	assertTrue(i == 3);
	return 0;
}