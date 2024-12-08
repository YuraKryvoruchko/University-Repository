#include <iostream>

using namespace std;

int factorial(int n) {
    cout << n;
    if (n == 1) {
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

void printFibonacciSequence(int firstNumber, int secondNumber, int n) {
    if (n < 1) {
        return;
    }

    cout << firstNumber << "; ";

    int currentNumber = firstNumber + secondNumber;
    printFibonacciSequence(currentNumber, firstNumber, n - 1);
}
void printFibonacciSequenceTest() {
    int n;
    cout << "Input fibonacci sequence lenght: ";
    cin >> n;

    printFibonacciSequence(0, 1, n);
}

int main()
{
    printFibonacciSequenceTest();

    return 0;
}
