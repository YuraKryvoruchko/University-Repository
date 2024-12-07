#include <iostream>
#include "Rightangled.h"

using namespace std;

Pair processPair(Pair& pair) {
    pair.display();
    return pair;
}

int main() {
    Pair pair(5, 6);
    cout << "Pair 1: " << endl;
    processPair(pair);
    cout << "Product: " << pair.product() << endl;

    cout << "\nRightangled: " << endl;
    Rightangled rightangled(3, 4);
    Pair pair2 = processPair(rightangled);

    cout << "\nPair2: " << endl;
    processPair(pair2);

    return 0;
}
