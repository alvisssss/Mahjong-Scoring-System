package persistence;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Account;
import model.AccountList;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

@ExcludeFromJacocoGeneratedReport
public class TestAccountsReader extends JsonAccountsTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonAccountsReader reader = new JsonAccountsReader("./data/noSuchFile.json");
        try {
            AccountList accounts = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyAccountList() {
        JsonAccountsReader reader = new JsonAccountsReader("./data/testReaderEmptyAccountList.json");
        try {
            AccountList accounts = reader.read();
            assertEquals(accounts.getAccountList(), new ArrayList<>());
            assertEquals(accounts.getAccountNames(), new ArrayList<>());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralAccountList() {
        JsonAccountsReader reader = new JsonAccountsReader("./data/testReaderGeneralAccountList.json");
        try {
            AccountList accounts = reader.read();
            List<Account> accountlist = accounts.getAccountList();
            assertEquals(3, accountlist.size());
            checkInformation("yolo", 150, accountlist.get(0));
            checkInformation("idk", -246, accountlist.get(1));
            checkInformation("hi there", 0, accountlist.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
