package atomkraftzwerg.javamaumau;

import atomkraftzwerg.javamaumau.elements.Card;
import atomkraftzwerg.javamaumau.elements.CardStack;
import atomkraftzwerg.javamaumau.elements.Player;

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
        System.out.println("Topmost card is " + card + ".");
    }

    static void gameBegins() {
        System.out.println("The game now begins.");
    }

    static void playersCardsAre(Player player) {
        CardStack hand = player.getHand();

        System.out.println("Player " + player.getName() + " has " + hand.size() + " cards which are: " + hand);
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
        System.out.println("Yay, " + activePlayer.getName() + " card " + matchingCard + " matching to " + topmostCard + " and places it!");
    }

    static void playerWon(Player winningPlayer) {
        System.out.println("Congratulations, player " + winningPlayer.getName() + " won!");
    }

}
