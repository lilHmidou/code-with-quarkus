import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import dto.TransactionDetails;
import model.Item;
import model.PlayerAccount;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import service.GameTransactionService;

import java.util.UUID;

@ApplicationScoped
public class GameTransactionApp extends RouteBuilder {

    @Inject
    GameTransactionService gameTransactionService;

    @Override
    public void configure() {
        Item item = new Item("Dragon Slayer", 1, "Legendary", 40, 100);

        PlayerAccount playerAccount = new PlayerAccount("1234", "Guts", 1000, 150);

        TransactionDetails transactionDetails = new TransactionDetails(item, playerAccount);



        from("platform-http:/startTransaction")
                .process(exchange -> {
                    System.out.println("----------------------");
                    String transactionId = UUID.randomUUID().toString();

                    exchange.getIn().setHeader("TransactionId", transactionId);
                    exchange.getIn().setBody(transactionDetails);

                    System.out.println("Transaction Starting ..." +
                            "\n TransactionId: " + transactionId
                            + "\n Name: " + playerAccount.getPlayerName() + "\n Credit: " + playerAccount.getCredit()
                            + "\n Item: " + item.name() + "\n Price: " + item.price()
                            + "\n");
                })
                .to("direct:debitPlayerAccount")
                .to("direct:addItemToInventory")
                .process(exchange -> {
                    String response = "Transaction successfully completed. \n TransactionId: " +
                            exchange.getIn().getHeader("TransactionId");
                    exchange.getIn().setBody(response);
                });

        from("direct:debitPlayerAccount")
                .process(exchange -> {
                    logTransaction(exchange, "\nDebiting the player's account...");
                    gameTransactionService.debitAccount(playerAccount, item);
                });

        from("direct:addItemToInventory")
                .process(exchange -> {
                    logTransaction(exchange, "\nAdd item to inventory...");
                    gameTransactionService.addItemToInventory(playerAccount, item);
                });

    }

        private void logTransaction(Exchange exchange, String action) {
        String transactionId = exchange.getIn().getHeader("TransactionId", String.class);
        if (transactionId != null) {
            System.out.println(action + "\n- Transaction ID: " + transactionId);
        } else {
            System.out.println(action + "\n- Transaction ID est nul");
        }
    }
}

