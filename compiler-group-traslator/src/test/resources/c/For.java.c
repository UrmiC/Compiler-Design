#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
void emptyFor() {
	for ( ;  ; ) {
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
void initOnlyFor() {
	int i;
	for (i = 0 ;  ; ) {
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
void condOnlyFor() {
	int i;
	i = 0;
	for ( ; i < 10 ; ) {
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
void incOrDecOnlyFor() {
	int i;
	i = 0;
	for ( ;  ; i++) {
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
void missingIncOrDecFor() {
	int i;
	for (i = 0 ; i < 10 ; ) {
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
void missingCondFor() {
	int i;
	for (i = 0 ;  ; i++) {
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
void missingInitFor() {
	int i;
	i = 0;
	for ( ; i < 10 ; i++) {
	}
	assertTrue(i == 10);
	//set all vars to zero and have the TEMP variable for the return expression
}
void forFor() {
	int i;
	for (i = 0 ; i < 10 ; i++) {
	}
	assertTrue(i == 10);
	//set all vars to zero and have the TEMP variable for the return expression
}
void multipleInitOnlyFor() {
	int i;
	int j;
	int k;
	for (i = 0, j = 0, k = 0 ;  ; ) {
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
void multipleIncOrDecOnlyFor() {
	int i;
	int j;
	int k;
	i = 0;
	j = 0;
	k = 0;
	for ( ;  ; i++, j++, k++) {
	}
	//set all vars to zero and have the TEMP variable for the return expression
}
void multipleFor() {
	int i;
	int j;
	int k;
	for (i = 0, j = 0, k = 0 ; i < 10 ; i++, j++, k++) {
	}
	assertTrue(i == 10);
	assertTrue(j == 10);
	assertTrue(k == 10);
	//set all vars to zero and have the TEMP variable for the return expression
}
int main(char* args) {
	vm = newVM();
	missingInitFor();
	forFor();
	multipleFor();
	return 0;
}