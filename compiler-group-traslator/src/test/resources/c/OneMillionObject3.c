#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct OneMillionObject3B {
}OneMillionObject3B;
typedef struct OneMillionObject3A {
	struct OneMillionObject3B* b;
}OneMillionObject3A;

int main(char* args) {
	vm = newVM();
	int i;
	struct OneMillionObject3A** a;
	struct OneMillionObject3B* b;
	a = newArray(vm,"struct OneMillionObject3A*",&a ,1000000 * sizeof (struct OneMillionObject3A*), NULL);
	for (i = 0 ; i < 1000000 ; i++) {
		a[i] = (OneMillionObject3A*)newObject(vm, &a, sizeof (OneMillionObject3A));
		b = (OneMillionObject3B*)newObject(vm, &b, sizeof (OneMillionObject3B));
		(checkArray(a,i, "a", 1000000) && checkNotNull(&a[i])? a[i] : a[i])->b = b;
	}
	return 0;
}