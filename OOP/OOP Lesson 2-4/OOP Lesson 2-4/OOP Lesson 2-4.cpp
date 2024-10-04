#include <iostream>
#include "Date.h"

using namespace std;

int main()
{
    Date date("2005.12.21");

    bool isContinue = true;
    while (isContinue) {
        date.Display();
        cout << '\n';

        cout << "\nCommand list:" << endl;
        cout << "1. Display" << endl;
        cout << "2. Read" << endl;
        cout << "3. AddDays" << endl;
        cout << "4. RemoveDays" << endl;
        cout << "5. IsLeapYear" << endl;
        cout << "6. Compare the date" << endl;
        cout << "7. Get Number Of Days Between Dates" << endl;
        cout << "8. Exit the program" << endl;

        cout << "\nInput command number: ";
        int commandNumber = 0;
        cin >> commandNumber;

        switch (commandNumber)
        {
        case 1: {
            cout << '\n';
            date.Display();
            cout << '\n';
            system("pause");
            break;
        }
        case 2: {
            date.Read();
            break;
        }
        case 3: {
            cout << "Days: ";
            unsigned int addedDays;
            cin >> addedDays;
            date.AddDays(addedDays);
            break;
        }
        case 4: {
            cout << "Days: ";
            unsigned int removedDays;
            cin >> removedDays;
            date.RemoveDays(removedDays);
            break;
        }
        case 5: {
            if (date.IsLeapYear(date.GetYear())) {
                cout << '\n' << date.GetYear() << " is leap year!\n";
            }
            else {
                cout << '\n' << date.GetYear() << " is not leap year!\n";
            }
            system("pause");
            break;
        }
        case 6: {
            Date newDate;
            cout << "New date: ";
            newDate.Read();
            if (date.IsEquals(newDate)) {
                cout << "Date " << date.ToString() << " and date " << newDate.ToString() << " are equals!\n";
            }
            else if (date.IsBefore(newDate)) {
                cout << "Date " << date.ToString() << " is before date " << newDate.ToString() << "!\n";
            }
            else {
                cout << "Date " << date.ToString() << " is after date " << newDate.ToString() << "!\n";
            }
            system("pause");
            break;
        }
        case 7: {
            Date secondDate;
            cout << "New date: ";
            secondDate.Read();
            cout << "Number of days: " << date.GetNumberOfDaysBetweenDates(secondDate) << "!\n";
            system("pause");
            break;
        }
        case 8: {
            isContinue = false;
            break;
        }
        default: {
            cout << "Invalid command number! Try again!" << endl;
            system("pause");
            break;
        }
        }

        system("cls");
    }

    return 0;
}