#pragma once
#include <iostream>

template<typename T>
class LinkedListNode
{
public:
	T Value;
	LinkedListNode<T>* NextNode;

	LinkedListNode(T value, LinkedListNode<T>* next = nullptr)
		: Value(value), NextNode(next) {}

	friend std::ostream& operator <<(std::ostream& os, const LinkedListNode<T>& node) {
		return os << '[' << node.Value << ']';
	}
};

