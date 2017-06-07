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
	int x;
	for (x = 0, i = 0, j = 0 ; i < 5 ; i++, j++) {
		x = x + i + j;
	}
	return 0;
}