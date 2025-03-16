#pragma once
#include <iostream>
#include "LinkedListNode.h"

template<typename T>
class Stack
{
private:
	int _count = 0;
	LinkedListNode<T>* _first = nullptr;
	LinkedListNode<T>* _last = nullptr;
public:
	int getCount() const {
		return _count;
	}
	void push(T value) {
		addLast(new LinkedListNode<T>(value, nullptr));
	}
	T pop() {
		LinkedListNode<T>* nodePtr = removeLast();
		T value = nodePtr->Value;
		delete nodePtr;
		return value;
	}
	T peek() {
		return _last->Value;
	}
	void clear() {
		while (_first != nullptr) {
			delete removeLast();
		}
	}
private:
	LinkedListNode<T>* getFirst() const {
		return _first;
	}
	LinkedListNode<T>* getLast() const {
		return _last;
	}
	void addLast(LinkedListNode<T>* node) {
		if (_count == 0) {
			_first = _last = node;
		}
		else {
			_last->NextNode = node;
			_last = node;
		}
		_count++;
	}
	LinkedListNode<T>* removeLast() {
		LinkedListNode<T>* removedNode = _last; 
		if (_count == 0) {
			throw std::invalid_argument("Cannot remove the last node because it does not exist in the list");
		}
		else if (_count == 1) {
			_first = _last = nullptr;
			_count--;
			return removedNode;
		}
		_count--;
		LinkedListNode<T>* next = _first;
		while (next->NextNode != _last) {
			next = next->NextNode;
		}

		_last = next;
		_last->NextNode = nullptr;
		return removedNode;
	}

	friend std::ostream& operator <<(std::ostream& os, const Stack<T>& list) {
		if (list.getCount() == 0) {
			return os;
		}

		auto next = list.getFirst();
		os << *next;
		while (next->NextNode != nullptr) {
			next = next->NextNode;
			os << " -> " << *next;
		}
		return os;
	}
};