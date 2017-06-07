#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo {
}Foo;

int main(char* args) {
	vm = newVM();
	struct Foo* f;
	struct Foo** g;
	return 0;
}