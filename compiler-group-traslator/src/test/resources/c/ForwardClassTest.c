#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo2 {
	struct Bar* b;
}Foo2;
typedef struct Bar {
	int x;
}Bar;

int main(char* args) {
	vm = newVM();
	struct Foo2* a;

	a = (Foo2*)newObject(vm, &a, sizeof (Foo2)) ;

	a->b = (Bar*)newObject(vm, &a, sizeof (Bar)) ;

	a->b->x = 5 ;
	return 0;
}