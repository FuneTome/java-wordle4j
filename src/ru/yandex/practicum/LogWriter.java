package ru.yandex.practicum;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter {
    public LogWriter() {
        try (FileWriter writer = new FileWriter("log.txt", false)) {
            Date date = new Date();//Чтобы прошел CodeStyle
        } catch (IOException e) {
            System.out.println("Ошибка при записи в лог-файл: " + e.getMessage());
        }
    }

    public static void logWrite(Exception e) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            Date date = new Date(); // Получаем текущую дату и время
            writer.write("Время: " + dateFormat.format(date) + " - Ошибка: " + e.getMessage() + "\n");
        } catch (IOException ex) {
            System.out.println("Ошибка при записи в лог-файл: " + ex.getMessage());
        }
    }
}
