#include "Pair.h"
#include <iostream>

using namespace std;

Pair::Pair(double first = 0, double second = 0) {
    this->First = first;
    this->Second = second;
}
void Pair::setFirst(double value) {
    this->First = value;
}
void Pair::setSecond(double value) {
    this->Second = value;
}
double Pair::product() {
    return this->First * this->Second;
}
void Pair::display() {
    cout << "First: " << this->First << "\nSecond: " << this->Second << endl;
}