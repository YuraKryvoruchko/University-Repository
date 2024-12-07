#pragma once

class Pair {
protected:
    double First;
    double Second;

public:
    Pair(double first, double second);

    void setFirst(double value);
    void setSecond(double value);

    double product();

    virtual void display();
};