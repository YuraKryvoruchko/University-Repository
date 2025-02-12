// Lb6.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include "toString.h"
#include "LinkedListNode.h"
#include "LinkedList.h"

class Test {
public:
	int A;
	friend std::ostream& operator <<(std::ostream& os, const Test& test) {
		return os << std::to_string(test.A);
	}
};

int main()
{
	Test test, test2, test3;
	test.A = 14;
	test2.A = 19;
	test3.A = -2;

	LinkedList<Test> linkedList;
	linkedList.addFirst(test);
	linkedList.addLast(test2);
	linkedList.addLast(test3);

	std::string text = linkedList.toString();
	std::cout << text;
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
