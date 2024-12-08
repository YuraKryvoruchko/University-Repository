#pragma once
#include<iostream>

class Triangle
{
protected:
	void valid(double AB, double BC, double AC) throw(std::invalid_argument);
public:
	double virtual medianAC() = 0;
	double virtual averageLineAC() = 0;
	void virtual run() = 0;
};

