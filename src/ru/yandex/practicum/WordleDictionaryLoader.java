package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static ru.yandex.practicum.LogWriter.logWrite;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {

    public WordleDictionary loadDictionary(String fileName) {
        try(Reader fileReader = new FileReader(fileName, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(fileReader)) {
            List<String> dictionary = new ArrayList<>();
            String line;
            String[] words;
            while ((line = reader.readLine()) != null) {
                words = line.split(" ");
                for (String word : words) {
                    if (word.length() != 5) {
                        continue;
                    }
                    word = word.toLowerCase();
                    if (word.indexOf("ё") != -1){
                        word = word.replace("ё", "е");
                    }
                    dictionary.add(word);
                }
            }
            return new WordleDictionary(dictionary);
        } catch (FileNotFoundException e) {
            logWrite(e);
        } catch (IOException e) {
            logWrite(e);
        }
        return null;
    }
}
