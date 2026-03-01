package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.Account;
import model.AccountList;

@ExcludeFromJacocoGeneratedReport
public class TestAccountsWriter extends JsonAccountsTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            JsonAccountsWriter writer = new JsonAccountsWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyAccountList() {
        try {
            AccountList accounts = new AccountList();
            JsonAccountsWriter writer = new JsonAccountsWriter("./data/testWriterEmptyAccountList.json");
            writer.open();
            writer.write(accounts);
            writer.close();

            JsonAccountsReader reader = new JsonAccountsReader("./data/testWriterEmptyAccountList.json");
            accounts = reader.read();
            assertEquals(accounts.getAccountList(), new ArrayList<>());
            assertEquals(accounts.getAccountNames(), new ArrayList<>());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralWorkroom() {
        try {
            AccountList accounts = new AccountList();
            Account player1 = new Account("what");
            Account player2 = new Account("how");
            player1.changePlayerScore(200);
            player2.changePlayerScore(-357);
            accounts.addAccount(player1);
            accounts.addAccount(player2);
            JsonAccountsWriter writer = new JsonAccountsWriter("./data/testWriterGeneralAccountList.json");
            writer.open();
            writer.write(accounts);
            writer.close();
            JsonAccountsReader reader = new JsonAccountsReader("./data/testWriterGeneralAccountList.json");
            accounts = reader.read();
            List<Account> accountlist = accounts.getAccountList();
            assertEquals(2, accountlist.size());
            checkInformation("what", 200, accountlist.get(0));
            checkInformation("how", -357, accountlist.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
