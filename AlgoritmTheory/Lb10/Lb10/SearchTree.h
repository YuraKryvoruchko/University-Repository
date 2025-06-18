#pragma once
#include <iostream>
#include "TreeNode.h"

template<typename T, typename K>
class SearchTree
{
private:
	int _count = 0;
	char _aroundMode = 0;
	TreeNode<T, K>* _coreNodePtr = nullptr;
public:
	int getCount() const {
		return _count;
	}
	char getAroundMode() const {
		return _aroundMode;
	}
	void setAroundMode(char mode) {

		_aroundMode = mode;
	}
	void add(T value, K key) {
		_coreNodePtr = addNode(_coreNodePtr, new TreeNode<T, K>(value, key));
	}
	void remove(K key) {
		_coreNodePtr = deleteNode(_coreNodePtr, key);
	}
	T peek(K key) const {
		TreeNode<T, K>* nodePtr = searchNode(_coreNodePtr, key);
		if (nodePtr == nullptr) {
			throw std::invalid_argument("");
		}
		return nodePtr->Value;
	}
	bool isContains(K key) const {
		return searchNode(_coreNodePtr, key) != nullptr;
	}
	int getHeight() const {
		return getHeightFrom(_coreNodePtr);
	}
	int getDepth(K key) const {
		return getDepthFrom(_coreNodePtr, key);
	}
private:
	int getNodeHeight(TreeNode<T, K>* node) const {
		return node ? node->Height : 0;
	}
	int getBalance(TreeNode<T, K>* node) const {
		return node ? getNodeHeight(node->Left) - getNodeHeight(node->Right) : 0;
	}
	void updateNodeHeight(TreeNode<T, K>* node) {
		if (node)
			node->Height = std::max(getNodeHeight(node->Left), getNodeHeight(node->Right)) + 1;
	}
	TreeNode<T, K>* rotateRight(TreeNode<T, K>* y) {
		TreeNode<T, K>* x = y->Left;
		TreeNode<T, K>* T2 = x->Right;

		x->Right = y;
		y->Left = T2;

		updateNodeHeight(y);
		updateNodeHeight(x);

		return x;
	}
	TreeNode<T, K>* rotateLeft(TreeNode<T, K>* x) {
		TreeNode<T, K>* y = x->Right;
		TreeNode<T, K>* T2 = y->Left;

		y->Left = x;
		x->Right = T2;

		updateNodeHeight(x);
		updateNodeHeight(y);

		return y;
	}
	TreeNode<T, K>* addNode(TreeNode<T, K>* node, TreeNode<T, K>* newNode) {
		if (node == nullptr) {
			_count++;
			return newNode;
		}

		if (newNode->Key < node->Key) {
			node->Left = addNode(node->Left, newNode);
		}
		else if (newNode->Key > node->Key) {
			node->Right = addNode(node->Right, newNode);
		}
		else {
			throw std::invalid_argument("The element with this key does not exist!");
		}

		updateNodeHeight(node);

		int balance = getBalance(node);

		if (balance > 1 && newNode->Key < node->Left->Key)
			return rotateRight(node);

		if (balance < -1 && newNode->Key > node->Right->Key)
			return rotateLeft(node);

		if (balance > 1 && newNode->Key > node->Left->Key) {
			node->Left = rotateLeft(node->Left);
			return rotateRight(node);
		}

		if (balance < -1 && newNode->Key < node->Right->Key) {
			node->Right = rotateRight(node->Right);
			return rotateLeft(node);
		}

		return node;
	}
	TreeNode<T, K>* searchNode(TreeNode<T, K>* node, K key) const {
		if (node == nullptr) {
			return nullptr;
		}
		else if (node->Key == key) {
			return node;
		}
		else {
			return (key < node->Key) ? searchNode(node->Left, key) : searchNode(node->Right, key);
		}
	}
	TreeNode<T, K>* getMin(TreeNode<T, K>* node) const {
		if (node == nullptr) {
			return nullptr;
		}
		else if (node->Left == nullptr) {
			return node;
		}
		else {
			return getMin(node->Left);
		}
	}
	TreeNode<T, K>* getMax(TreeNode<T, K>* node) const {
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
	TreeNode<T, K>* deleteNode(TreeNode<T, K>* root, K key) {
		if (!root) {
			throw std::invalid_argument("The element with this key does not exist!");
		}

		if (key < root->Key) {
			root->Left = deleteNode(root->Left, key);
		}
		else if (key > root->Key) {
			root->Right = deleteNode(root->Right, key);
		}
		else {
			if (!root->Left || !root->Right) {
				TreeNode<T, K>* temp = root->Left ? root->Left : root->Right;
				delete root;
				_count--;
				return temp;
			}
			else {
				TreeNode<T, K>* maxLeft = getMax(root->Left);
				root->Key = maxLeft->Key;
				root->Value = maxLeft->Value;
				root->Left = deleteNode(root->Left, maxLeft->Key);
			}
		}

		updateNodeHeight(root);

		int balance = getBalance(root);

		if (balance > 1 && getBalance(root->Left) >= 0)
			return rotateRight(root);

		if (balance > 1 && getBalance(root->Left) < 0) {
			root->Left = rotateLeft(root->Left);
			return rotateRight(root);
		}

		if (balance < -1 && getBalance(root->Right) <= 0)
			return rotateLeft(root);

		if (balance < -1 && getBalance(root->Right) > 0) {
			root->Right = rotateRight(root->Right);
			return rotateLeft(root);
		}

		return root;
	}
	int getHeightFrom(TreeNode<T, K>* node) const {
		if (node == nullptr) {
			return 0;
		}

		int leftHeight = getHeightFrom(node->Left) + 1;
		int rightHeight = getHeightFrom(node->Right) + 1;
		return leftHeight > rightHeight ? leftHeight : rightHeight;
	}
	int getDepthFrom(TreeNode<T, K>* node, K key) const {
		if (node == nullptr) {
			throw std::invalid_argument("The element with this key does not exist");
		}
		else if (node->Key == key) {
			return 1;
		}
		else {
			return key < node->Key ? getDepthFrom(node->Left, key) + 1 : getDepthFrom(node->Right, key) + 1;
		}
	}
	void aroundDirectly(std::ostream& os, TreeNode<T, K>* node) const {
		if (node == nullptr) {
			return;
		}
		os << " " << *node;
		aroundDirectly(os, node->Left);
		aroundDirectly(os, node->Right);
	}
	void aroundReversely(std::ostream& os, TreeNode<T, K>* node) const {
		if (node == nullptr) {
			return;
		}
		aroundReversely(os, node->Left);
		aroundReversely(os, node->Right);
		os << " " << *node;
	}
	void aroundSymmetrically(std::ostream& os, TreeNode<T, K>* node) const {
		if (node == nullptr) {
			return;
		}
		aroundSymmetrically(os, node->Left);
		os << " " << *node;
		aroundSymmetrically(os, node->Right);
	}
	friend std::ostream& operator <<(std::ostream& os, const SearchTree<T, K>& searchTree) {
		char aroundMode = searchTree.getAroundMode();
		if (aroundMode == 0) {
			searchTree.aroundDirectly(os, searchTree._coreNodePtr);
		}
		else if (aroundMode == 1) {
			searchTree.aroundReversely(os, searchTree._coreNodePtr);
		}
		else {
			searchTree.aroundSymmetrically(os, searchTree._coreNodePtr);
		}
		return os;
	}
};
