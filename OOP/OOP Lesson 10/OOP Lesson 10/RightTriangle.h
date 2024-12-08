#pragma once
#include "TriangleOnSide.h"

class RightTriangle : public TriangleOnSide
{
public:
	RightTriangle(double AB, double BC) throw(std::invalid_argument);
	double inscribedCircleRadius();
	double circumscribedCircleRadius();
	void run() override;
};

