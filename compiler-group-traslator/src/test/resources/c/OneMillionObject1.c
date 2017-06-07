#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct OneMillionObject1A {
}OneMillionObject1A;

int main(char* args) {
	vm = newVM();
	int i;
	struct OneMillionObject1A** mOneMillionObjectAnother;
	mOneMillionObjectAnother = newArray(vm,"struct OneMillionObject1A*",&mOneMillionObjectAnother ,1000000 * sizeof (struct OneMillionObject1A*), NULL);
	for (i = 0 ; i < 1000000 ; i++) {
		mOneMillionObjectAnother[i] = (OneMillionObject1A*)newObject(vm, &mOneMillionObjectAnother, sizeof (OneMillionObject1A));
	}
	return 0;
}