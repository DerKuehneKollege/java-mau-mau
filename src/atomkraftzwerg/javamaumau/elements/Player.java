package atomkraftzwerg.javamaumau.elements;

public class Player {
    private String name;
    private CardStack hand;

    private Card matchingCardCache;

    public Player(String name) {
        this.name = name;
        this.hand = CardStack.empty();
    }

    public Card pickCardFrom(CardStack talon) {
        Card pickedCard = talon.pickCard();
        hand.addCard(pickedCard);

        return pickedCard;
    }

    public boolean hasMatchingCardTo(Card topmostCard) {
        this.matchingCardCache = hand.getMatchingCard(topmostCard);

        return this.matchingCardCache != null;
    }

    public Card pickMatchingCard() {
        // TODO validation necessary
        hand.removeCard(this.matchingCardCache);

        return matchingCardCache;
    }

    public Card pickCard(Card card) {
        hand.removeCard(card);

        return card;
    }

    public String getName() {
        return name;
    }

    public CardStack getHand() {
        return hand;
    }
}
