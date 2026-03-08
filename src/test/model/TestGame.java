package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class TestGame {

    private Game testGame;
    private Account player1;
    private Account player2;
    private Account player3;
    private Account player4;
    
    @BeforeEach
    void runBefore() {
        player1 = new Account("yolo");
        player2 = new Account("hi");
        player3 = new Account("idk");
        player4 = new Account("1");
        testGame = new Game(player1, player2, player3, player4);
    }

    @Test
    void testConstructor() {
        assertEquals(testGame.getEastPlayerId(), "yolo");
        assertEquals(testGame.getSouthPlayerId(), "hi");
        assertEquals(testGame.getWestPlayerId(), "idk");
        assertEquals(testGame.getNorthPlayerId(), "1");
        assertEquals(testGame.getEastPlayerScore(), 0);
        assertEquals(testGame.getSouthPlayerScore(), 0);
        assertEquals(testGame.getWestPlayerScore(), 0);
        assertEquals(testGame.getNorthPlayerScore(), 0);
        assertEquals(testGame.getCurrentRound(), "E1-0");
        assertEquals(testGame.getGamePlayerList(), List.of(player1, player2, player3, player4));
        assertEquals(testGame.getLastTransaction(), List.of(0, 0, 0, 0));
        assertFalse(testGame.getGameEnd());
    }

    @Test
    void testDealerDealIn() {
        testGame.dealIn(2, 0, 10);
        assertEquals(testGame.getCurrentRound(), "E2-0");
        assertEquals(testGame.getLastTransaction(), List.of(-16, 0, 16, 0));
    }

    @Test
    void testNonDealerDealInToNonDealer() {
        testGame.dealIn(2, 1, 10);
        assertEquals(testGame.getCurrentRound(), "E2-0");
        assertEquals(testGame.getLastTransaction(), List.of(0, -15, 15, 0));
    }

    @Test
    void testNonDealerDealInToDealer() {
        testGame.dealIn(0, 1, 10);
        assertEquals(testGame.getCurrentRound(), "E1-1");
        assertEquals(testGame.getLastTransaction(), List.of(16, -16, 0, 0));
    }

    @Test
    void testLastTransactionWinnerAndLoserSwap() {
        testGame.dealIn(3, 2, 10);
        testGame.dealIn(2, 3, 17);
        assertEquals(testGame.getLastTransaction(), List.of(0, 0, 22, -22));
        assertEquals(testGame.getWestPlayerScore(), -8);
        assertEquals(testGame.getNorthPlayerScore(), 8);
    }

    @Test
    void testLastTransactionWinnerIsLastLoser() {
        testGame.dealIn(3, 2, 10);
        testGame.dealIn(2, 1, 17);
        assertEquals(testGame.getLastTransaction(), List.of(0, -23, 23, 0));
        assertEquals(testGame.getWestPlayerScore(), -15);
        assertEquals(testGame.getNorthPlayerScore(), 15);
    }

    @Test
    void testLastTransactionLoserIsLastWinner() {
        testGame.dealIn(3, 2, 10);
        testGame.dealIn(0, 3, 17);
        assertEquals(testGame.getLastTransaction(), List.of(22, 0, 0, -22));
        assertEquals(testGame.getWestPlayerScore(), -15);
        assertEquals(testGame.getNorthPlayerScore(), 15);
    }

    @Test
    void testLastTransactionWinnerIsNotLastWinnerOrLastLoser() {
        testGame.dealIn(3, 2, 10);
        testGame.selfDraw(0, 17);
        assertEquals(testGame.getLastTransaction(), List.of(67, -23, -22, -22));
        assertEquals(testGame.getWestPlayerScore(), -15);
        assertEquals(testGame.getNorthPlayerScore(), 15);
    }

    @Test
    void testLastTransactionSameWinnerSameLoser() {
        testGame.dealIn(3, 2, 10);
        testGame.dealIn(3, 2, 21);
        assertEquals(testGame.getLastTransaction(), List.of(0, 0, -49, 49));
    }

    @Test
    void testLastTransactionSameWinnerDifferentLoser() {
        testGame.dealIn(3, 2, 10);
        testGame.dealIn(3, 0, 21);
        assertEquals(testGame.getLastTransaction(), List.of(-26, 0, -15, 41));
    }

    @Test
    void selfDrawDealer() {
        testGame.selfDraw(0, 10);
        assertEquals(testGame.getCurrentRound(), "E1-1");
        assertEquals(testGame.getLastTransaction(), List.of(48, -16, -16, -16));
    }

    @Test
    void selfDrawNonDealer() {
        testGame.selfDraw(2, 10);
        assertEquals(testGame.getCurrentRound(), "E2-0");
        assertEquals(testGame.getLastTransaction(), List.of(-16, -15, 46, -15));
    }

    @Test
    void testDeckOut() {
        testGame.deckOut();
        assertEquals(testGame.getCurrentRound(), "E1-1");
    }

    @Test
    void testExtraTransaction() {
        testGame.extraTransaction(10, -5, 30, 0);
        assertEquals(testGame.getEastPlayerScore(), 10);
        assertEquals(testGame.getSouthPlayerScore(), -5);
        assertEquals(testGame.getWestPlayerScore(), 30);
        assertEquals(testGame.getNorthPlayerScore(), 0);
        testGame.extraTransaction(-3, 11, 6, -2);
        assertEquals(testGame.getEastPlayerScore(), 7);
        assertEquals(testGame.getSouthPlayerScore(), 6);
        assertEquals(testGame.getWestPlayerScore(), 36);
        assertEquals(testGame.getNorthPlayerScore(), -2);
        testGame.extraTransaction(List.of(10, -11, 0, 37));
        assertEquals(testGame.getEastPlayerScore(), 17);
        assertEquals(testGame.getSouthPlayerScore(), -5);
        assertEquals(testGame.getWestPlayerScore(), 36);
        assertEquals(testGame.getNorthPlayerScore(), 35);
    }

    @Test
    void testGameEnd() {
        testGame.dealIn(3, 2, 10);
        testGame.dealIn(0, 3, 10);
        testGame.dealIn(1, 0, 10);
        testGame.dealIn(2, 1, 10);
        testGame.dealIn(3, 2, 10);
        testGame.dealIn(0, 3, 10);
        testGame.dealIn(1, 0, 10);
        testGame.dealIn(2, 1, 10);
        testGame.dealIn(3, 2, 10);
        testGame.dealIn(0, 3, 10);
        testGame.dealIn(1, 0, 10);
        testGame.dealIn(2, 1, 10);
        testGame.dealIn(3, 2, 10);
        testGame.dealIn(0, 3, 10);
        testGame.dealIn(1, 0, 10);
        testGame.dealIn(2, 1, 10);
        assertTrue(testGame.getGameEnd());
        assertEquals(testGame.getEastPlayerScore(), 0);
        assertEquals(testGame.getSouthPlayerScore(), 0);
        assertEquals(testGame.getWestPlayerScore(), 0);
        assertEquals(testGame.getNorthPlayerScore(), 0);
    }
}
