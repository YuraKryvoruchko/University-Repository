#pragma once
#include <iostream>
#include "LinkedListNode.h"

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
	void addFirst(T value);
	void addLast(T value);
	void addAfter(LinkedListNode<T>* node, LinkedListNode<T>* after) {
		LinkedListNode<T>* next = node->NextNode;
		node->NextNode = after;
		after->NextNode = next;
	}
	void insertAfter(int index, T value);
	void remove(T value);
	void removeNode(LinkedListNode<T>* node);
	void removeLast();

	std::string toString();
};