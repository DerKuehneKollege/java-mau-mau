package atomkraftzwerg.javamaumau;

import atomkraftzwerg.javamaumau.elements.Card;
import atomkraftzwerg.javamaumau.elements.CardStack;
import atomkraftzwerg.javamaumau.elements.Player;

import java.util.*;

public class Game {

    public final boolean DEBUG = true;
    public final int NUM_INITIAL_CARDS = 5;
    public final boolean RULE_PULL_TWO_CARDS_ON_7 = false;
    public final boolean RULE_MISS_ON_8 = false;
    public final boolean RULE_WISH_CARD_ON_JACK = false;
    public final boolean RULE_NO_JACK_ON_JACK = false;
    public final boolean SAY_MAU_ON_LAST_CARD = false;

    private List<Player> players;
    private Player humanPlayer; // TODO option
    private Player activePlayer; // TODO option

    private CardStack talon; // TODO option
    private Card topmostCard; // TODO option
    private int roundCounter;
    private Iterator<Player> playerIterator;

    Game() {
        players = new ArrayList<>();

        Log.welcome();
    }

    void addPlayer(String name) {
        Player p = new Player(name);
        players.add(p);

        Log.playerJoins(p);

        // TODO only up to 5 players possible
    }

    void addHumanPlayer(String name) {
        Player p = new Player(name);
        humanPlayer = p;
        players.add(p);

        Log.playerJoins(p);

        // TODO only up to 5 players possible
    }

    void start() {
        createTalon();

        Log.playerGetsNumCards(NUM_INITIAL_CARDS);
        this.handOutCardsToPlayer();

        topmostCard = talon.pickCard();
        Log.gameBegins();

        this.roundCounter = 1;
        playerIterator = players.iterator();

        while (getWinningPlayer() == null) {
            // next turn
            nextTurn();

            // check if one of the players card matches
            if (activePlayer.hasMatchingCardTo(topmostCard)) {
                matchingCardFound();
            } else {
                noMatchingCardFound();
            }

            // infinity loop prevention
            if (++roundCounter >= 150) {
                break;
            }
        }

        gameEnds();
    }

    private void createTalon() {
        // fill the talon with a shuffled full set of cards
        talon = CardStack.fullSet();
        talon.shuffle();
        Log.talonCreated();
    }

    private void handOutCardsToPlayer() {
        for (Player player : players) {
            for (int i = 0; i < NUM_INITIAL_CARDS; i++) {
                player.pickCardFrom(talon);
            }

            if (DEBUG || player == humanPlayer) {
                Log.playersCardsAre(player);
            }
        }
    }

    private void nextTurn() {
        getNextPlayer();

        Log.itIsNowTurnOf(activePlayer, roundCounter);
        Log.topmostCardIs(topmostCard);

        System.out.println(topmostCard.graphic());

        if (DEBUG || activePlayer == humanPlayer) {
            Log.playersCardsAre(activePlayer);
        }
    }

    private void getNextPlayer() {
        if (!playerIterator.hasNext()) {
            // cycle
            playerIterator = players.iterator();
            // TODO assert that now playerIterator.hasNext() is true
        }
        activePlayer = playerIterator.next();
    }

    private boolean activePlayerIsHuman() {
        return activePlayer == humanPlayer;
    }

    private void matchingCardFound() {
        Card matchingCard;

        if (!activePlayerIsHuman()) {
            // a matching card was found, remove card from players card stack
            matchingCard = activePlayer.pickMatchingCard();
        } else {
            List<Card> hand = activePlayer.getHand().getCards();
            int handSize = hand.size();
            Map<String, Card> cardSelection = new HashMap<>();

            StringBuilder output = new StringBuilder("Your cards: ");

            for (int cardNr = 1; cardNr <= handSize; cardNr++) {
                Card currCard = hand.get(cardNr - 1);

                output.append("\n(").append(cardNr).append(") for ").append(currCard);
                cardSelection.put(Integer.toString(cardNr), currCard);
            }

            output.append("\n");

            do {
                System.out.println(output.toString());

                String input = ConsoleHelper.readLine("Please select the card you want to place by the numbers above: ").trim();

                if (!cardSelection.containsKey(input)) {
                    System.out.println("Invalid input, please try again!");
                    continue;
                }

                matchingCard = cardSelection.get(input);
                if (!matchingCard.matches(topmostCard)) {
                    System.out.println("You cannot put " + matchingCard + " on " + topmostCard + ", color and/or value must be the same.");
                    continue;
                }

                break;

            } while (true);

            // a matching card was found, remove card from players card stack
            activePlayer.pickCard(matchingCard);
        }

        Log.matchingCardFound(activePlayer, matchingCard, topmostCard);

        // it is now topmost card, put the current topmost card back to the talon
        talon.addCard(topmostCard);

        // set matching card as topmost card now
        topmostCard = matchingCard;
    }

    private void noMatchingCardFound() {
        Log.noMatchingCardFound(topmostCard);

        if (activePlayerIsHuman()) {
            ConsoleHelper.readLine("Please press Return to continue...");
        }

        // no matching card was found, player has to pick one
        Card pickedCard = activePlayer.pickCardFrom(talon);

        Log.playerPicksCardFromTalon(activePlayer, pickedCard);
    }

    private Player getWinningPlayer() {
        for (Player player : players) {
            if (player.getHand().isEmpty()) {
                return player;
            }
        }

        return null;
    }

    private void gameEnds() {
        Player winningPlayer = getWinningPlayer();
        if (winningPlayer != null) {
            Log.playerWon(winningPlayer);
        } else {
            System.out.println("[DEBUG] Game interrupted to avoid infinity loop.");
        }
    }
}
