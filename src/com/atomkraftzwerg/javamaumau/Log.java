package com.atomkraftzwerg.javamaumau;

import com.atomkraftzwerg.javamaumau.elements.Card;
import com.atomkraftzwerg.javamaumau.elements.CardStack;
import com.atomkraftzwerg.javamaumau.elements.Player;

class Log {

    static void welcome() {
        System.out.println("Hello and welcome to Mau Mau!");
    }

    static void playerJoins(Player player) {
        System.out.println("Player " + player.getName() + " joins the game.");
    }

    static void talonCreated() {
        System.out.println("Cards are shuffled and placed on the talon.");
    }

    static void topmostCardIs(Card card) {
        System.out.println("Topmost card is:");
        System.out.println(CardPrinter.printCard(card));
    }

    static void gameBegins() {
        System.out.println("The game now begins.");
    }

    static void playersCardsAre(Player player) {
        CardStack hand = player.getHand();

        System.out.println("[DEBUG] Player " + player.getName() + " has " + hand.size() + " cards which are:");
        System.out.println();
        System.out.println(CardPrinter.printCardList(hand.getCards()));
    }

    static void itIsNowTurnOf(Player activePlayer, int roundCounter) {
        System.out.println();
        System.out.println("It is now turn of player " + activePlayer.getName() + " (round " + roundCounter + ").");
    }

    static void playerPicksCardFromTalon(Player activePlayer, Card pickedCard) {
        System.out.println("Player " + activePlayer.getName() + " picks card " + pickedCard + " from the talon.");
    }

    static void playerGetsNumCards(int numInitialCards) {
        System.out.println("Every player gets " + numInitialCards + " initial cards from the talon.");
    }

    static void noMatchingCardFound(Card topmostCard) {
        System.out.println("Dang, found no matching card for " + topmostCard + ".");
    }

    static void matchingCardFound(Player activePlayer, Card matchingCard, Card topmostCard) {
        System.out.println("Yay, " + activePlayer.getName() + " has a matchin card and places it:");
        System.out.println(CardPrinter.printCardPlacing(matchingCard, topmostCard));
    }

    static void playerWon(Player winningPlayer) {
        System.out.println("Congratulations, player " + winningPlayer.getName() + " won!");
    }

}
