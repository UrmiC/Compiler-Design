#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo5 {
	int z;
}Foo5;

int main(char* args) {
	vm = newVM();
	struct Foo5** a;
	struct Foo5** b;
	int i;

	a = newArray(vm,"struct Foo5*",&a ,1000000 * sizeof (struct Foo5*), NULL) ;

	b = newArray(vm,"struct Foo5*",&b ,1000000 * sizeof (struct Foo5*), NULL) ;

	a = b ;
	return 0;
}