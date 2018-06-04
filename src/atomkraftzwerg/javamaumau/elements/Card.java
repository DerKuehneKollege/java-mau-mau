package atomkraftzwerg.javamaumau.elements;

import java.util.HashMap;
import java.util.Map;

public class Card {
    public static final Map<CardColors, String> cardColorSigns;
    static
    {
        cardColorSigns = new HashMap<>();
        cardColorSigns.put(CardColors.Club,    "♣");
        cardColorSigns.put(CardColors.Diamond, "♦");
        cardColorSigns.put(CardColors.Heart,   "♥");
        cardColorSigns.put(CardColors.Spade,   "♠");
    }

    public static final Map<CardValues, String> cardValueSigns;
    static
    {
        cardValueSigns = new HashMap<>();
        cardValueSigns.put(CardValues.Ace,   "A");
        cardValueSigns.put(CardValues.One,   "1");
        cardValueSigns.put(CardValues.Two,   "2");
        cardValueSigns.put(CardValues.Three, "3");
        cardValueSigns.put(CardValues.Four,  "4");
        cardValueSigns.put(CardValues.Five,  "5");
        cardValueSigns.put(CardValues.Six,   "6");
        cardValueSigns.put(CardValues.Seven, "7");
        cardValueSigns.put(CardValues.Eight, "8");
        cardValueSigns.put(CardValues.Nine,  "9");
        cardValueSigns.put(CardValues.Ten,   "10");
        cardValueSigns.put(CardValues.Jack,  "J");
        cardValueSigns.put(CardValues.Queen, "Q");
        cardValueSigns.put(CardValues.King,  "K");
    }

    private CardColors color;
    private CardValues value;

    Card(CardColors color, CardValues value) {
        this.color = color;
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Card)) {
            return false;
        }

        return this.color == ((Card) other).color && this.value == ((Card) other).value;
    }

    @Override
    public String toString() {
        // TODO validations
        return "[" + cardColorSigns.get(this.color) + " " + cardValueSigns.get(this.value) + "]";
    }

    public String toLongString() {
        return "[" + this.color.name() + " " + this.value.name() + "]";
    }

    public boolean matches(Card other) {
        return this.value == other.value || this.color == other.color;
    }

    public CardColors getColor() {
        return color;
    }

    public CardValues getValue() {
        return value;
    }
}
