#pragma once
#include <iostream>
#include "LinkedListNode.h"
#include "toString.h"

template<typename T>
class LinkedList
{
private:
	int _count = 0;
	LinkedListNode<T>* _first = nullptr;
	LinkedListNode<T>* _last = nullptr;
public:
	int getCount() const {
		return _count;
	}
	int getNumberByCondition(std::function<bool(T)> condition) const {
		int number = 0;
		LinkedListNode<T>* next = _first;
		while (next != nullptr) {
			if (condition(next->Value)) {
				number++;
			}
			next = next->NextNode;
		}
		return number;
	}

	LinkedListNode<T>* getFirst() const {
		return _first;
	}
	LinkedListNode<T>* getLast() const {
		return _last;
	}
	void addLast(LinkedListNode<T>* node) {
		if (_count == 0) {
			_first = node;
			_last = node;
		}
		else {
			_last->NextNode = node;
			_last = node;
		}
		_count++;
	}
	void addAfter(LinkedListNode<T>* node, LinkedListNode<T>* after) {
		if (node == _last) {
			_last->NextNode = after;
			_last = after;
		}
		else {
			LinkedListNode<T>* next = node->NextNode;
			node->NextNode = after;
			after->NextNode = next;
		}
		_count++;
	}
	void addAfter(int index, LinkedListNode<T>* after) {
		if (index < 0 || index >= _count) {
			throw std::invalid_argument("Input invalid index!");
		}

		LinkedListNode<T>* next = this->getFirst();
		for (int i = 1; i <= index; i++) {
			next = next->NextNode;
		}

		this->addAfter(next, after);
	}
	LinkedListNode<T>* removeLast() {
		LinkedListNode<T>* removedNode = _last; 
		if (_count == 0) {
			throw std::invalid_argument("Cannot remove the last node because it does not exist in the list");
		}
		else if (_count == 1) {
			_first = nullptr;
			_last = nullptr;
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

	friend std::ostream& operator <<(std::ostream& os, const LinkedList<T>& list) {
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