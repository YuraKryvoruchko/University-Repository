#include <iostream>
#include <string>
#include <vector>

using namespace std;

string encryptCaesar(string& text, int shift) {
    string result = "";
    shift = shift % 26;
    for (int i = 0; i < text.size(); i++) {
        if (isalpha(text[i])) {
            char base = isupper(text[i]) ? 'A' : 'a';
            text[i] = (text[i] - base + shift + 26) % 26 + base;
        }

        result += text[i];
    }
    return result;
}
string decryptCaesar(string& text, int shift) {
    return encryptCaesar(text, -shift);
}

int getCantorNumberFromPair(int x, int y) {
    if (x < 0 || y < 0)
        return -1;

    return ((x + y + 1) * (x + y)) / 2 + y;
}
int getCantorNumberFromVector(vector<int>& numbers) {
    int size = numbers.size();
    if (size < 2)
        return -1;

    int number = getCantorNumberFromPair(numbers[0], numbers[1]);
    for (int i = 2; i < size; i++) {
        number = getCantorNumberFromPair(number, numbers[i]);
    }
    return number;
}
pair<int, int> getPairFromCantorNumber(int number) {
    if (number < 0)
        return pair<int, int>();

    int w = (sqrt(8 * number + 1) - 1) / 2;
    int t = (w * w + w) / 2;
    int y = number - t;
    int x = w - y;
    return pair<int, int>(x, y);
}
vector<int> getVectorFromCantorNumber(int number, int length) {
    if (length <= 0 || number < 0)
        return vector<int>();

    vector<int> numberVector(length);
    for (int i = length - 1; i > 0; i--) {
        pair<int, int> numberPair = getPairFromCantorNumber(number);
        numberVector[i] = numberPair.second;
        number = numberPair.first;
    }
    numberVector[0] = number;
    return numberVector;
}

void caesarExample() {
    cout << "Input text: ";
    string text;
    getline(cin, text);
    cout << "Input shift: ";
    int shift;
    cin >> shift;

    string encryptText = encryptCaesar(text, shift);
    cout << "Encrypt text: " << encryptText << endl;
    cout << "Decrypt text: " << decryptCaesar(encryptText, shift) << endl;
}
void cantorPairExample() {
    int x, y;
    cout << "Enter x: ";
    cin >> x;
    cout << "Enter y: ";
    cin >> y;

    int cantorNumber = getCantorNumberFromPair(x, y);
    cout << "Cantor number from " << x << " and " << y << " = " << cantorNumber << endl;
    pair<int, int> pairNumber = getPairFromCantorNumber(cantorNumber);
    cout << "Pair namuber(X = " << pairNumber.first << "; Y = " << pairNumber.second 
        << ") from canter number: " << cantorNumber << endl;
}
void cantorVectorExample() {
    int length;
    cout << "Input length of vector: ";
    cin >> length;
    vector<int> numberVector(length);
    for (int i = 0; i < length; i++) {
        cout << "Element #" << i + 1 << ": ";
        cin >> numberVector[i];
    }
    int cantorNumber = getCantorNumberFromVector(numberVector);
    cout << "\nCantor number from this vector = " << cantorNumber << endl;
    vector<int> numbers = getVectorFromCantorNumber(cantorNumber, length);
    cout << "Number vector from cantor number " << cantorNumber << ": ";
    for (int i = 0; i < length; i++) {
        cout << numbers[i] << "; ";
    }
    cout << endl;
}

int main()
{
    caesarExample();
    cout << endl;
    cantorPairExample();
    cout << endl;
    cantorVectorExample();

    return 0;
}
