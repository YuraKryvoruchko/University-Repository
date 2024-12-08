#pragma once

#include <iostream>
#include <string>

using namespace std;

struct Complex {
	double real;
	double imaginary;

    Complex();
	Complex(double real, double imaginary);
	string toString();
};


class DivisionByZeroException {
};

class CustomDivisionByZeroException {
public:
	const char* what() const noexcept;
};

class ComplexDivisionByZeroException : public exception {
private:
    Complex _a, _b;
    string _message;
public:
	ComplexDivisionByZeroException(Complex a, Complex b);

	const char* what() const noexcept override;
};
