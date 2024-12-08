#include "Rightangled.h"
#include <iostream>

using namespace std;

Rightangled::Rightangled(double cathetus1, double cathetus2) : Pair(cathetus1, cathetus2) {}
double Rightangled::hypotenuse() {
    return sqrt(this->First * this->First + this->Second * this->Second);
}
double Rightangled::area() {
    return (this->First * this->Second) / 2;
}
void Rightangled::display() {
    cout << "Rightangled triangle: Cathets (" << this->First << ", " << this->Second << ")" << endl;
    cout << "Hypotenuse: " << hypotenuse() << endl;
    cout << "Area: " << area() << endl;
}
