#pragma once
#include "GeometricFigure.h"

class Circle : public GeometricFigure
{
private:
	int _radius;
public:
	Circle(Vector2 position, int radius, int angle, int scale);

	void show() override;
	void hide() override;
};

