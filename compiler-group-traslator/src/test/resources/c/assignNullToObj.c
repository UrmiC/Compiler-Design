#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo9 {
}Foo9;

int main(char* args) {
	vm = newVM();
	struct Foo9* b;

	b = NULL ;
	assertTrue(b == NULL);
	return 0;
}