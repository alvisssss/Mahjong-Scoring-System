package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAccount {

    private Account testAccount;

    @BeforeEach
    void setup() {
        testAccount = new Account("testing");
    }

    @Test
    void testConstructor() {
        assertEquals(testAccount.getPlayerId(), "testing");
        assertEquals(testAccount.getPlayerScore(), 0);
    }

    @Test
    void testChangePlayerScore() {
        testAccount.changePlayerScore(-30);
        assertEquals(testAccount.getPlayerScore(), -30);
        testAccount.changePlayerScore(47);
        assertEquals(testAccount.getPlayerScore(), 17);
        testAccount.changePlayerScore(0);
        assertEquals(testAccount.getPlayerScore(), 17);
    }

}
