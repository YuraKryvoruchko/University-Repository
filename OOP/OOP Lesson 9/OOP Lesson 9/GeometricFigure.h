#pragma once
#include "Vector2.h"

const double PI = 3.141592653589793238463;

class GeometricFigure
{
protected:
	Vector2 Center;
	int Angle;
	int Scale;
public:
	GeometricFigure(Vector2 position, int angle, int scale);

	void rotate(int degress);
	void replace(Vector2 position);

	void virtual show() = 0;
	void virtual hide() = 0;
};

