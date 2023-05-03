//Завдання 3
//        Напишіть метод, який буде рахувати частоту кожного слова у файлі words.txt.
//
//        Вважаємо що:
//
//        words.txt містить лише слова в нижньому регістрі, розділені пробілом
//        Кожне слово містить лише літери в нижньому регістрі
//        Слова розділені одним або декількома пробілами, або переносом рядка
//        Приклад:
//
//        Для файлу words.txt із вмістом:
//
//        the day is sunny the the
//        the sunny is is
//
//        Метод повинен повернути результат на кшталт:
//
//        the 4
//        is 3
//        sunny 2
//        day 1
//
//        УВАГА
//        Результат виводу в консоль повинен бути відсортований за частотою (спочатку йдуть слова, що зустрічаються найчастіше)

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class FrequencyWord {

    public static void main(String[] args) {

        calculateWords(readFile());

    }

    public static String readFile() {
        String line = "";

        try (FileInputStream fileInputStream = new FileInputStream("E:\\Go IT\\Home task\\Module 10\\words.txt")) {

            System.out.println("File size is bytes: " + fileInputStream.available());
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);

            for (int i = 0; i < buffer.length; i++) {
                if ((char) buffer[i] != '\r' && (char) buffer[i] != '\n') {
                    line = line + (char) buffer[i];
                } else {
                    line = line.strip() + " ";
                }

            }
            line = line.strip();

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

        return line;
    }

    public static Map calculateWords(String line) {

        Map<String, Integer> wordsHash = new HashMap<>();
        String[] words = line.split(" ");

        for (String word : words) {

            if (wordsHash.get(word) != null) {
                wordsHash.replace(word, wordsHash.get(word), wordsHash.get(word) + 1);
            } else {
                wordsHash.put(word, 1);
            }
        }


        Map<String, Integer> sortedMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String key1, String key2) {
                Integer value1 = wordsHash.get(key1);
                Integer value2 = wordsHash.get(key2);
                return value2.compareTo(value1);
            }
        });

        sortedMap.putAll(wordsHash);

        System.out.println(sortedMap);
        return sortedMap;
    }
}



