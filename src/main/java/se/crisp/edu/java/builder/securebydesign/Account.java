package se.crisp.edu.java.builder.securebydesign;

import static org.apache.commons.lang3.Validate.notNull;
import static org.apache.commons.lang3.Validate.validState;

public class Account {
    private final AccountNumber number;
    private final LegalPerson owner;
    private final Percentage interest;
    private Money creditLimit;
    private AccountNumber fallbackAccount;

    private Account(AccountNumber number,
                    LegalPerson owner,
                    Percentage interest) {
        this.number = notNull(number);
        this.owner = notNull(owner);
        this.interest = notNull(interest);
    }

    public static ICreditOrFallback accountNumber(AccountNumber number,
                                                  LegalPerson owner,
                                                  Percentage interest) {
        return new Builder(number, owner, interest);
    }

    private void checkInvariants() throws IllegalStateException {
        validState(fallbackAccount != null
                ^ creditLimit != null);
    }

    public static class Builder implements ICreditOrFallback, IBuild {
        private Account product;

        public Builder(AccountNumber number,
                       LegalPerson owner,
                       Percentage interest) {
            product = new Account(number, owner, interest);
        }

        public IBuild withCreditLimit(Money creditLimit) {
            validState(product != null);
            product.creditLimit = creditLimit;
            return this;
        }

        public IBuild withFallbackAccount(AccountNumber fallbackAccount) {
            validState(product != null);
            product.fallbackAccount = fallbackAccount;
            return this;
        }

        public Account build() {
            validState(product != null);
            product.checkInvariants();
            Account result = product;
            product = null;
            return result;
        }
    }

    public interface ICreditOrFallback {
        IBuild withCreditLimit(Money creditLimit);

        IBuild withFallbackAccount(AccountNumber fallbackAccount);
    }

    public interface IBuild {
        Account build();
    }
}

