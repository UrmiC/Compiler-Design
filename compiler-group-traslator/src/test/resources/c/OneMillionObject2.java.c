#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int i;
	int* bigArray;
	bigArray = newArray(vm,"int",&bigArray ,1000000 * sizeof (int), NULL);
	for (i = 0 ; i < 1000000 ; i++) {
		bigArray[i] = i;
	}
	return 0;
}