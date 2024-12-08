#include <chrono>
#include <iostream>
#include <thread>
#include "Circle.h"

using namespace std;

int main()
{
    Vector2 position;
    int angle, scale, radius;
    cout << "Set position:" << endl;
    cout << "X: ";
    cin >> position.X;
    cout << "Y: ";
    cin >> position.Y;
    cout << "Set angle: ";
    cin >> angle;
    cout << "Set scale: ";
    cin >> scale;
    cout << "Set radius: ";
    cin >> radius;

    Circle circle(position, radius, angle, scale);
    while (true) {
        circle.show();
        cout << "1. Move;\n2. Rotate;\n3. Exit\n";
        int commandNumber = 0;
        cin >> commandNumber;
        if (commandNumber == 1) {
            cout << "X: ";
            cin >> position.X;
            cout << "Y: ";
            cin >> position.Y;
            circle.replace(position);
        }
        else if (commandNumber == 2) {
            cout << "Set angle: ";
            cin >> angle;
            circle.rotate(angle);
        }
        else {
            break;
        }
        circle.hide();
    }

    return 0;
}
