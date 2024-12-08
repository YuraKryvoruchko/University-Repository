#include <iostream>
#include <iostream>
#include <string>

#pragma once

using namespace std;

class Date {
    unsigned int _year;
    unsigned int _month;
    unsigned int _day;

    unsigned int GetDaysCountInMonth(unsigned int month, unsigned int year);

public:
    Date();
    Date(unsigned int year, unsigned int month, unsigned int day);
    Date(string stringDate);
    void Init(Date date);
    void Read();
    void Display();
    string ToString();
    void AddDays(unsigned int days);
    void RemoveDays(unsigned int days);
    void SetDay(unsigned int day);
    void SetMonth(unsigned int month);
    void SetYear(unsigned int year);
    unsigned int GetDay();
    unsigned int GetMonth();
    unsigned int GetYear();
    unsigned int GetNumberOfDaysBetweenDates(Date secondDate);
    bool IsEquals(Date date);
    bool IsBefore(Date date);
    bool IsAfter(Date date);
    bool IsLeapYear(unsigned int year);

    bool operator >(const Date& other);
    bool operator <(const Date& other);
    bool operator ==(const Date& other);
    bool operator >=(const Date& other);
    bool operator <=(const Date& other);
};
