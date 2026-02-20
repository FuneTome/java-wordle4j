package ru.yandex.practicum;

import java.util.*;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции посравнению слов, букв и т.д.
 */
public class WordleDictionary {
    static final int MAXWORDLENGTH = 5;
    private List<String> words;
    private Set<String> hintSet;
    private final List<String> addWords = new ArrayList<>();

    private LogWriter logWriter;  // новое поле

    public WordleDictionary(List<String> words, LogWriter logWriter) {
        this.words = words;
        this.hintSet = new HashSet<>(words);
        this.logWriter = logWriter;
    }

    public List<String> getWords() {
        return words;
    }

    public int size() {
        return words.size();
    }

    public boolean checkWord(String word) {
        if (word.length() != MAXWORDLENGTH) {
            logWriter.logWrite(new WordException("Слово не подходит по длине"));
            return false;
        } else if (!words.contains(word)) {
            logWriter.logWrite(new WordException("Слово не найдено в словаре"));
            return false;
        }
        return true;
    }

    public static String normalize(String word) {
        return word.trim().toLowerCase().replace("ё", "е");
    }

    public void addWord(String word, String position) {
        addWords.add(word);
        int i = 0;
        for (Character c : position.toCharArray()) {
            if (c == '+') {
                for (String w : words) {
                    if (w.charAt(i) != word.charAt(i)) {
                        hintSet.remove(w);
                    }
                }
            } else if (c == '^') {
                for (String w : words) {
                    if (w.indexOf(word.charAt(i)) == -1) {
                        hintSet.remove(w);
                    }
                }
            } else {
                for (String w : words) {
                    if (w.indexOf(word.charAt(i)) != -1) {
                        hintSet.remove(w);
                    }
                }
            }
            i++;
        }
    }

    public String getHint() {
        Random rand = new Random();
        int index = rand.nextInt(hintSet.size());
        int i = 0;
        for (String element : hintSet) {
            if (i == index) {
                return element;
            }
            i++;
        }
        return null;
    }

    public void clearHint() {
        hintSet = new HashSet<>(words);
    }
}
