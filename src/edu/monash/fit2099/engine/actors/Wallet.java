package edu.monash.fit2099.engine.actors;

/**
 * A class that represents a wallet that can be used to store the game's currency.
 * Example #1: the balance of the wallet can represent the amount of souls in the Dark Souls game.
 * Example #2: the balance of the wallet can also represent the amount of runes in the Elden Ring game.
 */
public class Wallet {
    private int balance;

    /**
     * Constructor.
     * Initializes the balance of the wallet to 0.
     */
    public Wallet() {
        this.balance = 0;
    }

    /**
     * Getter for the balance of the wallet.
     * @return the balance of the wallet
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Adds the given amount to the balance of the wallet.
     * @param amount the amount to be added to the balance of the wallet
     */
    public void addBalance(int amount) {
        this.balance += amount;
    }

    /**
     * Deducts the given amount from the balance of the wallet.
     * @param amount the amount to be deducted from the balance of the wallet
     */
    public void deductBalance(int amount) {
        this.balance -= amount;
    }
}
