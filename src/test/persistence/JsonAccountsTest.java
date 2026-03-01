package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Account;

@ExcludeFromJacocoGeneratedReport
public class JsonAccountsTest {
    protected void checkInformation(String playerId, int score, Account account) {
        assertEquals(playerId, account.getPlayerId());
        assertEquals(score, account.getPlayerScore());
    }
}
