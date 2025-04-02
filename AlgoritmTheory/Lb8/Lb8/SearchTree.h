#pragma once
#include <iostream>
#include "TreeNode.h"

template<typename T, typename K>
class SearchTree
{
private:
	int _count = 0;
	TreeNode<T, K>* _coreNodePtr = nullptr;
public:
	int getCount() const {
		return _count;
	}
	void add(T value, K key) {
		if (_coreNodePtr == nullptr) {
			_coreNodePtr = new TreeNode<T, K>(value, key);
			_count++;
		}
		else {
			addNode(_coreNodePtr, new TreeNode<T, K>(value, key));
		}
	}
	void remove(K key) {
		_coreNodePtr = deleteNode(_coreNodePtr, key);
	}
	T peek(K key) {
		return searchNode(_coreNodePtr, key)->Value;
	}
	bool isContains(K key) {
		return searchNode(_coreNodePtr, key) != nullptr;
	}
	int getHeight() {
		return getHeightFrom(_coreNodePtr);
	}
	int getDepth(K key) {
		return getDepthFrom(_coreNodePtr, key);
	}
private:
	void addNode(TreeNode<T, K>* corePtr, TreeNode<T, K>* nodePtr) {
		if (nodePtr->Key < corePtr->Key) {
			if (corePtr->Left == nullptr) {
				corePtr->Left = nodePtr;
				_count++;
			}
			else {
				addNode(corePtr->Left, nodePtr);
			}
		}
		else if (nodePtr->Key > corePtr->Key) {
			if (corePtr->Right == nullptr) {
				corePtr->Right = nodePtr;
				_count++;
			}
			else {
				addNode(corePtr->Right, nodePtr);
			}
		}
		else {
			throw std::invalid_argument("A node with this key already exists");
		}
	}
	TreeNode<T, K>* searchNode(TreeNode<T, K>* node, K key) {
		if (node == nullptr) {
			return nullptr;
		}
		else if(node->Key == key) {
			return node;
		}
		else {
			return (key < node->Key) ? searchNode(node->Left, key) : searchNode(node->Right, key);
		}
	}
	TreeNode<T, K>* getMin(TreeNode<T, K>* node) {
		if (node == nullptr) {
			return nullptr;
		}
		else if(node->Left == nullptr) {
			return node;
		}
		else {
			return getMin(node->Left);
		}
	}
	TreeNode<T, K>* getMax(TreeNode<T, K>* node) {
		if (node == nullptr) {
			return nullptr;
		}
		else if (node->Right == nullptr) {
			return node;
		}
		else {
			return getMax(node->Right);
		}
	}
	TreeNode<T, K>* deleteNode(TreeNode<T, K>* node, K key) {
		if (node == nullptr) {
			return nullptr;
		}
		else if (key < node->Key) {
			node->Left = deleteNode(node->Left, key);
		}
		else if (key > node->Key) {
			node->Right = deleteNode(node->Right, key);
		}
		else {
			if (node->Left == nullptr || node->Right == nullptr) {
				TreeNode<T, K>* tmp = node;
				node = (node->Left == nullptr) ? node->Right : node->Left;
				delete tmp;
				_count--;
			}
			else {
				TreeNode<T, K>* maxInLeft = getMax(node->Left);
				node->Key = maxInLeft->Key;
				node->Value = maxInLeft->Value;
				node->Left = deleteNode(node->Left, maxInLeft->Key);
			}
		}
		return node;
	}
	int getHeightFrom(TreeNode<T, K>* node) {
		if (node == nullptr) {
			return 0;
		}

		int leftHeight = getHeightFrom(node->Left) + 1;
		int rightHeight = getHeightFrom(node->Right) + 1;
		return leftHeight > rightHeight ? leftHeight : rightHeight;
	}
	int getDepthFrom(TreeNode<T, K>* node, K key) {
		if (node == nullptr) {
			return 0;
		}
		else if (node->Key == key) {
			return 1;
		}
		else {
			int result = key < node->Key ? getDepthFrom(node->Left, key) : getDepthFrom(node->Right, key);
			return result >= 1 ? result + 1 : 0;
		}
	}
	void printTree(std::ostream& os, TreeNode<T, K>* node ) const {
		if (node == nullptr) {
			return;
		}
		printTree(os, node->Left);
		os << " " << *node;
		printTree(os, node->Right);
	}
	friend std::ostream& operator <<(std::ostream& os, const SearchTree<T, K>& searchTree) {
		searchTree.printTree(os, searchTree._coreNodePtr);
		return os;
	}
};
