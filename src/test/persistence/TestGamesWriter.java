package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Account;
import model.AccountList;
import model.Game;
import model.GameList;

@ExcludeFromJacocoGeneratedReport
public class TestGamesWriter extends JsonGamesTest {

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
    public void testWriterInvalidFile() {
        try {
            JsonGamesWriter writer = new JsonGamesWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyGameList() {
        try {
            GameList games = new GameList();
            JsonGamesWriter writer = new JsonGamesWriter("./data/testWriterEmptyGameList.json");
            writer.open();
            writer.write(games);
            writer.close();

            JsonGamesReader reader = new JsonGamesReader("./data/testWriterEmptyGameList.json", testAccountList);
            games = reader.read();
            assertEquals(games.getGameList(), new ArrayList<>());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralGameList() {
        try {
            GameList games = new GameList();
            Game game1 = new Game(player4, player3, player2, player1);
            game1.setup(List.of(-10, 0, 10, 20), List.of(-15, -15, 46, -16), 7, 2, 0, List.of(0, 1, 3));
            Game game2 = new Game(player5, player1, player4, player2);
            game2.setup(List.of(-16, 11, -163, 234), List.of(0, 30, 0, -30), 4, 6, 1, List.of(3));
            games.addGame(game1);
            games.addGame(game2);
            JsonGamesWriter writer = new JsonGamesWriter("./data/testWriterGeneralGameList.json");
            writer.open();
            writer.write(games);
            writer.close();
            JsonGamesReader reader = new JsonGamesReader("./data/testWriterGeneralGameList.json", testAccountList);
            games = reader.read();
            List<Game> gameList = games.getGameList();
            assertEquals(2, gameList.size());
            checkInformation(List.of("d", "c", "b", "a"), List.of(-10, 0, 10, 20), 7, 2, 0, List.of(0, 1, 3),
                    List.of(-15, -15, 46, -16), gameList.get(0));
            checkInformation(List.of("e", "a", "d", "b"), List.of(-16, 11, -163, 234), 4, 6, 1, List.of(3),
                    List.of(0, 30, 0, -30), gameList.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
