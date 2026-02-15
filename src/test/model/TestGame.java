package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(testGame.getCurrentRound(), "E1");
        assertEquals(testGame.getContinuance(), 0);
    }

    @Test
    void testDealerDealIn() {
        testGame.dealIn(2, 0, 10);
        List<int[]> list = new ArrayList<>();
        int[] list2 = {0, 2, 16};
        list.add(list2);
        assertEquals(testGame.getCurrentRound(), "E2");
        assertEquals(testGame.getContinuance(), 0);
        assertEquals(testGame.getIncompleteTransactions(), list);
    }

    @Test
    void testNonDealerDealInToNonDealer() {
        testGame.dealIn(2, 1, 10);
        List<int[]> list = new ArrayList<>();
        int[] list2 = {1, 2, 15};
        list.add(list2);
        assertEquals(testGame.getCurrentRound(), "E2");
        assertEquals(testGame.getContinuance(), 0);
        assertEquals(testGame.getIncompleteTransactions(), list);
    }

    @Test
    void testNonDealerDealInToDealer() {
        testGame.dealIn(0, 1, 10);
        List<int[]> list = new ArrayList<>();
        int[] list2 = {1, 2, 16};
        list.add(list2);
        assertEquals(testGame.getCurrentRound(), "E1");
        assertEquals(testGame.getContinuance(), 1);
        assertEquals(testGame.getIncompleteTransactions(), list);
    }

    @Test
    void selfDrawDealer() {
        testGame.selfDraw(0, 10);
        List<int[]> list = new ArrayList<>();
        int[] list2 = {1, 0, 16};
        int[] list3 = {2, 0, 16};
        int[] list4 = {3, 0, 16};
        list.add(list2);
        list.add(list3);
        list.add(list4);
        assertEquals(testGame.getCurrentRound(), "E1");
        assertEquals(testGame.getContinuance(), 1);
        assertEquals(testGame.getIncompleteTransactions(), list);
    }

    @Test
    void selfDrawNonDealer() {
        testGame.selfDraw(2, 10);
        List<int[]> list = new ArrayList<>();
        int[] list2 = {0, 0, 16};
        int[] list3 = {1, 0, 15};
        int[] list4 = {3, 0, 15};
        list.add(list2);
        list.add(list3);
        list.add(list4);
        assertEquals(testGame.getCurrentRound(), "E2");
        assertEquals(testGame.getContinuance(), 0);
        assertEquals(testGame.getIncompleteTransactions(), list);
    }

    @Test
    void testDeckOut() {
        testGame.deckOut();
        assertEquals(testGame.getCurrentRound(), "E1");
        assertEquals(testGame.getContinuance(), 1);
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
