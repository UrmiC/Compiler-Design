#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* str) {
	vm = newVM();
	int* i_array;
	i_array = newArray(vm,"int",&i_array , 4 * sizeof (int), makeList(vm,&i_array, 4,1, 2, 3 ));
	i_array[0] = 1;
	return 0;
}