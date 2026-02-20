package ru.yandex.practicum;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriter implements AutoCloseable {
    private FileWriter writer;

    public LogWriter() throws IOException {
        writer = new FileWriter("log.txt", false);
    }

    public void logWrite(Exception e) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm:ss");
        Date date = new Date();
        try {
            writer.write("Время: " + dateFormat.format(date) + " - Ошибка: " + e.getMessage() + "\n");
        } catch (IOException ex) {
            System.err.println("Не удалось записать в лог: " + ex.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
