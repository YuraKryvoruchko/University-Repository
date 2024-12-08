#include "RightTriangle.h"

using namespace std;

const double EPSILON = 1e-9;

RightTriangle::RightTriangle(double AB, double BC)
	: TriangleOnSide(AB, BC, sqrt(pow(AB, 2) + pow(BC, 2)))
{
}
double RightTriangle::inscribedCircleRadius() {
	return (AB + BC - AC) / 2;
}
double RightTriangle::circumscribedCircleRadius() {
	return AC / 2;
}
void RightTriangle::run() {
	try {
		valid(this->AB, this->BC, this->AC);
		cout << "AB = " << this->AB << "; BC = " << this->BC << "; AC = " << this->AC << endl;
		cout << "Average line on AC = " << this->averageLineAC() << endl;
		cout << "Median AC = " << this->medianAC() << endl;
		cout << "Outer circle radius = " << circumscribedCircleRadius() << endl;
		cout << "Inner circle radius = " << inscribedCircleRadius() << endl;
	}
	catch (invalid_argument e) {
		cout << "Run: " << e.what();
	}
}