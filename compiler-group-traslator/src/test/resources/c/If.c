#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;

int main(char* args) {
	vm = newVM();
	int a;

	a = 0 ;
	if (true) {
	}else{
	}
	assertTrue(a == 0);

	a = 0 ;
	if (true) {
		a = 1 ;
	}else{
	}
	assertTrue(a == 1);

	a = 0 ;
	if (true) {
		a = 1 ;

		a = 2 ;
	}else{
	}
	assertTrue(a == 2);

	a = 0 ;
	if (true) {
	}else{
	}
	assertTrue(a == 0);

	a = 0 ;
	if (true) {
		a = 1 ;
	}else{
	}
	assertTrue(a == 1);

	a = 0 ;
	if (true) {
		a = 1 ;
	}else{
		a = 2 ;
	}
	assertTrue(a == 1);

	a = 0 ;
	if (true) {
		a = 1 ;
	}else{
		a = 2 ;

		a = 3 ;
	}
	assertTrue(a == 1);

	a = 0 ;
	if (false) {
	}else{
	}
	assertTrue(a == 0);

	a = 0 ;
	if (false) {
		a = 1 ;
	}else{
	}
	assertTrue(a == 0);

	a = 0 ;
	if (false) {
		a = 1 ;
	}else{
	}
	assertTrue(a == 0);

	a = 0 ;
	if (false) {
		a = 1 ;
	}else{
		a = 2 ;
	}
	assertTrue(a == 2);

	a = 0 ;
	if (false) {
		a = 1 ;
	}else{
		a = 2 ;

		a = 3 ;
	}
	assertTrue(a == 3);
	return 0;
}