#include <iostream>
#include "Stack.h"
#include "Queue.h"
#include "Deque.h"

using namespace std;

void handleInvalidInput() {
    cout << "You inputed the invalid command! Try again!";
    system("pause");
}

void testStack() {
    Stack<int> stack;

    bool isContinue = true;
    while (isContinue) {
        system("cls");
        cout << "Stack presentation" << endl;
        cout << "1. Push element" << endl;
        cout << "2. Peek element" << endl;
        cout << "3. Pop element" << endl;
        cout << "4. Clear" << endl;
        cout << "5. Exit" << endl;
        cout << "Your stack: " << stack << endl;
        cout << "Element cout: " << stack.getCount() << endl;
        cout << "\nInput command: ";
        char command = 0;
        cin >> command;
        int value = 0;
        switch (command) {
        case '1':
            cout << "Enter value: " << endl;
            cin >> value;
            stack.push(value);
            break;
        case '2': 
            cout << "Peeked value: " << stack.peek() << endl;
            system("pause");
            break;
        case '3': 
            cout << "Poped value: " << stack.pop() << endl;
            system("pause");
            break;
        case '4':
            stack.clear();
            break;
        case '5': 
            isContinue = false;
            break;
        default: handleInvalidInput();
            break;
        }
    }
}
void testQueue() {
    Queue<int> queue;

    bool isContinue = true;
    while (isContinue) {
        system("cls");
        cout << "Queue presentation" << endl;
        cout << "1. Enqueue element" << endl;
        cout << "2. Peek element" << endl;
        cout << "3. Dequeue element" << endl;
        cout << "4. Clear" << endl;
        cout << "5. Exit" << endl;
        cout << "Your queue: " << queue << endl;
        cout << "Element cout: " << queue.getCount() << endl;
        cout << "\nInput command: ";
        char command = 0;
        cin >> command;
        int value = 0;
        switch (command) {
        case '1':
            cout << "Enter value: " << endl;
            cin >> value;
            queue.enqueue(value);
            break;
        case '2':
            cout << "Peeked value: " << queue.peek() << endl;
            system("pause");
            break;
        case '3':
            cout << "Poped value: " << queue.dequeue() << endl;
            system("pause");
            break;
        case '4':
            queue.clear();
            break;
        case '5':
            isContinue = false;
            break;
        default: handleInvalidInput();
            break;
        }
    }
}
void testDeque() {
    Deque<int> deque;

    bool isContinue = true;
    while (isContinue) {
        system("cls");
        cout << "Deque presentation" << endl;
        cout << "1. Push element left" << endl;
        cout << "2. Push element right" << endl;
        cout << "3. Peek left element" << endl;
        cout << "4. Peek right element" << endl;
        cout << "5. Pop left element" << endl;
        cout << "6. Pop right element" << endl;
        cout << "7. Clear" << endl;
        cout << "8. Exit" << endl;
        cout << "Your queue: " << deque << endl;
        cout << "Element cout: " << deque.getCount() << endl;
        cout << "\nInput command: ";
        char command = 0;
        cin >> command;
        int value = 0;
        switch (command) {
        case '1':
            cout << "Enter value: " << endl;
            cin >> value;
            deque.pushLeft(value);
            break;
        case '2':
            cout << "Enter value: " << endl;
            cin >> value;
            deque.pushRight(value);
            break;
        case '3':
            cout << "Peeked left value: " << deque.peekLeft() << endl;
            system("pause");
            break;
        case '4':
            cout << "Peeked right value: " << deque.peekRight() << endl;
            system("pause");
            break;
        case '5':
            cout << "Poped value: " << deque.popLeft() << endl;
            system("pause");
            break;
        case '6':
            cout << "Poped value: " << deque.popRight() << endl;
            system("pause");
            break;
        case '7':
            deque.clear();
            break;
        case '8':
            isContinue = false;
            break;
        default: handleInvalidInput();
            break;
        }
    }
}

int main()
{
    bool isContinue = true;
    while (isContinue) {
        system("cls");
        cout << "Select a structure" << endl;
        cout << "1. Stack" << endl;
        cout << "2. Queue" << endl;
        cout << "3. Deque" << endl;
        cout << "4. Exit" << endl;
        cout << "\nInput command: ";
        char command = 0;
        cin >> command;
        switch (command) {
        case '1': testStack();          break;
        case '2': testQueue();          break;
        case '3': testDeque();          break;
        case '4': isContinue = false;   break;
        default: handleInvalidInput();  break;
        }
    }

    return 0;
}
