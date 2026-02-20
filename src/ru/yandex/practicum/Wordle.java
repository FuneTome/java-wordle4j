package ru.yandex.practicum;

import java.io.IOException;
import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {
    static final int STARTSTEP = 1;
    static final int ENDSTEP = 5;
    static final String WINANSWER = "+++++";


        public static void main (String[]args){
        try(LogWriter log = new LogWriter()) {
        Scanner sc = new Scanner(System.in);

        WordleDictionaryLoader loader = new WordleDictionaryLoader(log);
        WordleDictionary dictionary = loader.loadDictionary("words_ru.txt");
        WordleGame game = new WordleGame(dictionary);

        boolean isHint = true;
        while (true) {
            if (game.getSteps() == STARTSTEP && isHint) {
                game.gameStart();
                System.out.println("Начинаем!\nСлово загадано!");
            } else if (game.getSteps() > ENDSTEP) {
                System.out.println("Вы проиграли!");
                System.out.println("Загаданное слово это " + game.getWinWorld());
                System.out.println("Хотите начать сначала? (Y/N)");
                String answer = sc.nextLine();
                if (answer.equals("Y")) {
                    game.setSteps(STARTSTEP);
                    dictionary.clearHint();
                    continue;
                } else {
                    return;
                }
            }

            System.out.println("Попытка номер " + game.getSteps());
            System.out.println("Введите слово");
            String word = sc.nextLine();
            if (word.isBlank()) {
                System.out.println("Подсказка: " + dictionary.getHint());
                isHint = false;
            } else {
                isHint = true;
                while (!dictionary.checkWord(word)) {
                    System.out.println("Введите слово");
                    word = sc.nextLine();
                }
                String ans = game.getPosition(word);
                System.out.println(ans);
                if (ans.equals(WINANSWER)) {
                    System.out.println("Вы угадали!");
                    System.out.println("Хотите начать сначала? (Y/N)");
                    String answer = sc.nextLine();
                    if (answer.equals("Y")) {
                        game.setSteps(STARTSTEP);
                        dictionary.clearHint();
                        continue;
                    } else {
                        return;
                    }
                } else {
                    game.setSteps(game.getSteps() + STARTSTEP);
                }
            }
        }
    } catch (IOException e) {
                System.err.println("Не удалось создать лог-файл: " + e.getMessage());
                return;
            }
    }
}
