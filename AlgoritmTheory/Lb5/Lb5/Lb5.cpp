#include <iostream>

using namespace std;

int factorial(int n) {
    cout << n;
    if (n <= 1) {
        return 1;
    }

    cout << " * ";

    return n * factorial(n - 1);
}
void factorialTest() {
    int n;
    cout << "Input number: ";
    cin >> n;
    cout << "Factorial of " << n << " = ";
    int factorialNumber = factorial(n);
    cout << " = " << factorialNumber << endl;
}

int getFibonaccNumber(int n) {
    if (n <= 1) {
        return n;
    }

    return getFibonaccNumber(n - 1) + getFibonaccNumber(n - 2);
}
void printFibonacciSequenceTest() {
    int n;
    cout << "Input fibonacci number position: ";
    cin >> n;

    cout << "Fibonacci number: " << getFibonaccNumber(n);
}

int main()
{
    printFibonacciSequenceTest();

    return 0;
}
