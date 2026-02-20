package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {
    WordleDictionaryLoader loader = new WordleDictionaryLoader(new LogWriter());
    WordleDictionary dictionary = loader.loadDictionary("words_ru.txt");
    WordleGame game = new WordleGame(dictionary);

    WordleTest() throws IOException {
    }

    @BeforeEach
    public void updateGame(){
        game.gameStart();
    }

    @Test
    public void testReturnValueWin(){
        game.setAnswer("блюдо");
        String pos = game.getPosition("блюдо");
        Assertions.assertEquals("+++++", pos);
    }

    @Test
    public void testReturnValueNoWin(){
        game.setAnswer("блюдо");
        String pos = game.getPosition("айван");
        Assertions.assertEquals("-----", pos);
    }

    @Test
    public void testReturnValue(){
        game.setAnswer("вольт");
        String pos = game.getPosition("гость");
        Assertions.assertEquals("-+-^^", pos);
    }

    @Test
    public void testNoCorrectLength() {
        Assertions.assertFalse(dictionary.checkWord("океаны"));
    }

    @Test
    public void testNoCorrectValue() {
        assertFalse(dictionary.checkWord("океен"));
    }


    @Test
    public void testCorrectValue(){
        Assertions.assertTrue(dictionary.checkWord("океан"));
    }

    @Test
    public void testNormalizeMethod(){
        Assertions.assertEquals("океан", WordleDictionary.normalize("  ОкёАН    "));
    }
}
