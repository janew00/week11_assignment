import java.util.Random;
import java.util.Scanner;

public class job_scheduling {
    public static void main(String[] args) {
        long beforeTime= System.currentTimeMillis();

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
        System.out.print("MAX= "+schedule(n, m, times)+"\n");

        long afterTime=System.currentTimeMillis();
        long DiffTime=(afterTime-beforeTime)/1000;
        System.out.println("시간차이: "+DiffTime);
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
