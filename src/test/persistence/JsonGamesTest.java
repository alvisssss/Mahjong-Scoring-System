package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Game;

@ExcludeFromJacocoGeneratedReport
public class JsonGamesTest {
    protected void checkInformation(List<String> playerIds, List<Integer> playerScores, int round, int continuance,
                int lastWinner, List<Integer> lastLosers, List<Integer> lastTransaction, Game game) {
        assertEquals(game.getEastPlayerId(), playerIds.get(0));
        assertEquals(game.getSouthPlayerId(), playerIds.get(1));
        assertEquals(game.getWestPlayerId(), playerIds.get(2));
        assertEquals(game.getNorthPlayerId(), playerIds.get(3));
        assertEquals(game.getEastPlayerScore(), playerScores.get(0));
        assertEquals(game.getSouthPlayerScore(), playerScores.get(1));
        assertEquals(game.getWestPlayerScore(), playerScores.get(2));
        assertEquals(game.getNorthPlayerScore(), playerScores.get(3));
        assertEquals(game.getCurrentRound(), Game.ROUNDS[round] + "-" + continuance);
        assertEquals(game.getLastTransaction(), lastTransaction);
        assertEquals(game.getLastWinner(), lastWinner);
        assertEquals(game.getlastLosers(), lastLosers);
    }
}
