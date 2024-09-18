#include <iostream>
#include "MyStruct.h"

using namespace std;

int main()
{
    MyStruct myStruct;
    myStruct.Init(2, 34);

    bool isContinue = true;
    while (isContinue) {
        myStruct.Display();

        cout << "\nCommand list:" << endl;
        cout << "1. Display" << endl;
        cout << "2. Read" << endl;
        cout << "3. Minutes" << endl;
        cout << "4. Exit the program" << endl;

        cout << "\nInput command number: ";
        int commandNumber = 0;
        cin >> commandNumber;

        switch (commandNumber)
        {
        case 1:
            myStruct.Display();
            system("pause");
            break;
        case 2:
            myStruct.Read();
            break;
        case 3:
            cout << "Minutes: " << myStruct.Minutes() << endl;
            system("pause");
            break;
        case 4:
            isContinue = false;
            break;
        default:
            cout << "Invalid command number! Try again!" << endl;
            system("pause");
            break;
        }

        system("cls");
    }
    
    return 0;
}