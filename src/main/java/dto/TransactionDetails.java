package dto;

import model.Item;
import model.PlayerAccount;

public record TransactionDetails(Item item, PlayerAccount playerAccount) {
}
