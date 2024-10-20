#include <iostream>
#include <cstdlib>

using namespace std;

int linearSearch(int array[], int length, int value) {
    for (int i = 0; i < length; i++) {
        cout << "LINEAR_SEARCH current index: " << i << endl;
        if (array[i] == value) {
            return i;
        }
    }

    return -1;
}
int binarySearch(int array[], int length, int value) {
    int low = 0;
    int high = length - 1;
    while (low <= high) {
        int index = (low + high) / 2;
        cout << "#BINARY_SEARCH current index: " << index << endl;

        if (array[index] > value) {
            high = index - 1;
        }
        else if (array[index] < value) {
            low = index + 1;
        }
        else {
            return index;
        }
    }

    return -1;
}

void bubbleSort(int array[], int length) {
    cout << "#BUBBLE_SORT start!" << endl;
    for (int i = 0; i < length; i++) {
        for (int j = 0; j < length - 1; j++) {
            cout << "#BUBBLE_SORT check " << j << '(' << array[j] << ") and " 
                << j + 1 << '(' << array[j + 1] << ')' << endl;

            if (array[j] > array[j + 1]) {
                cout << "#BUBBLE_SORT swapping" << endl;

                int tmp = array[j + 1];
                array[j + 1] = array[j];
                array[j] = tmp;
            }
        }
    }
}
void choiceSort(int array[], int length) {
    cout << "#CHOICE_SORT start!" << endl;
    for (int i = 0; i < length; i++) {
        int min = array[i];
        int minIndex = i;
        bool minFound = false;

        for (int j = i + 1; j < length; j++) {
            if (min > array[j]) {
                min = array[j];
                minIndex = j;
                minFound = true;
            }
        }

        if (minFound) {
            array[minIndex] = array[i];
            array[i] = min;
        }
    }
}

int const ARRAY_SIZE = 15;

int main()
{
    srand(static_cast<unsigned int>(time(0)));

    int test[ARRAY_SIZE] = { 10, 20, 34, 24, 7, 100, 443, 84, 124, 53, 15, 21, 96, 31, 102 };
    cout << "Array: ";
    for (int i = 0; i < ARRAY_SIZE; i++) {
        cout << test[i] << "; ";
    }
    cout << endl;

    bool isBubbleSort = false;
    cout << "\nChoose sort mode: " << endl;
    cout << "0. Choice sort" << endl;
    cout << "1. Bubble sort" << endl;
    cin >> isBubbleSort;
    if (isBubbleSort) {
        bubbleSort(test, ARRAY_SIZE);
    }
    else {
        choiceSort(test, ARRAY_SIZE);
    }
    cout << "\nArray: ";
    for (int i = 0; i < ARRAY_SIZE; i++) {
        cout << test[i] << "; ";
    }
    
    int value;
    cout << "\n\nSearch value for linear search: ";
    cin >> value;
    cout << "Linear search result: " << linearSearch(test, ARRAY_SIZE, value) << endl;

    cout << "\nSearch value for binary search: ";
    cin >> value;
    cout << "Binary search result: " << binarySearch(test, ARRAY_SIZE, value) << endl;

    return 0;
}