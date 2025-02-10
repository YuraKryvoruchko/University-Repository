#include "LinkedList.h"
#include <iostream>;

template<typename T>
LinkedListNode<T>::LinkedListNode(T value, LinkedListNode<T>* next) {
	this->Value = value;
	this->NextNode = next;
}

template<typename T>
int LinkedList<T>::getCount() {
	return this->_count;
}

template<typename T>
int LinkedList<T>::getNumberByProperty(bool (*function)(T)) {
	int count = 0;
	LinkedListNode<T>* next = this->getFirst();
	while (next != nullptr) {
		if (function(next->Value)) {
			count++;
		}
		next = next->NextNode;
	}
	return count;
}

template<typename T>
LinkedListNode<T>* LinkedList<T>::getFirst() {
	return this->_first;
}

template<typename T>
LinkedListNode<T>* LinkedList<T>::getLast() {
	return this->_last;
}

template<typename T>
void LinkedList<T>::insertAtBeginning(T value) {
	if (this->_first == nullptr) {
		this->_first = new LinkedListNode<T>(value, nullptr);
		this->_last = this->_first
	}
	else {
		LinkedListNode<T>* newNode = new LinkedListNode<T>(value, this->_first);
		this->_first = newNode;
	}
	this->_count++;
}

template<typename T>
void LinkedList<T>::insertAtEnd(T value) {
	if (this->_last == nullptr) {
		this->_first = new LinkedListNode<T>(value, nullptr);
		this->_last = this->_first
	}
	else {
		LinkedListNode<T>* newNode = new LinkedListNode<T>(value, nullptr);
		this->_last->NextNode = newNode;
		this->_last = newNode;
	}
	this->_count++;
}

template<typename T>
void LinkedList<T>::insertAtPosition(T value, int position) {
	if (position >= this->_count) {
		throw std::invalid_argument("Argument Out Of Range Exception");
	}
	
	LinkedListNode<T>* previous = this->_first;
	for (int i = 1; i < position; i++) {
		previous = previous->NextNode;
	}

	LinkedListNode<T>* nextNode = previous->NextNode;
	previous->NextNode = new LinkedListNode<T>(value, nextNode);
	this->_count++;
}