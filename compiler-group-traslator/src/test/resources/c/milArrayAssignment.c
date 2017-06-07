#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo3 {
	int i;
}Foo3;

int main(char* args) {
	vm = newVM();
	struct Foo3** a;
	struct Foo3** b;

	a = newArray(vm,"struct Foo3*",&a ,1000000 * sizeof (struct Foo3*), NULL) ;

	b = newArray(vm,"struct Foo3*",&b ,1000000 * sizeof (struct Foo3*), NULL) ;
	return 0;
}