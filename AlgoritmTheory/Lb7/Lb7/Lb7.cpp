#include <iostream>
#include "Stack.h"
#include "Queue.h"
#include "Deque.h"

using namespace std;

void testStack() {
    Stack<int> stack;
    stack.push(1);
    stack.push(2);
    stack.push(3);
    cout << stack << "\nCount: " << stack.getCount() << std::endl;
    stack.pop();
    cout << stack << "\nCount: " << stack.getCount() << std::endl;
    int value = stack.peek();
    cout << stack << "\nValue: " << value << "\nCount: " << stack.getCount() << std::endl;
    stack.clear();
    cout << stack << "Clear:\nCount: " << stack.getCount() << std::endl;
}
void testQueue() {
    Queue<int> queue;
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    cout << queue << "\nCount: " << queue.getCount() << endl;
    int value = queue.dequeue();
    cout << queue << "\nDequeue: " << value << "\nCount: " << queue.getCount() << endl;
    value = queue.peek();
    cout << queue << "\nPeek: " << value << "\nCount: " << queue.getCount() << endl;
    queue.clear();
    cout << queue << "\nCount: " << queue.getCount() << endl;
}
void testDeque() {
    Deque<int> deque;
    deque.enqueueLeft(1);
    cout << "Added 1 left: " << deque << "\nCount: " << deque.getCount() << "\n\n";
    deque.enqueueLeft(2);
    cout << "Added 2 left: " << deque << "\nCount: " << deque.getCount() << "\n\n";
    deque.enqueueRight(3);
    cout << "Added 1 right: " << deque << "\nCount: " << deque.getCount() << "\n\n";
    int peekedValue = deque.peekLeft();
    cout << "Peeked left value: " << peekedValue << "\n";
    peekedValue = deque.peekRight();
    cout << "Peeked right value: " << peekedValue << "\n";
    cout << "Deque: " << deque << "\nCount: " << deque.getCount() << "\n\n";
    deque.dequeueLeft();
    cout << "Removed left: " << deque << "\nCount: " << deque.getCount() << "\n\n";
    deque.dequeueRight();
    cout << "Removed right: " << deque << "\nCount: " << deque.getCount() << "\n\n";
    deque.clear();
    cout << "Clear: " << deque << "\nCount: " << deque.getCount() << "\n\n";
}

int main()
{
    //testStack();
    //testQueue();
    testDeque();

    return 0;
}
