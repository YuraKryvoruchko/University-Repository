#include "GeometricFigure.h"

GeometricFigure::GeometricFigure(Vector2 position, int angle, int scale) {
	this->Center = position;
	this->Angle = angle;
	this->Scale = scale;
}
void GeometricFigure::replace(Vector2 position) {
	this->Center = position;
}
void GeometricFigure::rotate(int degress) {
	this->Angle = degress;
}