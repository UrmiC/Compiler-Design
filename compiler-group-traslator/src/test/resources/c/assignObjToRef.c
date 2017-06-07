#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo8 {
}Foo8;

int main(char* args) {
	vm = newVM();
	struct Foo8** a;
	struct Foo8* b;

	b = (Foo8*)newObject(vm, &b, sizeof (Foo8)) ;

	a = newArray(vm,"struct Foo8*",&a , 2 * sizeof (struct Foo8*), makeList(vm,&a, 2,b )) ;
	return 0;
}