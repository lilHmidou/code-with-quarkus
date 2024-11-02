package service;

import jakarta.enterprise.context.ApplicationScoped;
import model.Item;
import model.PlayerAccount;

@ApplicationScoped
public class GameTransactionService {

    public void debitAccount(PlayerAccount playerAccount, Item item) {
        playerAccount.setCredit(playerAccount.getCredit() - item.price());
        System.out.println("New account balance: " + playerAccount.getCredit() + " ");
    }

    public void addItemToInventory(PlayerAccount playerAccount, Item item) {
        playerAccount.setInventorySize(playerAccount.getInventorySize() - item.weight());
        System.out.println(item.name() + " added to inventory! ");
    }
}
