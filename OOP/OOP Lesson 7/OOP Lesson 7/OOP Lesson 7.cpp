#include <iostream>
#include "Complex.h"

using namespace std;

Complex divideWithoutSpecification(const Complex& a, const Complex& b) {
    double denominator = b.real * b.real + b.imaginary * b.imaginary;
    if (denominator == 0) {
        throw DivisionByZeroException();
    }

    double realPart = (a.real * b.real + a.imaginary * b.imaginary) / denominator;
    double imagPart = (a.imaginary * b.real - a.real * b.imaginary) / denominator;

    return Complex(realPart, imagPart);
}
Complex divideWithSpecification(const Complex& a, const Complex& b) {
    double denominator = b.real * b.real + b.imaginary * b.imaginary;
    if (denominator == 0) {
        throw CustomDivisionByZeroException();
    }

    double realPart = (a.real * b.real + a.imaginary * b.imaginary) / denominator;
    double imagPart = (a.imaginary * b.real - a.real * b.imaginary) / denominator;

    return Complex(realPart, imagPart);
}
Complex divideWithStandardException(const Complex& a, const Complex& b) {
    double denominator = b.real * b.real + b.imaginary * b.imaginary;
    if (denominator == 0) {
        throw invalid_argument("Division by zero when dividing complex numbers!");
    }

    double realPart = (a.real * b.real + a.imaginary * b.imaginary) / denominator;
    double imagPart = (a.imaginary * b.real - a.real * b.imaginary) / denominator;

    return Complex(realPart, imagPart);
}
Complex divideWithCustomException(const Complex& a, const Complex& b) {
    double denominator = b.real * b.real + b.imaginary * b.imaginary;
    if (denominator == 0) {
        throw ComplexDivisionByZeroException(a, b);
    }

    double realPart = (a.real * b.real + a.imaginary * b.imaginary) / denominator;
    double imagPart = (a.imaginary * b.real - a.real * b.imaginary) / denominator;

    return Complex(realPart, imagPart);
}

int main()
{
    Complex numbers[2], result;
    for (int i = 0; i < 2; i++) {
        cout << i + 1 << "# complex number:" << endl;
        cout << "Real part: ";
        cin >> numbers[i].real;
        cout << "Imaginary part: ";
        cin >> numbers[i].imaginary;
        cout << endl;
    }

    int functionNumber;
    cout << "Use function: " << endl;
    cout << "1. Without specifying exceptions;" << endl;
    cout << "2. With the throw() specification;" << endl;
    cout << "3. With a specific specification with a corresponding standard exception;" << endl;
    cout << "4. With a specification with its own implemented exception." << endl;
    cout << "Number: ";
    cin >> functionNumber;

    if (functionNumber == 1) {
        result = divideWithoutSpecification(numbers[0], numbers[1]);
    }
    else {
        try {
            switch (functionNumber)
            {
            case 2:
                result = divideWithSpecification(numbers[0], numbers[1]);
                break;
            case 3:
                result = divideWithStandardException(numbers[0], numbers[1]);
                break;
            case 4:
                result = divideWithCustomException(numbers[0], numbers[1]);
                break;
            default:
                cout << "Incorrect number!";
            }
        }
        catch (const CustomDivisionByZeroException& exception) {
            cout << "Custom exception: " << exception.what() << endl;
        }
        catch (const ComplexDivisionByZeroException& exception) {
            cout << "Inherited exception: " << exception.what() << endl;
        }
        catch (const invalid_argument& exception) {
            cout << "Standard exception: " << exception.what() << endl;
        }
    }

    cout << "\nResult: " << result.toString() << endl;
        
    return 0;
}
