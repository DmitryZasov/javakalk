import java.util.Scanner;

public class Kalk{

    public static void main(String[] args) throws Exception {

        String mo = new Scanner(System.in).nextLine();

        String str = calc(mo);

        System.out.println(str);
    }

    public static String calc(String str) throws Exception {
        String[] parts = str.split(" ");
        if (parts.length != 3)
            throw new Exception("Неверный формат ввода");

        String a = parts[0];
        String znak = parts[1];
        String b = parts[2];

        int num1, num2;

        if ((isRomanNumber(a) && !isRomanNumber(b)) || (!isRomanNumber(a) && isRomanNumber(b))) {
            throw new Exception("Разные системы счисления");
        }

        if (isRomanNumber(a) && isRomanNumber(b)) {
            num1 = romanToArabic(a);
            num2 = romanToArabic(b);
        } else {
            num1 = Integer.parseInt(a);
            num2 = Integer.parseInt(b);

            if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
                throw new Exception("Число должно быть от 1 до 10 включительно");
            }
        }

        String result;
        switch (znak) {
            case "+":
                result = String.valueOf(num1 + num2);
                break;
            case "-":
                if (isRomanNumber(a) && isRomanNumber(b)) {
                    if (num1 <= num2) {
                        throw new Exception("Результат вычитания двух римских чисел не может быть <= 0");
                    }
                }
                result = String.valueOf(num1 - num2);
                break;
            case "*":
                result = String.valueOf(num1 * num2);
                break;
            case "/":
                result = String.valueOf(num1 / num2);
                break;
            default:
                throw new Exception("Недопустимый оператор");
        }

        if (isRomanNumber(a) && isRomanNumber(b))
            result = arabicToRoman(Integer.parseInt(result));
return result;


    }

//проверка является ли число римским
    private static boolean isRomanNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            if (num != 'I' && num != 'V' && num != 'X') {
                return false;
            }
        }
        return true;
    }


    private static int romanToArabic(String roman) throws Exception {
        switch (roman) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                throw new Exception("Неверное римское число. Должно быть от I до X включительно");
        }
    }

    private static String arabicToRoman(int number) {

        String[] romanOnes = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] romanTens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] romanHundreds = {"", "C"};

        int ones = number % 10;
        int tens = (number / 10) % 10;
        int hundreds = number / 100;

        return romanHundreds[hundreds] + romanTens[tens] + romanOnes[ones];
    }
}