package com.atomkraftzwerg.javamaumau.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CardStack {
    private List<Card> cards;

    private CardStack() {
        this.cards = new ArrayList<>();
    }

    public static CardStack fullSet() {
        CardStack result = new CardStack();
        result.addAllCards();

        return result;
    }

    public List<Card> getCards() {
        return cards;
    }

    static CardStack empty() {
        return new CardStack();
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card pickCard() {
        return cards.remove(0); // TODO assert
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(Card::toString)
                .collect( Collectors.joining( ", " ) );
    }

    Card getMatchingCard(Card topmostCard) {
        for (Card card : cards) {
            if (card.matches(topmostCard)) {
                return card;
            }
        }

        return null;
    }

    void removeCard(Card card) {
        this.cards.remove(card);
    }

    private void addAllCards() {
        for (CardColors color : CardColors.values()) {
            for (CardValues value : CardValues.values()) {
                this.addCard(new Card(color, value));
            }
        }
    }
}
