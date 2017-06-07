#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct A {
	int x;
}A;
typedef struct B {
	int x;
}B;

int main(char* args) {
	vm = newVM();
	struct A* a;
	struct B* b;
	int z;

	a = (A*)newObject(vm, &a, sizeof (A)) ;

	b = (B*)newObject(vm, &b, sizeof (B)) ;

	z = (checkNotNull(&(a->x))? a->x : a->x) + (checkNotNull(&(b->x))? b->x : b->x) ;
	return 0;
}