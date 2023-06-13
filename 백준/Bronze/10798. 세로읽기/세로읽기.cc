#include <iostream>
using namespace std;

char a[5][16]; 

int main(){
  for(int i=0; i<5; i++)  
    cin>>a[i];
  
  for(int k=0; k<15; k++)  
  {
    for(int i=0; i<5; i++)  
    {
      if(a[i][k] != '\0')  
        cout<<a[i][k];
    }
  }
}