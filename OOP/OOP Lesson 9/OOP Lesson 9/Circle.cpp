#include "Circle.h"
#include <iostream>
#include <vector>

using namespace std;

Circle::Circle(Vector2 position, int radius, int angle, int scale) 
	: GeometricFigure(position, angle, scale) 
{
	this->_radius = radius;
}
void Circle::show() {
	vector<vector<char>> screen(25, vector<char>(80, ' '));
	double angleRad = this->Angle * PI / 180.0;

	int centerX = this->Center.X;
	int centerY = 25 - this->Center.Y;
	if (centerX < 80 && centerX >= 0 && centerY < 25 && centerY >= 0)
		screen[centerY][centerX] = '*';

	int scaledRadius = this->_radius * this->Scale;
	for (int i = 1; i < scaledRadius; i++) {
		int x = round(centerX + i * cos(angleRad));
		int y = round(centerY + i * sin(angleRad));
		if (x < 80 && x >= 0 && y < 25 && y >= 0)
			screen[y][x] = '*';
	}
	for (int y = -scaledRadius; y <= scaledRadius; y++) {
		for (int x = -scaledRadius; x <= scaledRadius; x++) {
			if (round(sqrt(x * x + y * y)) == scaledRadius) {
				if (x + centerX < 80 && x + centerX >= 0 && y + centerY < 25 && y + centerY >= 0) {
					screen[y + centerY][x + centerX] = '*';
				}
			}
		}
	}

	for (int i = 0; i < 25; i++) {
		for (int j = 0; j < 80; j++) {
			cout << screen[i][j];
		}
		cout << '\n';
	}
}
void Circle::hide() {
	system("cls");
}