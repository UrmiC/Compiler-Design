#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* str) {
	vm = newVM();
	int i;
	int* a;
	int* b;
	i = 0;
	a = newArray(vm,"int",&a , 4 * sizeof (int), makeList(vm,&a, 4,1, 2, 3 ));
	b = newArray(vm,"int",&b ,3 * sizeof (int), NULL);
	b[i] = (checkArray(a,(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i]), "a", 4) && checkNotNull(&a[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])])? a[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])] : a[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])]);
	b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i]) * i] = (checkArray(a,(checkArray(b,(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i]), "b", 3) && checkNotNull(&b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])])? b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])] : b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])]), "a", 4) && checkNotNull(&a[(checkArray(b,(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i]), "b", 3) && checkNotNull(&b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])])? b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])] : b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])])])? a[(checkArray(b,(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i]), "b", 3) && checkNotNull(&b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])])? b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])] : b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])])] : a[(checkArray(b,(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i]), "b", 3) && checkNotNull(&b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])])? b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])] : b[(checkArray(a,i, "a", 4) && checkNotNull(&a[i])? a[i] : a[i])])]);
	return 0;
}