package ru.yandex.practicum;

import java.util.Scanner;
import static ru.yandex.practicum.WordleDictionary.checkWord;

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LogWriter log = new LogWriter(); //костыль чтобы при перезапуске файл с логами очищался
                                        // но при работе записывал подряд

        WordleDictionaryLoader loader = new WordleDictionaryLoader();
        WordleDictionary dictionary = loader.loadDictionary("words_ru.txt");
        WordleGame game = new WordleGame(dictionary);

        boolean isHint = true;
        while (true) {
            if (game.getSteps() == 1 && isHint) {
                game.gameStart();
                System.out.println("Начинаем!\nСлово загадано!");
            } else if (game.getSteps() > 5) {
                System.out.println("Вы проиграли!");
                System.out.println("Загаданное слово это " + game.getWinWorld());
                System.out.println("Хотите начать сначала? (Y/N)");
                String answer = sc.nextLine();
                if (answer.equals("Y")) {
                    game.setSteps(1);
                    continue;
                } else {
                    return;
                }
            }

            System.out.println("Попытка номер " + game.getSteps());
            System.out.println("Введите слово");
            String word = sc.nextLine();
            if (word.isBlank()) {
                System.out.println("Подсказка: " + dictionary.getHint(word));
                isHint = false;
            } else {
                isHint = true;
                while (!checkWord(word)) {
                    System.out.println("Введите слово");
                    word = sc.nextLine();
                }
                String ans = game.getPosition(word.toLowerCase());
                System.out.println(ans);
                if (ans.equals("+++++")) {
                    System.out.println("Вы угадали!");
                    System.out.println("Хотите начать сначала? (Y/N)");
                    String answer = sc.nextLine();
                    if (answer.equals("Y")) {
                        game.setSteps(0);
                        continue;
                    } else {
                        return;
                    }
                } else {
                    game.setSteps(game.getSteps() + 1);
                }
            }
        }
    }

}
