#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo4 {
}Foo4;

int main(char* args) {
	vm = newVM();
	struct Foo4** a;
	struct Foo4* b;
	int i;

	b = NULL ;

	a = newArray(vm,"struct Foo4*",&a ,1000000 * sizeof (struct Foo4*), NULL) ;
	for (
	i = 0  ; i < 1000000 ; i++) {
		 checkArray(a,i, "a", 1000000);
		checkNotNull(&a[i]);
		a[i] = b ;
	}
	return 0;
}