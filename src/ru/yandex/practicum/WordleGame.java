package ru.yandex.practicum;

import java.util.Random;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {

    private String answer;
    private int steps;
    private WordleDictionary dictionary;


    public WordleGame(WordleDictionary dictionary) {
        this.answer = null;
        this.steps = 1;
        this.dictionary = dictionary;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public void gameStart() {
        Random rand = new Random();
        answer = dictionary.getWords().get(rand.nextInt(dictionary.size()));
    }

    public String getWinWorld() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPosition(String word) {
        String res = "";
        for (int i = 0; i < 5; i++) {
            if (word.charAt(i) == answer.charAt(i)) {
                res += "+";
            } else if (answer.indexOf(word.charAt(i)) != -1) {
                res += "^";
            } else {
                res += "-";
            }
        }
        WordleDictionary.addWord(word, res);
        return res;
    }
}
