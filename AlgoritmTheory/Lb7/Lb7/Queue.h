#pragma once
#include <iostream>
#include "LinkedListNode.h"

template<typename T>
class Queue
{
private:
	int _count = 0;
	LinkedListNode<T>* _first = nullptr;
	LinkedListNode<T>* _last = nullptr;
public:
	int getCount() const {
		return _count;
	}
	void enqueue(T value) {
		addLast(new LinkedListNode<T>(value, nullptr));
	}
	T dequeue() {
		LinkedListNode<T>* nodePtr = removeFirst();
		T value = nodePtr->Value;
		delete nodePtr;
		return value;
	}
	T peek() const {
		if (_count == 0) {
			throw std::exception("Cannot peek the element because it does not exist in the queue");
		}

		return _first->Value;
	}
	void clear() {
		while (_last != nullptr) {
			delete removeFirst();
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
	LinkedListNode<T>* removeFirst() {
		LinkedListNode<T>* removedNode = _first;
		if (_count == 0) {
			throw std::invalid_argument("Cannot remove the last node because it does not exist in the list");
		}
		else if (_count == 1) {
			_first = _last = nullptr;
			_count--;
			return removedNode;
		}
		_count--;
		_first = _first->NextNode;
		return removedNode;
	}

	friend std::ostream& operator <<(std::ostream& os, const Queue<T>& queue) {
		if (queue.getCount() == 0) {
			return os;
		}

		auto next = queue.getFirst();
		os << *next;
		while (next->NextNode != nullptr) {
			next = next->NextNode;
			os << " <- " << *next;
		}
		return os;
	}
};
