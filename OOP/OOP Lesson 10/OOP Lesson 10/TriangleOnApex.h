#pragma once
#include "Triangle.h"
#include "Point.h"

class TriangleOnApex : public Triangle
{
private:
	Point _a, _b, _c;
	double calculate(Point a, Point c);
public:
	TriangleOnApex(Point a, Point b, Point c);
	Point getA();
	Point getB();
	Point getC();
	double medianAC() override;
	double averageLineAC() override;
	void run() override;
};

