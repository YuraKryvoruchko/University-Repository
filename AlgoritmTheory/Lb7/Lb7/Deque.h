#pragma once
#include <iostream>
#include "LinkedListNode.h"

template<typename T>
class Deque
{
private:
	int _count = 0;
	LinkedListNode<T>* _left = nullptr;
	LinkedListNode<T>* _right = nullptr;
public:
	int getCount() const {
		return _count;
	}
	void pushLeft(T value) {
		addFirst(new LinkedListNode<int>(value, nullptr));
	}
	void pushRight(T value) {
		addLast(new LinkedListNode<T>(value, nullptr));
	}
	T popLeft() {
		LinkedListNode<T>* nodePtr = removeFirst();
		T value = nodePtr->Value;
		delete nodePtr;
		return value;
	}
	T popRight() {
		LinkedListNode<T>* nodePtr = removeLast();
		T value = nodePtr->Value;
		delete nodePtr;
		return value;
	}
	T peekLeft() const {
		if (_count == 0) {
			throw std::exception("Cannot peek the element because it does not exist in the deque");
		}

		return _left->Value;
	}
	T peekRight() const {
		if (_count == 0) {
			throw std::exception("Cannot peek the element because it does not exist in the deque");
		}

		return _right->Value;
	}
	void clear() {
		while (_right != nullptr) {
			delete removeFirst();
		}
	}
private:
	LinkedListNode<T>* getFirst() const {
		return _left;
	}
	LinkedListNode<T>* getLast() const {
		return _right;
	}
	void addFirst(LinkedListNode<T>* node) {
		if (_count == 0) {
			_left = _right = node;
		}
		else {
			LinkedListNode<T>* leftNodePtr = _left;
			_left = node;
			_left->NextNode = leftNodePtr;
		}
		_count++;
	}
	void addLast(LinkedListNode<T>* node) {
		if (_count == 0) {
			_left = _right = node;
		}
		else {
			_right->NextNode = node;
			_right = node;
		}
		_count++;
	}
	LinkedListNode<T>* removeFirst() {
		LinkedListNode<T>* removedNode = _left;
		if (_count == 0) {
			throw std::invalid_argument("Cannot remove the last node because it does not exist in the list");
		}
		else if (_count == 1) {
			_left = _right = nullptr;
			_count--;
			return removedNode;
		}
		_count--;
		_left = _left->NextNode;
		return removedNode;
	}
	LinkedListNode<T>* removeLast() {
		LinkedListNode<T>* removedNode = _right;
		if (_count == 0) {
			throw std::invalid_argument("Cannot remove the last node because it does not exist in the list");
		}
		else if (_count == 1) {
			_left = _right = nullptr;
			_count--;
			return removedNode;
		}
		_count--;
		LinkedListNode<T>* next = _left;
		while (next->NextNode != _right) {
			next = next->NextNode;
		}

		_right = next;
		_right->NextNode = nullptr;
		return removedNode;
	}

	friend std::ostream& operator <<(std::ostream& os, const Deque<T>& deque) {
		if (deque.getCount() == 0) {
			return os;
		}

		auto next = deque.getFirst();
		os << *next;
		while (next->NextNode != nullptr) {
			next = next->NextNode;
			os << " - " << *next;
		}
		return os;
	}
};