import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class N1 extends main {
    public void n1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размер матрицы");
        int size = sc.nextInt();
        int arr[][] = new int[size][size];
        fillRandom(arr, size);//заполнение случ
        printArr(arr);
        moveToLeft(arr);//циклич сдвиг
        printArr(arr);
        moveToRight(arr);
        printArr(arr);
        moveToUp(arr);
        printArr(arr);
        moveToDown(arr);
        printArr(arr);
        System.out.println("Нули сдвинуты вправо:");
        zeroToRight(arr);//нули в строках-после всех остальных
        printArr(arr);
        System.out.println();
        inDecrease(arr);//макс последовательность возрастающих/убыв элементов
    }

    public static void fillRandom(int a[][], int d) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                a[j][i] = random.nextInt(2 * d + 1) - d;
            }
        }
    }

    public static void printArr(int[][] a) {

        for(int[]x:a){
            for(int z:x){
                System.out.print(z+"  ");
            }  System.out.println();
        }

    }
    /*Выполнить циклический сдвиг заданной матрицы на k позиций вправо
    (влево, вверх, вниз)*/
    public static void moveToLeft(int[][] b) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите сдвиг влево");
        int step = sc.nextInt();
        if (step > b.length || step < 1) {
            System.out.println("Сдвиг невозможен");
            return;
        }
        int buff, i, j;
        for (int r = 0; r < step; r++) {
            for (i = 0; i < b.length; i++) {
                buff = b[i][0];
                for (j = 0; j < b.length - 1; j++) {
                    b[i][j] = b[i][j + 1];
                }
                b[i][j] = buff;
            }
        }
    }

    public static void moveToRight(int[][] b) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите сдвиг вправо");
        int step = sc.nextInt();
        if (step > b.length || step < 1) {
            System.out.println("Сдвиг невозможен");
            return;
        }
        int buff, i, j;
        for (int r = 0; r < step; r++) {
            for (i = 0; i < b.length; i++) {
                buff = b[i][b.length - 1];
                for (j = b.length - 1; j > 0; j--) {
                    b[i][j] = b[i][j - 1];
                }
                b[i][j] = buff;
            }
        }
    }

    public static void moveToDown(int[][] b) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите сдвиг вниз");
        int step = sc.nextInt();
        if (step > b.length || step < 1) {
            System.out.println("Сдвиг невозможен");
            return;
        }
        int buff, i, j;
        for (int r = 0; r < step; r++) {
            for (i = 0; i < b.length; i++) {
                buff = b[b.length - 1][i];
                for (j = b.length - 1; j > 0; j--) {
                    b[j][i] = b[j - 1][i];
                }
                b[j][i] = buff;
            }
        }
    }

    public static void moveToUp(int[][] b) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите сдвиг вверх");
        int step = sc.nextInt();
        if (step > b.length || step < 1) {
            System.out.println("Сдвиг невозможен");
            return;
        }
        int buff, i, j;
        for (int r = 0; r < step; r++) {
            for (i = 0; i < b.length; i++) {
                buff = b[0][i];
                for (j = 0; j < b.length - 1; j++) {
                    b[j][i] = b[j + 1][i];
                }
                b[j][i] = buff;
            }
        }
    }
    //Преобразовать строки матрицы таким образом, чтобы элементы, равные нулю, располагались после всех остальных
    public static void zeroToRight(int[][] b) {
        for (int i = 0; i < b.length; i++) {
            int n = 0;
            int[] c = new int[b.length];
            for (int j = 0; j < b.length; j++) {
                if (b[i][j] != 0) {
                    c[n++] = b[i][j];
                }
            }
            b[i] = Arrays.copyOf(c, c.length);
        }
    }
    /*Найти и вывести наибольшее число возрастающих (убывающих) элемен-
    тов матрицы, идущих подряд*/
    public static void inDecrease(int[][] b) {
        int[] f = new int[b.length * b.length];//преобразую в одномерный массив
        int c = 0;int r=0;int max=0;int h=0;int m=0;int min=0;int h1=0;
        for(int[]x:b){
            for(int z:x){
                f[c++] =z;
            }
        }
        for (int g=0;g<f.length-1;g++){
            if(f[g]<f[g+1]){//если последующий элемент больше
                r+=1;
                if(r>max){max=r;h=g+1;}//запоминаем количество возрастаний и индекс последнего возрастающего элем
            }else{r=0;}//возрастание прервалась
            if(f[g]>f[g+1]){
                m+=1;
                if(m>min){min=m;h1=g+1;}//запоминаем количество возрастаний и индекс последнего убыв элем
            } else{m=0;}// убывание прервалась
        }
        System.out.println("Максимальная последовательность возрастающих чисел:");
        int[]l=new int[max+1];
        System.arraycopy(f,(h-max),l,0,(max+1));//копирую последовательность возр элем в новый массив
        System.out.println(Arrays.toString(l)+" "+(max+1));
        System.out.println("Максимальная последовательность убывающих чисел:");
        int[]t=new int[min+1];
        System.arraycopy(f,(h1-min),t,0,(min+1));//копирую последовательность убыв элем в новый массив
        System.out.println(Arrays.toString(t)+" "+(min+1));
    }
}
