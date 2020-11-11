import java.util.Scanner;

public class N5 {
    //удалить если 0
    //подставить если 1
    private static String text;
    private static String symbol;
    private static String k;
    private static int intBool;
    private static boolean bool;

    public void n5() {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите ваш текст: ");
        text = in.next();
        System.out.println("Введите символ который необходимо удалить или заменить: ");
        symbol = in.next();
        char[] chArraySymbol = symbol.toCharArray();
        System.out.println("Введите символ который будет вставлен вместо символа " + "'" + symbol + "'");
        k = in.next();
        char[] chArrayK = k.toCharArray();
        System.out.println("Введите 1 если хотитe удалить все символы " + "'" + symbol + "'" + " в тексте\n " +
                "Введите 0 если хотите поменять все символы " + "'" + symbol + "'" + " в тексте на символ " + "'" + k + "'" + ": ");
        intBool = in.nextInt();
        if (intBool == 1) {
            bool = true;
        } else if (intBool == 0) {
            bool = false;
        } else {
            System.out.println("Введен некорректный символ.");
        }
        StringBuilder stringBuffer = new StringBuilder(text);
        if (bool) {
            char ch = chArraySymbol[0];
            for (int i = 0; i < stringBuffer.length(); i++) {
                if (ch == stringBuffer.charAt(i)) {
                    stringBuffer.deleteCharAt(i);
                }
            }
            System.out.println(stringBuffer.toString());
        } else {
            char ch1 = chArraySymbol[0];
            char ch2 = chArrayK[0];
            String tmpString = text.replace(ch1, ch2);
            System.out.println(tmpString);
        }
    }
}
