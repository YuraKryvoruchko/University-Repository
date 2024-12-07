#include "TriangleOnApex.h"

using namespace std;

TriangleOnApex::TriangleOnApex(Point a, Point b, Point c) {
	this->_a = a;
	this->_b = b;
	this->_c = c;
}
Point TriangleOnApex::getA() {
	return this->_a;
}
Point TriangleOnApex::getB() {
	return this->_b;
}
Point TriangleOnApex::getC() {
	return this->_c;
}

double TriangleOnApex::calculate(Point a, Point c) {
	return sqrt(pow(a.X - c.X, 2) + pow(a.Y - c.Y, 2));
}

double TriangleOnApex::medianAC() {
	double AB = calculate(getA(), getB());
	double BC = calculate(getB(), getC());
	double AC = calculate(getA(), getC());
	return 0.5 * sqrt(2 * AB * AB + 2 * BC * BC - AC * AC);
}

double TriangleOnApex::averageLineAC() {
	return calculate(this->_a, this->_c) / 2;
}

void TriangleOnApex::run() {
	cout << "Apexes: A = (" << getA().X << "; " << getA().Y << "); B = (" 
		<< getB().X << "; " << getB().Y << ") ; C = (" << getC().X << "; " << getC().Y << ");" << endl;
	cout << "Average line on AC = " << averageLineAC() << endl;
	cout << "Median AC = " << medianAC() << endl;
}
