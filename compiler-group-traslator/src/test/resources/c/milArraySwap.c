#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Foo6 {
	int z;
}Foo6;

int main(char* args) {
	vm = newVM();
	struct Foo6** a;
	struct Foo6* c;
	int i;
	int j;

	a = newArray(vm,"struct Foo6*",&a ,1000000 * sizeof (struct Foo6*), NULL) ;
	for (
	i = 0 , 
	j = 99999  ; i < 100 ; i++, j--) {
		c = (checkArray(a,i, "a", 1000000) && checkNotNull(&a[i])? a[i] : a[i]) ;
		 checkArray(a,i, "a", 1000000);
		checkNotNull(&a[i]);
		a[i] = (checkArray(a,j, "a", 1000000) && checkNotNull(&a[j])? a[j] : a[j]) ;
		 checkArray(a,j, "a", 1000000);
		checkNotNull(&a[j]);
		a[j] = (checkArray(a,i, "a", 1000000) && checkNotNull(&a[i])? a[i] : a[i]) ;
	}
	return 0;
}