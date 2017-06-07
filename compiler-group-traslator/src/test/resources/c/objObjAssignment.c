#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo12 {
	int z;
}Foo12;

int main(char* args) {
	vm = newVM();
	struct Foo12* a;
	struct Foo12* b;

	a = (Foo12*)newObject(vm, &a, sizeof (Foo12)) ;

	a->z = 5 ;

	b = a ;
	assertTrue((checkNotNull(&(b->z))? b->z : b->z) == 5);
	return 0;
}