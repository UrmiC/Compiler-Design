#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo13 {
	int* a;
}Foo13;

int main(char* args) {
	vm = newVM();
	struct Foo13* b;

	b = (Foo13*)newObject(vm, &b, sizeof (Foo13)) ;

	b->a = newArray(vm,"int",&b ,10 * sizeof (int), NULL) ;
	 checkArray((checkNotNull(&(b->a))? b->a : b->a),0, "(checkNotNull(&(b->a))? b->a : b->a)", -1);
	checkNotNull(&(checkNotNull(&(b->a))? b->a : b->a)[0]);
	(checkNotNull(&(b->a))? b->a : b->a)[0] = 0 ;
	assertTrue((checkArray((checkNotNull(&(b->a))? b->a : b->a),0, "(checkNotNull(&(b->a))? b->a : b->a)", -1) && checkNotNull(&(checkNotNull(&(b->a))? b->a : b->a)[0])? (checkNotNull(&(b->a))? b->a : b->a)[0] : (checkNotNull(&(b->a))? b->a : b->a)[0]) == 0);
	return 0;
}