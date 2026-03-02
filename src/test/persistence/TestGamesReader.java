package persistence;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Account;
import model.AccountList;
import model.Game;
import model.GameList;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

@ExcludeFromJacocoGeneratedReport
public class TestGamesReader extends JsonGamesTest {

    private AccountList testAccountList;
    private Account player1;
    private Account player2;
    private Account player3;
    private Account player4;
    private Account player5;

    @Before
    public void setup() {
        testAccountList = new AccountList();
        player1 = new Account("a");
        player2 = new Account("b");
        player3 = new Account("c");
        player4 = new Account("d");
        player5 = new Account("e");
        testAccountList.addAccount(player1);
        testAccountList.addAccount(player2);
        testAccountList.addAccount(player3);
        testAccountList.addAccount(player4);
        testAccountList.addAccount(player5);
    }

    @Test
    public void testReaderNonExistentFile() {
        JsonGamesReader reader = new JsonGamesReader("./data/noSuchFile.json", testAccountList);
        try {
            GameList games = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyGameList() {
        JsonGamesReader reader = new JsonGamesReader("./data/testReaderEmptyGameList.json", testAccountList);
        try {
            GameList games = reader.read();
            assertEquals(games.getGameList(), new ArrayList<>());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralGameList() {
        JsonGamesReader reader = new JsonGamesReader("./data/testReaderGeneralGameList.json", testAccountList);
        try {
            GameList games = reader.read();
            List<Game> gameList = games.getGameList();
            assertEquals(2, gameList.size());
            checkInformation(List.of("a", "b", "c", "d"), List.of(100, -20, -31, 0), 2, 0, 0, List.of(1),
                    List.of(15, -15, 0, 0), gameList.get(0));
            checkInformation(List.of("b", "d", "a", "c"), List.of(-400, 500, -1, 37), 5, 3, 1, List.of(0, 2, 3),
                    List.of(-200, 400, -100, -100), gameList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderFaultyGameList() {
        JsonGamesReader reader = new JsonGamesReader("./data/testReaderFaultyGameList.json", testAccountList);
        try {
            GameList games = reader.read();
            fail("IO Exception expected");
        } catch (IOException e) {
            // pass
        }
    }
}
