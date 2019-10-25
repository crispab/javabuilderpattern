package se.crisp.edu.java.builder.securebydesign;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AccountTest {

    private static final AccountNumber SOME_ACCOUNT_NUMBER = new AccountNumber();
    private static final AccountNumber SOME_FALLBACK_ACCOUNT = new AccountNumber();
    private static final LegalPerson SOME_OWNER = new LegalPerson();
    private static final Percentage SOME_INTEREST = new Percentage();

    @Test
    public void build_account_with_credit() {
        Account accountWithhCredit = Account
                .accountNumber(
                        SOME_ACCOUNT_NUMBER,
                        SOME_OWNER,
                        SOME_INTEREST)
                .withCreditLimit(new Money())
                .build();

        assertNotNull(accountWithhCredit);
    }

    @Test
    public void build_account_with_fallback() {
        Account accountWithhCredit = Account
                .accountNumber(
                        SOME_ACCOUNT_NUMBER,
                        SOME_OWNER,
                        SOME_INTEREST)
                .withFallbackAccount(SOME_FALLBACK_ACCOUNT)
                .build();

        assertNotNull(accountWithhCredit);
    }
}