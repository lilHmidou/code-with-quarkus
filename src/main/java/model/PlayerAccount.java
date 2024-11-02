package model;

public class PlayerAccount {
    private String playerId;
    private String playerName;
    private int credit;
    private int inventorySize;

    public PlayerAccount(String playerId, String playerName, int credit, int inventorySize) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.credit = credit;
        this.inventorySize = inventorySize;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
