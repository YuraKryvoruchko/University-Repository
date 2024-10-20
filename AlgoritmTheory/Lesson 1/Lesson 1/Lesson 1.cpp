#include <iostream>

using namespace std;

int main()
{
    float a, b, c;
    cout << "a = ";
    cin >> a;
    cout << "b = ";
    cin >> b;
    cout << "c = ";
    cin >> c;

    float sum = a + b + c;
    float product = a * b * c;
    float mediumArefmatic = sum / 3;

    cout << "\nSum: " << sum << endl;
    cout << "Product: " << product << endl;
    cout << "Medium arefmatic: " << mediumArefmatic << endl;

    return 0;
}