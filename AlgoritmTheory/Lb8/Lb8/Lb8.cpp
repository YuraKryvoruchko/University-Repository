#include <iostream>
#include "SearchTree.h"

using namespace std;

void handleInvalidInput() {
    cout << "You inputed the invalid command! Try again!";
    system("pause");
}

int main()
{
    SearchTree<int, int> tree;

    bool isContinue = true;
    while (isContinue) {
        system("cls");
        cout << "1. Add element" << endl;
        cout << "2. Is constains element" << endl;
        cout << "3. Peek element" << endl;
        cout << "4. Delete element" << endl;
        cout << "5. Get height of tree" << endl;
        cout << "6. Get depth for element" << endl;
        cout << "7. Set around mode" << endl;
        cout << "8. Exit" << endl;
        cout << "Your tree: " << tree << endl;
        cout << "Element cout: " << tree.getCount() << endl;
        cout << "\nInput command: ";
        char command = 0;
        cin >> command;
        int value = 0;
        switch (command) {
        case '1':
            cout << "Enter value: ";
            cin >> value;
            tree.add(value, value);
            break;
        case '2':
            cout << "Enter value: ";
            cin >> value;
            cout << "Result: " << tree.isContains(value) << endl;
            system("pause");
            break;
        case '3':
            cout << "Peekable value: ";
            cin >> value;
            cout << "Peeked value: " << tree.peek(value) << endl;
            system("pause");
            break;
        case '4':
            cout << "Delete value: ";
            cin >> value;
            tree.remove(value);
            break;
        case '5':
            cout << "Height of tree: " << tree.getHeight() << endl;
            system("pause");
            break;
        case '6':
            cout << "Enter key: ";
            cin >> value;
            cout << "Depth of " << '[' << value << ']' << " element: " << tree.getDepth(value) << endl;
            system("pause");
            break;
        case '7':
            cout << "Valid modes:" << endl;
            cout << "1. Directly" << endl;
            cout << "2. Reversely" << endl;
            cout << "3. Symmetrically" << endl;
            cout << "Enter mode: ";
            cin >> value;
            value--;
            if (value < 0 || value > 2) {
                cout << "Invalid mode - " << value << endl;
                system("pause");
            }
            else {
                tree.setAroundMode(value);
            }
            break;
        case '8':
            isContinue = false;
            break;
        default: handleInvalidInput();
            break;
        }
    }

    return 0;
}
