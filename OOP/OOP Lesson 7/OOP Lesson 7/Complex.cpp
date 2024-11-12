#include "Complex.h"

using namespace std;

Complex::Complex() {
	this->real = 0;
	this->imaginary = 0;
}
Complex::Complex(double real, double imaginary) {
	this->real = real;
	this->imaginary = imaginary;
}
string Complex::toString() {
	string result = to_string(this->real);
	if (this->imaginary < 0) {
		result += " - ";
	}
	else {
		result += " + ";
	}

	result += to_string(abs(this->imaginary)) + "i";
	return result;
}

const char* CustomDivisionByZeroException::what() const noexcept {
	return "Custom Division By Zero Exception";
}

ComplexDivisionByZeroException::ComplexDivisionByZeroException(Complex a, Complex b) : _a(a), _b(b) {
	_message = "EXCEPTION: Cannot divide (" + _a.toString() + ") by (" + _b.toString() + ")!";
}
const char* ComplexDivisionByZeroException::what() const noexcept {
	return _message.c_str();
}
