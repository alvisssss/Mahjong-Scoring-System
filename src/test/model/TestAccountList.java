package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAccountList {

    private AccountList testAccountList;
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
        testAccountList = new AccountList();
        player1 = new Account("yolo");
        player2 = new Account("hi");
        player3 = new Account("idk");
        player4 = new Account("1");
        player5 = new Account("2");
        player6 = new Account("6$");
        player7 = new Account("280");
        player8 = new Account("154");
    }

    @Test
    void testConstructor() {
        assertEquals(testAccountList.getAccountList(), new ArrayList<>());
    }

    @Test
    void testAddOneAccount() {
        testAccountList.addAccount(player1);
        assertEquals(testAccountList.getAccountList(), List.of(player1));
    }

    @Test
    void testAddMultipleAccounts() {
        testAccountList.addAccount(player3);
        testAccountList.addAccount(player7);
        testAccountList.addAccount(player4);
        assertEquals(testAccountList.getAccountList(), List.of(player3, player7, player4));
    }

    @Test
    void testRemoveOneAccount() {
        testAccountList.addAccount(player5);
        testAccountList.removeAccount(player5);
        assertEquals(testAccountList.getAccountList(), List.of());

    }

    @Test
    void testRemoveMultipleAccounts() {
        testAccountList.addAccount(player2);
        testAccountList.addAccount(player6);
        testAccountList.addAccount(player8);
        testAccountList.removeAccount(player6);
        testAccountList.removeAccount(player8);
        assertEquals(testAccountList.getAccountList(), List.of(player2));
    }
}
