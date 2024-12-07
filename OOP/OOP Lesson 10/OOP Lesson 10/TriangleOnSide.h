#pragma once
#include "Triangle.h"

class TriangleOnSide : public Triangle
{
protected:
	double AB, BC, AC;
public:
	TriangleOnSide(double AB, double BC, double AC) throw(std::invalid_argument);
	double medianAC() override;
	double averageLineAC() override;
	virtual void run() override;
};

