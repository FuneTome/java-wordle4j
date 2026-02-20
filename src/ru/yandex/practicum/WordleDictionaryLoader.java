package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {
    static final int MAXWORDLENGTH = 5;
    private LogWriter logWriter;

    public WordleDictionaryLoader(LogWriter logWriter) {
        this.logWriter = logWriter;
    }

    public WordleDictionary loadDictionary(String fileName) {
        try (Reader fileReader = new FileReader(fileName, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(fileReader)) {
            List<String> dictionary = new ArrayList<>();
            String line;
            String[] words;
            while ((line = reader.readLine()) != null) {
                words = line.split(" ");
                for (String word : words) {
                    if (word.length() != MAXWORDLENGTH) {
                        continue;
                    }
                    dictionary.add(WordleDictionary.normalize(word));
                }
            }
            return new WordleDictionary(dictionary, logWriter);
        } catch (IOException e) {
            logWriter.logWrite(e);
            return null;
        }
    }
}
