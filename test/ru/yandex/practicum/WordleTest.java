package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {
    WordleDictionaryLoader loader = new WordleDictionaryLoader();
    WordleDictionary dictionary = loader.loadDictionary("words_ru.txt");
    WordleGame game = new WordleGame(dictionary);

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
        assertFalse(WordleDictionary.checkWord("океаны"));
    }

    @Test
    public void testNoCorrectValue() {
        assertFalse(WordleDictionary.checkWord("океен"));
    }


    @Test
    public void testCorrectValue(){
        Assertions.assertTrue(WordleDictionary.checkWord("океан"));
    }

}
