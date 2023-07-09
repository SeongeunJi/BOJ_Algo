#include <iostream>

using namespace std;

int a, x, y;
int maxi = -1;


int main() {

	for (int i = 1; i <= 9; i++) {
		for (int k = 1; k <= 9; k++) {
			cin >> a;

			if (maxi <= a) {
				maxi = a;
				x = i;
				y = k;
			}
		}
	}

	cout << maxi << '\n';
	cout << x << ' ' << y;

	return 0;
}