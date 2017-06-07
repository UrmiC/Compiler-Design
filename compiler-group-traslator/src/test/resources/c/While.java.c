#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int i;
	i = 0;
	while (i < 10) {
		i++;
	}
	assertTrue(i == 10);
	return 0;
}