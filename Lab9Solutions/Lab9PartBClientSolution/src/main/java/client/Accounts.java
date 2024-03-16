package client;

import java.util.ArrayList;
import java.util.Collection;

public class Accounts {

    Collection<AccountResponse> accountList = new ArrayList<AccountResponse>();

    public Collection<AccountResponse> getAccountList() {
        return accountList;
    }

    public void setAccountList(Collection<AccountResponse> accountList) {
        this.accountList = accountList;
    }

}
