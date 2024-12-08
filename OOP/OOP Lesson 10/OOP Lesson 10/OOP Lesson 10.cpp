#include <iostream>
#include "TriangleOnApex.h"
#include "TriangleOnSide.h"
#include "RightTriangle.h"

using namespace std;

int main()
{
    Triangle* triangleOnSide1 = new TriangleOnSide(-2, 6, 4);
    Triangle* triangleOnSide2 = new TriangleOnSide(2, 5, 5);
    triangleOnSide1->run();
    triangleOnSide2->run();
    delete triangleOnSide1;
    delete triangleOnSide2;

    cout << endl;
    Triangle* rightTriangle1 = new RightTriangle(-8, 43);
    Triangle* rightTriangle2 = new RightTriangle(3, 4);
    rightTriangle1->run();
    rightTriangle2->run();
    delete rightTriangle1;
    delete rightTriangle2;

    cout << endl;
    Triangle* triangleOnApex1 = new TriangleOnApex(Point(5, 4), Point(8, 3), Point(1, 2));
    triangleOnApex1->run();
    delete triangleOnApex1;

    return 0;
}
