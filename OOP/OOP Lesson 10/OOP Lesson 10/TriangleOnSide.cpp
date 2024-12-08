#include "TriangleOnSide.h"

using namespace std;

TriangleOnSide::TriangleOnSide(double AB, double BC, double AC) throw(std::invalid_argument) {
	try {
		valid(AB, BC, AC);
	} 
	catch (invalid_argument e) {
		cout << "Construct: " << e.what();
	}
	this->AB = AB;
	this->BC = BC;
	this->AC = AC;
}
double TriangleOnSide::medianAC() {
	return 0.5 * sqrt(2 * AB * AB + 2 * BC * BC - AC * AC);
}
double TriangleOnSide::averageLineAC() {
	return AC / 2;
}
void TriangleOnSide::run() {
	try {
		valid(this->AB, this->BC, this->AC);
		cout << "AB = " << this->AB << "; BC = " << this->BC << "; AC = " << this->AC << endl;
		cout << "Average line on AC = " << averageLineAC() << endl;
		cout << "Median AC = " << medianAC() << endl;
	}
	catch (invalid_argument e) {
		cout << "Run: " << e.what();
	}
}