#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int* a;
	a = newArray(vm,"int",&a ,10 * sizeof (int), NULL);
	a[10] = 2;
	return 0;
}