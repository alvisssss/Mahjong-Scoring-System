package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    }

    @Test
    void testDealerDealIn() {
        testGame.dealIn(List.of(2), 0, List.of(10));
        Map<List<Integer>, Integer> map = new HashMap<>();
        map.put(List.of(0, 2), 16);
        assertEquals(testGame.getCurrentRound(), "E2-0");
        assertEquals(testGame.getIncompleteTransactions(), map);
    }

    @Test
    void testNonDealerDealInToNonDealer() {
        testGame.dealIn(List.of(2), 1, List.of(10));
        Map<List<Integer>, Integer> map = new HashMap<>();
        map.put(List.of(1, 2), 15);
        assertEquals(testGame.getCurrentRound(), "E2-0");
        assertEquals(testGame.getIncompleteTransactions(), map);
    }

    @Test
    void testNonDealerDealInToDealer() {
        testGame.dealIn(List.of(0), 1, List.of(10));
        Map<List<Integer>, Integer> map = new HashMap<>();
        map.put(List.of(1, 0), 16);
        assertEquals(testGame.getCurrentRound(), "E1-1");
        assertEquals(testGame.getIncompleteTransactions(), map);
    }

    @Test
    void testNonDealerDealInToDealerAndNonDealer() {
        testGame.dealIn(List.of(0, 2), 1, List.of(10, 20));
        Map<List<Integer>, Integer> map = new HashMap<>();
        map.put(List.of(1, 0), 16);
        map.put(List.of(1, 2), 25);
        assertEquals(testGame.getCurrentRound(), "E1-1");
        assertEquals(testGame.getIncompleteTransactions(), map);
    }

    @Test
    void testNonDealerDealInToNonDealers() {
        testGame.dealIn(List.of(3, 2), 1, List.of(65, 10));
        Map<List<Integer>, Integer> map = new HashMap<>();
        map.put(List.of(1, 3), 70);
        map.put(List.of(1, 2), 15);
        assertEquals(testGame.getCurrentRound(), "E2-0");
        assertEquals(testGame.getIncompleteTransactions(), map);
    }

    @Test
    void selfDrawDealer() {
        testGame.selfDraw(0, 10);
        Map<List<Integer>, Integer> map = new HashMap<>();
        map.put(List.of(1, 0), 16);
        map.put(List.of(2, 0), 16);
        map.put(List.of(3, 0), 16);
        assertEquals(testGame.getCurrentRound(), "E1-1");
        assertEquals(testGame.getIncompleteTransactions(), map);
    }

    @Test
    void selfDrawNonDealer() {
        testGame.selfDraw(2, 10);
        Map<List<Integer>, Integer> map = new HashMap<>();
        map.put(List.of(0, 2), 16);
        map.put(List.of(1, 2), 15);
        map.put(List.of(3, 2), 15);
        assertEquals(testGame.getCurrentRound(), "E2-0");
        assertEquals(testGame.getIncompleteTransactions(), map);
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
    }
}
