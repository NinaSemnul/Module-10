//Завдання 1
//        Дано текстовий файл file.txt, в якому є список номерів телефонів (один рядок - один телефон).
//
//        Необхідно написати метод, який буде читати файл, і виводити в консоль всі валідні номери телефонів.
//
//        Телефон вважається валідним, якщо він відповідає одному з двох форматів (x - це одна цифра):
//
//        (xxx) xxx-xxxx
//        xxx-xxx-xxxx
//        Приклад:
//
//        Для файлу file.txt з наступним змістом:
//
//        987-123-4567
//        123 456 7890
//        (123) 456-7890
//
//        Метод повинен вивести на екран:
//
//        987-123-4567
//        (123) 456-7890

import java.io.FileInputStream;
import java.io.IOException;

public class ReadPhone {

    public static void main(String[] args) {

        readFileWithInputStream();

    }


    public static void readFileWithInputStream() {

        try (FileInputStream fileInputStream = new FileInputStream("E:\\Go IT\\Home task\\Module 10\\file.txt")) {

            System.out.println("File size is bytes: " + fileInputStream.available());
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            String line = "";
            int a = 0;

            for (int i = 0; i < buffer.length; i++) {
                if ((char) buffer[i] != '\r') {
                    line = line + (char) buffer[i];
                } else {
                    line = line.strip();

                    if (isGoodLine(line)) {
                        System.out.println(line);
                        a++;
                    }
                    line = "";

                }

            }
            line = line.strip();

            if (isGoodLine(line)) {
                System.out.println(line);
                a++;
            }
            line = "";

        } catch (IOException e) {
            System.out.println(e.getMessage());

    }


}

    private static boolean isGoodLine(String line) {
        boolean isGood = false;

        if (line.length() == 14) {     // (xxx) xxx-xxxx
            isGood = line.substring(0, 1).contains("(")
                    && (line.charAt(3) >= '0' && line.charAt(3) <= '9')
                    && line.substring(4, 5).contains(")")
                    && line.substring(5, 6).contains(" ")

                    && (line.charAt(7) >= '0' && line.charAt(7) <= '9')

                    && line.substring(9, 10).contains("-")

                    && (line.charAt(12) >= '0' && line.charAt(12) <= '9')
                    && (line.charAt(13) >= '0' && line.charAt(13) <= '9');
        }

        if (line.length() == 12) {     //  xxx-xxx-xxxx
            isGood = (line.charAt(0) >= '0' && line.charAt(0) <= '9')

                    && line.substring(3, 4).contains("-")
                    && (line.charAt(4) >= '0' && line.charAt(4) <= '9')
                    && (line.charAt(5) >= '0' && line.charAt(5) <= '9')

                    && line.substring(7, 8).contains("-");

        }

        isGood = isGood
                && (line.charAt(1) >= '0' && line.charAt(1) <= '9')
                && (line.charAt(2) >= '0' && line.charAt(2) <= '9')
                && (line.charAt(6) >= '0' && line.charAt(6) <= '9')
                && (line.charAt(8) >= '0' && line.charAt(8) <= '9')
                && (line.charAt(10) >= '0' && line.charAt(10) <= '9')
                && (line.charAt(11) >= '0' && line.charAt(11) <= '9');

        return isGood;
    }
    }
