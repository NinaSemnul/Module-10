//Завдання 2
//        Є текстовий файл file.txt.
//        Необхідно прочитати файл, перетворити його в список об'єктів типу User,
//        і записати їх у новий файл user.json.
//
//        Формат файлу:
//
//        перший рядок - заголовок
//        кожний наступний рядок - окремий об'єкт, кожна колонка розділена пробілом
//        Приклад:
//
//        Для файлу file.txt із вмістом:
//
//        name age
////        alice 21
////        ryan 30

import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class ListUser {
    public static void main(String[] args) {

        readFileWithInputStream();

    }

    public static <Gson> void readFileWithInputStream() {
//        String filePath = new File("").getAbsolutePath();
//        filePath.concat("file.txt");
        ArrayList lines = new ArrayList();

        try (FileInputStream fileInputStream = new FileInputStream("E:\\Go IT\\Home task\\Module 10\\file2.txt")) {
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
                    lines.add(a, line);
                    a++;
                    line = "";

                }

            }
            line = line.strip();
            lines.add(a, line);
            line = "";

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        com.google.gson.Gson gson = new GsonBuilder().setPrettyPrinting().create();

       String fileJson = new File("").getAbsolutePath();
        fileJson.concat("user.json");

        try(FileOutputStream fileOutputStream = new FileOutputStream("user.json")) {

            String t = "[" ;
            byte[] buffer = t.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);

        for (int i = 1; i < lines.size(); i++) {
            String a = lines.get(i).toString();
            String[] words = a.split(" ");

            Person person = new Person(words[0], words[1]);
            String json = gson.toJson(person);

            buffer = json.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);

            System.out.println(json);
        }

            t = "]" ;
            buffer = t.getBytes();
            fileOutputStream.write(buffer, 0, buffer.length);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


class Person {
    private String name;
    private String age;
    

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
      
    }

    public String getName() {

        return name;
    }

    public String getAge() {

        return age;
    }
    
}
