import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите номер задачи: ");
        int number = in.nextInt();
        if (number > 7 || number < 1) {
            System.out.println("Нет такой задачи.");
        } else {
            switch (number) {
                case 1:
                    N1 n1 = new N1();
                    n1.n1();
                    break;
                case 2:
                    N2 n2 = new N2();
                    n2.n2();
                    break;
                case 3:
                    N3 n3 = new N3();
                    n3.n3();
                    break;
                case 4:
                    N4 n4 = new N4();
                    n4.n4();
                    break;
                case 5:
                    N5 n5 = new N5();
                    n5.n5();
                    break;
                case 6:
                    N6 n6 = new N6();
                    n6.n6();
                    break;
            }
        }
    }
}
