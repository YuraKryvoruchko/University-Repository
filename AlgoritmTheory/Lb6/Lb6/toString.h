#pragma once
#include <iostream>
#include <string>
#include <sstream>

template <typename T>
std::string toString(const T& obj) {
	std::ostringstream oss;
	oss << obj;
	return oss.str();
}