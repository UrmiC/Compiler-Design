#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int* a;
	int b;
	int i;

	a = newArray(vm,"int",&a ,5 * sizeof (int), NULL) ;

	b = 0 ;
	for (
	i = 0  ; i < 5 ; i++) {
		 checkArray(a,i, "a", 5);
		checkNotNull(&a[i]);
		a[i] = i ;

		b = b + (checkArray(a,i, "a", 5) && checkNotNull(&a[i])? a[i] : a[i]) ;
	}
	assertTrue(b == 10);
	for (
	i = 0  ; i < 5 ; i++) {
		a[i]++;
	}

	i = 0 ;
	for (
	i = 0  ; i < 5 ; i++) {
		assertTrue((checkArray(a,i, "a", 5) && checkNotNull(&a[i])? a[i] : a[i]) == (i + 1));
	}
	return 0;
}