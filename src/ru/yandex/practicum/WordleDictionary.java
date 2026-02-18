package ru.yandex.practicum;

import static ru.yandex.practicum.LogWriter.logWrite;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции посравнению слов, букв и т.д.
 */
public class WordleDictionary {

    private static List<String> words;
    private static List<String> hintList;
    private static List<String> addWords = new ArrayList<>();

    public WordleDictionary(List<String> words) {
        this.words = words;
        this.hintList = new ArrayList<>(words);
    }

    public List<String> getWords() {
        return words;
    }

    public int size() {
        return words.size();
    }

    public static boolean checkWord(String word) {
        try {
            if (word.length() != 5) {
                throw new WordError("Слово не подходит по длине");
            } else if (!words.contains(word)) {
                throw new WordError("Слово не найдено в словаре");
            }
            return true;
        } catch (WordError e) {
            System.out.println(e.getMessage());
            logWrite(e);
        }
        return false;
    }

    public static void addWord(String word, String position) {
        addWords.add(word);
        int i = 0;
        for (Character c : position.toCharArray()) {
            if (c == '+') {
                for (String w : words) {
                    if (w.charAt(i) != word.charAt(i)) {
                        hintList.remove(w);
                    }
                }
            } else if (c == '^') {
                for (String w : words) {
                    if (w.indexOf(word.charAt(i)) == -1) {
                        hintList.remove(w);
                    }
                }
            } else {
                for (String w : words) {
                    if (w.indexOf(word.charAt(i)) != -1) {
                        hintList.remove(w);
                    }
                }
            }
            i++;
        }
    }

    public String getHint(String word) {
        Random rand = new Random();
        return hintList.get(rand.nextInt(hintList.size()));
    }

    public void clearHint() {
        hintList = new ArrayList<>(words);
    }
}
