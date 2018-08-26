#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <bitset>

using namespace std;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
int main()
{
    vector<int> counts = vector<int>();
    string MESSAGE;
    getline(cin, MESSAGE);
    bitset<7> bs;
    for(auto iter = begin(MESSAGE); iter != end(MESSAGE); ++iter) {
        int c = *iter;
        bs = bitset<7>(c);
        for(int i = 6; i >= 0; --i) {
            bool b = bs[i];
            if(counts.size() == 0) {
                if(b) {
                    counts.push_back(0);
                    counts.push_back(1);
                } else {
                    counts.push_back(1);
                }
            } else {
                if(b) {
                    if((counts.size() & 1) == 0) {
                        ++counts[counts.size() - 1];
                    } else {
                        counts.push_back(1);
                    }
                } else {
                    if((counts.size() & 1) == 1) {
                        ++counts[counts.size() - 1];
                    } else {
                        counts.push_back(1);
                    }
                }
            }
        }
    }

    for(int i = 0; i < counts.size(); ++i) {
        if(counts[i] != 0) {
            cout << "0";
            if((i & 1) == 0) {
                cout << "0";
            }
            cout << " ";
            for(int j = 0; j < counts[i]; ++j) {
                cout << "0";
            }
            if(i != counts.size() - 1) {
                cout << " ";
            }
        }
    }
    cout << endl;
}
