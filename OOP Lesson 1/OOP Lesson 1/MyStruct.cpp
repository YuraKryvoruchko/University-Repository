#include "MyStruct.h"
#include <iostream>

using namespace std;

void MyStruct::Init(int first, int second)
{
    if (first < 0 || second < 0)
        throw invalid_argument("Received negative value");

    this->first = first;
    this->second = second;
}
void MyStruct::Read() {
    cout << "Number of hours: ";
    do {
        cin >> this->first;

        if (this->first < 0) {
            cout << "Received negative value!" << endl;
        }
    } while (this->first < 0);

    cout << "Number of minutes: ";
    do {
        cin >> this->second;

        if (this->second < 0) {
            cout << "Received negative value!" << endl;
        }
    } while (this->second < 0);
}
void MyStruct::Display() {
    cout << "Hours: " << this->first << ": Minutes: " << this->second << endl;
}
int MyStruct::Minutes() {
    this->second += this->first * 60;
    this->first = 0;

    return this->second;
}