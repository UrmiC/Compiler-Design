#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct FieldAccessFoo {
	int i;
}FieldAccessFoo;
typedef struct FieldAccessBar {
	int i;
	struct FieldAccessFoo* f;
}FieldAccessBar;

int main(char* args) {
	vm = newVM();
	int i;
	struct FieldAccessFoo* f;
	struct FieldAccessBar* b;

	f = (FieldAccessFoo*)newObject(vm, &f, sizeof (FieldAccessFoo)) ;

	f->i = 0 ;

	i = (checkNotNull(&(f->i))? f->i : f->i) ;
	assertTrue((checkNotNull(&(f->i))? f->i : f->i) == 0);
	assertTrue(i == 0);

	b = (FieldAccessBar*)newObject(vm, &b, sizeof (FieldAccessBar)) ;

	b->i = 1 ;

	i = (checkNotNull(&(b->i))? b->i : b->i) ;
	assertTrue((checkNotNull(&(b->i))? b->i : b->i) == 1);
	assertTrue(i == 1);

	b->f = f ;

	b->f->i = 2 ;

	i = (checkNotNull(&(b->f->i))? b->f->i : b->f->i) ;
	assertTrue((checkNotNull(&(b->f->i))? b->f->i : b->f->i) == 2);
	assertTrue(i == 2);

	b->f->i = 3 ;

	f->i = (checkNotNull(&(b->f->i))? b->f->i : b->f->i) ;
	assertTrue((checkNotNull(&(b->f->i))? b->f->i : b->f->i) == 3);
	assertTrue((checkNotNull(&(f->i))? f->i : f->i) == 3);

	b->f->i = 4 ;

	f = (checkNotNull(&(b->f))? b->f : b->f) ;
	assertTrue((checkNotNull(&(f->i))? f->i : f->i) == 4);

	f->i = 5 ;

	b->f = f ;
	assertTrue((checkNotNull(&(b->f->i))? b->f->i : b->f->i) == 5);
	b->f->i++;
	assertTrue((checkNotNull(&(b->f->i))? b->f->i : b->f->i) == 6);
	return 0;
}