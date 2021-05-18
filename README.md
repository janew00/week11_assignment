# 202001626 김정윤
## 11주차 과제 - 작업 스케줄링 (Job Scheduling)

## 1. 작업 스케줄링
* ### 각 작업의 수행 시간이 다른 n개의 작업과 동일한 m개의 기계가 주어졌을 때, 모든 작업이 가장 빨리 종료되도록 기계에 작업을 배정하는 문제   <br>
  
* 그리디 알고리즘 사용
* 작업 하나에 배정된 기계는 연속적으로 수행.
* 기계는 한 번에 하나의 작업만 수행.     <br>    

       
* ### 예시: **4개**의 기계와 수행 시간이 [5, 2, 4, 3, 4, 7, 9, 2, 4, 1]인 **10개**의 작업       
<br> 


<img src= "https://user-images.githubusercontent.com/80301788/118478858-53b91200-b74b-11eb-9162-d64422d1de88.png">   <br>    
<br>
<img src= "https://user-images.githubusercontent.com/80301788/118479185-ad214100-b74b-11eb-9230-b2abf3e90be2.png" width="500">    

## 2. 코드   

* ### 작업의 개수 입력해야 함 (n = 4, 8, 16)    
* ### 기계의 개수 m = 2 
* ### 작업 시간 times[ ] 랜덤 함수로 생성 (10 이내 [1~9])

* L[ ] = 해당 기계에 배정된 마지막 작업의 종료 시간
* max = 가장 늦은 작업 종료 시간

```
import java.util.Random;
import java.util.Scanner;

public class job_scheduling {
public static void main(String[] args) {

        Random random = new Random();
        Scanner sc=new Scanner(System.in);

        System.out.print("작업의 개수 : ");
        int n=sc.nextInt();
        System.out.print("기계의 개수 : ");
        int m = 2;
        int[] times = new int[n];

        System.out.print("작업 시간 : ");
        for (int i = 0; i < n; i++) {
            times[i] = random.nextInt(9)+1 ;
            System.out.printf("%d ", times[i]);
        }
        System.out.println();
        System.out.println(schedule(n, m, times));
    }

    public static int schedule(int n, int m, int[] times) {
        int[] L = new int[m]; 
        
        for (int j = 0; j < m; j++) {
            L[j] = 0;
        }
        
        for (int i = 0; i < n; i++) {
            int min = 0;
            for (int j = 1; j < m; j++) {
                if (L[j] < L[min]) {
                    min = j;
                }
            }
            L[min] = L[min] + times[i];
        }
        
        int max = L[0];
        for (int i = 1; i < m; i++) {
            if (L[i] > max) {
                max = L[i];
            }
        }
        return max;
    }
}   
```    

## 3. 시간 복잡도   

* n개의 작업을 배정하는 과정에서 가장 빨리 끝나는 기계에 배정하는 알고리즘
* for 루프가 (m-1)번 수행
 #### ==> n*O(m) 
```  
for (int i = 0; i < n; i++) {
            int min = 0;
            for (int j = 1; j < m; j++) {
                if (L[j] < L[min]) {
                    min = j;
                }
            }
            L[min] = L[min] + times[i];
        } 
``` 
* 모든 작업이 배정되도록 L[ ]을 확인해야 함
 #### ==> O(m)

```
int max = L[0];
        for (int i = 1; i < m; i++) {
            if (L[i] > max) {
                max = L[i];
            }
        }
        return max;
``` 

### ===> n*O(m) + O(m) = _**O(nm)**_

## 4. 근사 비율
* 근사해의 값과 최적해의 값의 비율
* 1.0의 값에 가까울수록 정확도가 높은 알고리즘
* 근사 비율을 알기 위해서는 최적해를 알아야 한다는 모순이 생기므로 _**간접적인**_ 최적해를 찾아 이로 대신함.


* 위에서 사용한 예시를 가지고와서 설명
* 알고리즘의 근사해를 OPT'라 하고, 최적해를 OPT라고 하면 근사해는 최적해의 2배 이하(OPT'<=OPT)   
->아래와 같이 증명됨.
  
* 1을 통해 T <= T' 도 알게 됨.

 <img src= "https://user-images.githubusercontent.com/80301788/118501920-2b89dd00-b764-11eb-9a6d-b3741adc8b67.png" width="500">   <br>    


* 근사해인 OPT'는 작업이 끝난 13
* ### 가장 마지막에 배정되는 작업을 제외한 나머지 작업에 대한 평균 종료 시간    
  #### -> (3+7+4+9+2+4+4+5+2+1)-1 / 4 = 10   = T '
* ### 마지막 작업이 배정 전의 해당 기계 작업 종료 시간 
  #### -> 7 = T
### ===> T = 7 *<=* T' = 10
<img src= "https://user-images.githubusercontent.com/80301788/118501046-50318500-b763-11eb-835e-1cf77eb11c30.png" width="600">   <br>    














