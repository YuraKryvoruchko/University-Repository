#include "Triangle.h"

void Triangle::valid(double AB, double BC, double AC) {
	if(AB <= 0 || BC <= 0 || AC <= 0) {
		throw std::invalid_argument("Incorrect data was entered. Negative side length value\n");
	}
	if (AB + BC <= AC || AB + AC <= BC || BC + AC <= AB) {
		throw std::invalid_argument("Incorrectly entered data. One of the parties is greater than the sum of the other two\n");
	}
}