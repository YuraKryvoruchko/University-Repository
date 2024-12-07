#pragma once
#include "Pair.h"

class Rightangled : public Pair {
public:
    Rightangled(double cathetus1 = 0, double cathetus2 = 0);

    double hypotenuse();
    double area();

    void display() override;
};