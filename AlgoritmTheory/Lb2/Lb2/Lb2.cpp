#include <iostream>

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
    int iterationNumber = 0;
    for (int i = 0; i < length; i++) {
        for (int j = 0; j < length - 1; j++) {
            cout << "#BUBBLE_SORT check " << j << '(' << array[j] << ") and " << j + 1 << '(' << array[j + 1] << ')' << endl;

            if (array[j] > array[j + 1]) {
                cout << "#BUBBLE_SORT swapping" << endl;

                int tmp = array[j + 1];
                array[j + 1] = array[j];
                array[j] = tmp;
            }
            iterationNumber++;
        }
    }
    cout << "#BUBBLE_SORT Iteration number: " << iterationNumber;
}
void choiceSort(int array[], int length) {
    cout << "#CHOICE_SORT start!" << endl;
    int iterationNumber = 0;
    for (int i = 0; i < length; i++) {
        int min = array[i];
        int minIndex = i;
        bool minFound = false;

        for (int j = i + 1; j < length; j++) {
            cout << "#CHOICE_SORT check min(" << min << ") and " << j << '(' << array[j] << ')' << endl;

            if (min > array[j]) {
                min = array[j];
                minIndex = j;
                minFound = true;
                cout << "#CHOICE_SORT set min index: " << minIndex << "; min value: " << min << endl;
            }
            iterationNumber++;
        }

        if (minFound) {
            array[minIndex] = array[i];
            array[i] = min;
            cout << "#CHOICE_SORT min is founded: " << i << "(" << array[i] << "); " << i << "(" << array[i] << ")" << endl;
        }
    }
    cout << "#CHOICE_SORT Iteration number: " << iterationNumber;
}

int main()
{
    cout << "Input array size: ";
    int arraySize = 0;
    cin >> arraySize;
    int* arrayPtr = new int[arraySize];
    for (int i = 0; i < arraySize; i++) {
        cout << "Input number #" << i + 1 << ": ";
        cin >> arrayPtr[i];
    }
    cout << endl;

    bool isBubbleSort = false;
    cout << "\nChoose sort mode: " << endl;
    cout << "0. Choice sort" << endl;
    cout << "1. Bubble sort" << endl;
    cin >> isBubbleSort;
    if (isBubbleSort) {
        bubbleSort(arrayPtr, arraySize);
    }
    else {
        choiceSort(arrayPtr, arraySize);
    }

    cout << "\nArray: ";
    for (int i = 0; i < arraySize; i++) {
        cout << arrayPtr[i] << "; ";
    }
    
    int searchValue;
    cout << "\n\nSearch value for linear search: ";
    cin >> searchValue;
    cout << "Linear search result: " << linearSearch(arrayPtr, arraySize, searchValue) << endl;

    cout << "\nSearch value for binary search: ";
    cin >> searchValue;
    cout << "Binary search result: " << binarySearch(arrayPtr, arraySize, searchValue) << endl;

    delete[] arrayPtr;

    return 0;
}