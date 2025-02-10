#pragma once

#include <iostream>;

template<typename T>
class LinkedListNode {
public:
	LinkedListNode(T value, LinkedListNode<T>* next = nullptr);

	T Value;
	LinkedListNode<T>* NextNode;
};

template<typename T>
class LinkedList
{
private:
	int _count;
	LinkedListNode<T>* _first;
	LinkedListNode<T>* _last;
public:
	int getCount();
	int getNumberByProperty(bool (*function)(T));

	LinkedListNode<T>* getFirst();
	LinkedListNode<T>* getLast();
	void insertAtBeginning(T value);
	void insertAtEnd(T value);
	void insertAtPosition(T value, int position);
	void remove(T value);
	void removeNode(LinkedListNode<T>* node);
	void merge(LinkedList<T> secondList);

	std::string toString();
};

