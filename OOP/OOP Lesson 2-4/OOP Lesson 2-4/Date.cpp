#include "Date.h"
#include <iostream>
#include <string>

using namespace std;

Date::Date() {
	this->_year = 1;
	this->_month = 1;
	this->_day = 1;
}
Date::Date(unsigned int year, unsigned int month, unsigned int day) {
	SetYear(year);
	SetMonth(month);
	SetDay(day);
}
Date::Date(string stringDate) {
	size_t length = stringDate.length();
	bool isYearSetup = false;
	int startIndex = 0;
	for (int i = 0; i < length; i++) {
		if (stringDate[i] == '.') {
			if (!isYearSetup) {
				SetYear(stoi(stringDate.substr(startIndex, i)));
				isYearSetup = true;
				startIndex = i + 1;
			}
			else {
				SetMonth(stoi(stringDate.substr(startIndex, i)));
				startIndex = i + 1;
				break;
			}
		}
	}
	SetDay(stoi(stringDate.substr(startIndex, length - 1)));
}

void Date::Init(Date date) {
	this->_year = date._year;
	this->_month = date._month;
	this->_day = date._day;
}

void Date::Read() {
	cout << "\nInput date as yyyy.mm.dd: ";
	string date;
	cin >> date;
	this->Init(date);
}

void Date::Display() {
	cout << "Date: " << this->ToString();
}

string Date::ToString()
{
	return to_string(_year) + '.' + to_string(_month) + '.' + to_string(_day);
}

void Date::SetDay(unsigned int day) {
	if (day == 0 || day > GetDaysCountInMonth(this->_month, this->_year)) {
		throw invalid_argument("The number of days cannot be zero or more than"
			+ to_string(GetDaysCountInMonth(this->_month, this->_year)) + " days in "
			+ to_string(this->_month) + "-month period!");
	}

	this->_day = day;
}

void Date::SetMonth(unsigned int month) {
	if (month == 0 || month > 12) {
		throw invalid_argument("The number of months cannot be zero or more than 12");
	}

	this->_month = month;
}

void Date::SetYear(unsigned int year) {
	this->_year = year;
}

unsigned int Date::GetDay() {
	return this->_day;
}

unsigned int Date::GetMonth() {
	return this->_month;
}

unsigned int Date::GetYear() {
	return this->_year;
}

void Date::AddDays(unsigned int days) {
	if (days < 0)
		return;

	unsigned int totalDays = this->_day + days;
	unsigned int month = this->_month;
	unsigned int year = this->_year;

	while (totalDays > GetDaysCountInMonth(month, year)) {
		totalDays -= GetDaysCountInMonth(month, year);
		month++;
		if (month > 12u) {
			month = 1u;
			year++;
		}
	}

	SetYear(year);
	SetMonth(month);
	SetDay(totalDays);
}

void Date::RemoveDays(unsigned int days) {
	if (days < 0)
		return;

	unsigned int month = this->_month;
	unsigned int year = this->_year;

	while (this->_day <= days) {
		days -= this->_day;
		month--;
		if (month == 0u) {
			year--;
			this->_year = year;
			month = 12u;
		}
		this->_month = month;
		SetDay(this->GetDaysCountInMonth(month, year));
	}
	SetDay(this->_day - days);

	SetYear(year);
	SetMonth(month);
}

bool Date::IsEquals(Date date) {
	return this->_year == date._year && this->_month == date._month && this->_day == date._day;
}

bool Date::IsBefore(Date date) {
	return this->_year < date._year || (this->_year == date._year && this->_month < date._month
		|| (this->_month == date._month && this->_day < date._day));
}

bool Date::IsAfter(Date date) {
	return this->_year > date._year || (this->_year == date._year && this->_month > date._month
		|| (this->_month == date._month && this->_day > date._day));
}

bool Date::IsLeapYear(unsigned int year)
{
	if (year % 100u == 0u)
		return year % 400u == 0u;

	return year % 4u == 0u;
}

unsigned int Date::GetNumberOfDaysBetweenDates(Date secondDate) {
	if (IsEquals(secondDate))
		return 0;

	Date firstDate = *this;
	if (IsAfter(secondDate)) {
		firstDate = secondDate;
		secondDate = *this;
	}

	unsigned int totalDays = 0;
	while (firstDate.GetYear() < secondDate.GetYear()) {
		if (secondDate.IsLeapYear(secondDate.GetYear())) {
			totalDays += 366u;
			secondDate.RemoveDays(366u);
		}
		else {
			totalDays += 365u;
			secondDate.RemoveDays(365u);
		}
	}

	while (firstDate.GetMonth() != secondDate.GetMonth()) {
		if (firstDate.GetMonth() < secondDate.GetMonth()) {
			unsigned int removedDays = secondDate.GetDaysCountInMonth(secondDate.GetMonth(), secondDate.GetYear());
			totalDays += removedDays;
			secondDate.RemoveDays(removedDays);
		}
		else {
			unsigned int addedDays = secondDate.GetDaysCountInMonth(secondDate.GetMonth(), secondDate.GetYear());
			totalDays -= addedDays;
			secondDate.AddDays(addedDays);
		}
	}

	if (firstDate.GetDay() < secondDate.GetDay()) {
		totalDays += secondDate._day - firstDate._day;
	}
	else {
		totalDays -= firstDate._day - secondDate._day;
	}

	return totalDays;
}

unsigned int Date::GetDaysCountInMonth(unsigned int month, unsigned int year)
{
	switch (month) {
	case 1u: case 3u: case 5u: case 7u: case 8u: case 10u: case 12u:
		return 31u;
	case 4u: case 6u: case 9u: case 11u:
		return 30u;
	case 2u:
		if (IsLeapYear(year))
			return 29u;
		else
			return 28u;
	default:
		throw invalid_argument("There is no such month: " + to_string(month) + '!');
	}
}