// Lb6.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <functional>
#include "LinkedListNode.h"
#include "LinkedList.h"

using namespace std;

void viewAddLastScreen(LinkedList<int>& list) {
	system("cls");
	cout << "Input value: ";
	int value = 0;
	cin >> value;
	list.addLast(new LinkedListNode<int>(value));
}
void viewAddAfterScreen(LinkedList<int>& list) {
	system("cls");
	cout << "Input the node number: ";
	int i = 0, value = 0;
	cin >> i;
	if (i > list.getCount() || i < 1) {
		cout << "You inputed the invalid number! Try again!";
		system("pause");
		return;
	}

	cout << "Input value: ";
	cin >> value;
	list.addAfter(i - 1, new LinkedListNode<int>(value));
}
void viewGetCountElementsByConditionScreen(LinkedList<int>& list) {
	system("cls");
	int number = 0;	
	cout << "Input the number to compare with: ";
	cin >> number;

	cout << "\nChoose the condition:" << endl;
	cout << "1. >" << endl;
	cout << "2. >=" << endl;
	cout << "3. <" << endl;
	cout << "4. <=" << endl;
	cout << "5. ==" << endl;
	cout << "6. !=" << endl;

	std::function<bool(int)> condition = nullptr;
	bool isContinue = true;
	while (isContinue) {
		isContinue = false;

		cout << "Condition: ";
		char command = 0;
		cin >> command;

		switch (command) {
		case '1': condition = [number](int value) { return value > number; };  break;
		case '2': condition = [number](int value) { return value >= number; }; break;
		case '3': condition = [number](int value) { return value < number; }; break;
		case '4': condition = [number](int value) { return value <= number; }; break;
		case '5': condition = [number](int value) { return value == number; }; break;
		case '6': condition = [number](int value) { return value != number; }; break;
		default: isContinue = true; cout << "Invalid command!" << endl; break;
		}
	}
	cout << "Number of elements by condition: " << list.getNumberByCondition(condition) << endl;
	system("pause");
}
void handleInvalidInput() {
	cout << "You inputed the invalid command! Try again!";
	system("pause");
}

int main()
{
	LinkedList<int> list;

	bool isContinue = true;
	while (isContinue) {
		system("cls");
		cout << "1. Add last" << endl;
		cout << "2. Add after" << endl;
		cout << "3. Remove the last" << endl;
		cout << "4. Get count by prediction" << endl;
		cout << "5. Exit" << endl;
		cout << "Your list: " << list << endl;
		cout << "Element cout: " << list.getCount() << endl;
		cout << "\nInput command: ";
		char command = 0;
		cin >> command;
		switch (command) {
		case '1': viewAddLastScreen(list);						break;
		case '2': viewAddAfterScreen(list);						break;
		case '3': delete list.removeLast();						break;
		case '4': viewGetCountElementsByConditionScreen(list);	break;
		case '5': isContinue = false;							break;
		default: handleInvalidInput();							break;
		}
	}

 	return 0;
}