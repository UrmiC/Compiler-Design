#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include "GC.c"
#include "ExceptionHandlingFunctions.c"
VM* vm;
typedef struct Empty {
}Empty;
typedef struct ManyTypes {
	int i;
	bool b;
	struct Empty* e;
	int* i_array;
	bool* b_array;
	struct Empty** e_array;
}ManyTypes;
int aa;
bool bb;
struct Empty* cc;
int* dd;
bool* ee;
struct Empty** ff;
int returnInt() {
	int i;
	i = 0;
	return i;
	//set all vars to zero and have the TEMP variable for the return expression
}
bool returnBoolean(bool b) {
	bool c;
	int i;
	c = true;
	i = 0;
	return !!((!c) && (false || true) || (i > 2 && i >= -1) || 3 == i && (+4 != i) || 3 < 5 && ~1 <= -+~2);
	//set all vars to zero and have the TEMP variable for the return expression
}
struct Empty* returnEmpty(int i, struct Empty* e) {
	int j;
	struct Empty* f;
	f = (Empty*)newObject(vm, &f, sizeof (Empty));
	return f;
	//set all vars to zero and have the TEMP variable for the return expression
}
int* returnIntArray() {
	int* i;
	i = newArray(vm,"int",&i ,(((unsigned int)1 + ((2 - 3) * 4) / (checkDivision((5 % 6 << (checkLeftShift(5 % 6,7)? 7 : 7)))? (5 % 6 << (checkLeftShift(5 % 6,7)? 7 : 7)) : (5 % 6 << (checkLeftShift(5 % 6,7)? 7 : 7))) >> (8)) >> 9) + 3 * sizeof (int), NULL);
	return i;
	//set all vars to zero and have the TEMP variable for the return expression
}
bool* returnBooleanArray(bool* b) {
	bool* c;
	c = newArray(vm,"bool",&c , 4 * sizeof (bool), makeList(vm,&c, 4,true, false, false ));
	return (c);
	//set all vars to zero and have the TEMP variable for the return expression
}
struct Empty** returnObjectArray(int* i, struct Empty** e) {
	return e;
	//set all vars to zero and have the TEMP variable for the return expression
}
void returnVoid() {
	//set all vars to zero and have the TEMP variable for the return expression
}
int g;
int main(char* str) {
	vm = newVM();
	int i;
	int* i_array;
	int j;
	bool b;
	bool* b_array;
	struct ManyTypes* m;
	struct ManyTypes** m_array;
	i = 0;
	j = i;
	b = true;
	if ((i <= 0)) {
		while (~9 <= i && i == -1) {
			i = i + i;
		}
		b = false;
		while (true && b) {
			i++;
		}
		i--;
		for (i = 0 ; !(i != 0) ; i--, i++, i--) {
			i = ((unsigned int)i) >> +-1;
			returnInt();
			returnVoid();
		}
	}else{
	}
	do {
		i++;
	}while(+7 << (checkLeftShift(+7,~(-5))? ~(-5) : ~(-5)) == j);
	m = (ManyTypes*)newObject(vm, &m, sizeof (ManyTypes));
	m->b = false;
	i = 4;
	m_array = newArray(vm,"struct ManyTypes*",&m_array ,i * sizeof (struct ManyTypes*), NULL);
	m_array[0] = m;
	i_array = newArray(vm,"int",&i_array , 5 * sizeof (int), makeList(vm,&i_array, 5,0, i, j, 7 ));
	j = 0;
	m->i = 0;
	m_array[(checkArray(i_array,(checkNotNull(&(m->i))? m->i : m->i), "i_array", 5) && checkNotNull(&i_array[(checkNotNull(&(m->i))? m->i : m->i)])? i_array[(checkNotNull(&(m->i))? m->i : m->i)] : i_array[(checkNotNull(&(m->i))? m->i : m->i)])] = (checkArray(m_array,j, "m_array", i) && checkNotNull(&m_array[j])? m_array[j] : m_array[j]);
	for (i = 2, i = j ; j < (+i << (checkLeftShift(+i,4)? 4 : 4)) && j == i ; j++, i--) {
	}
	b = false;
	m = b ? NULL : ((m));
	b_array = newArray(vm,"bool",&b_array , 2 * sizeof (bool), makeList(vm,&b_array, 2,true ));
	m->b_array = b_array;
	b_array = returnBooleanArray((checkNotNull(&(m->b_array))? m->b_array : m->b_array));
	m = NULL;
	b_array = newArray(vm,"bool",&b_array , 2 * sizeof (bool), makeList(vm,&b_array, 2,true ));
	i_array[returnInt()] = (checkArray(i_array,(checkArray(returnIntArray(),2, "returnIntArray()", -1) && checkNotNull(&returnIntArray()[2])? returnIntArray()[2] : returnIntArray()[2]), "i_array", 5) && checkNotNull(&i_array[(checkArray(returnIntArray(),2, "returnIntArray()", -1) && checkNotNull(&returnIntArray()[2])? returnIntArray()[2] : returnIntArray()[2])])? i_array[(checkArray(returnIntArray(),2, "returnIntArray()", -1) && checkNotNull(&returnIntArray()[2])? returnIntArray()[2] : returnIntArray()[2])] : i_array[(checkArray(returnIntArray(),2, "returnIntArray()", -1) && checkNotNull(&returnIntArray()[2])? returnIntArray()[2] : returnIntArray()[2])]);
	return 0;
}