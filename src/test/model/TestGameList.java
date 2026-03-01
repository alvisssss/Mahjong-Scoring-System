package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestGameList {

    private GameList testGameList;
    private Game game1;
    private Game game2;
    private Game game3;
    private Account player1;
    private Account player2;
    private Account player3;
    private Account player4;
    private Account player5;
    private Account player6;
    private Account player7;
    private Account player8;

    @BeforeEach
    void setup() {
        testGameList = new GameList();
        player1 = new Account("yolo");
        player2 = new Account("hi");
        player3 = new Account("idk");
        player4 = new Account("1");
        player4 = new Account("2");
        player4 = new Account("6$");
        player4 = new Account("280");
        player4 = new Account("154");
        game1 = new Game(player1, player3, player5, player7);
        game2 = new Game(player2, player4, player6, player8);
        game3 = new Game(player7, player6, player4, player1);
    }

    @Test
    void testConstructor() {
        assertEquals(testGameList.getGameList(), new ArrayList<>());
    }

    @Test
    void testAddOneGame() {
        testGameList.addGame(game1);
        assertEquals(testGameList.getGameList(), List.of(game1));
    }

    @Test
    void testAddMultipleGames() {
        testGameList.addGame(game2);
        testGameList.addGame(game3);
        assertEquals(testGameList.getGameList(), List.of(game2, game3));
    }

    @Test
    void testRemoveOneGame() {
        testGameList.addGame(game2);
        testGameList.removeGame(game2);
        assertEquals(testGameList.getGameList(), List.of());

    }

    @Test
    void testRemoveMultipleGames() {
        testGameList.addGame(game2);
        testGameList.addGame(game1);
        testGameList.addGame(game3);
        testGameList.removeGame(game2);
        testGameList.removeGame(game1);
        assertEquals(testGameList.getGameList(), List.of(game3));
    }
}
