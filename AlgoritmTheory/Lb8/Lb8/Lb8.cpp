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
        cout << "3. Delete element" << endl;
        cout << "4. Get height of tree" << endl;
        cout << "5. Get depth for element" << endl;
        cout << "6. Exit" << endl;
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
            cout << "Result: " << tree.isContains(value);
            system("pause");
            break;
        case '3':
            cout << "Delete value: ";
            cin >> value;
            tree.remove(value);
            break;
        case '4':
            cout << "Height of tree: " << tree.getHeight() << endl;
            system("pause");
            break;
        case '5':
            cout << "Enter key: ";
            cin >> value;
            cout << "Depth of " << '[' << value << ']' << " element: " << tree.getDepth(value) << endl;
            system("pause");
            break;
        case '6':
            isContinue = false;
            break;
        default: handleInvalidInput();
            break;
        }
    }

    return 0;
}
