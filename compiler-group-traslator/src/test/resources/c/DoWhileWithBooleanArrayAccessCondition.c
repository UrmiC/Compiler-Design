#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	bool* B;
	int i;

	B = newArray(vm,"bool",&B , 3 * sizeof (bool), makeList(vm,&B, 3,true, false )) ;

	i = 1 ;
	do {
	}while((checkArray(B,i, "B", 3) && checkNotNull(&B[i])? B[i] : B[i]));
	return 0;
}