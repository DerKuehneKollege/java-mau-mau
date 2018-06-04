package atomkraftzwerg.javamaumau.elements;

import java.util.HashMap;
import java.util.Map;

public class Card {
    private static final Map<CardColors, String> cardColorSigns;
    static
    {
        cardColorSigns = new HashMap<>();
        cardColorSigns.put(CardColors.Club,    "♣");
        cardColorSigns.put(CardColors.Diamond, "♦");
        cardColorSigns.put(CardColors.Heart,   "♥");
        cardColorSigns.put(CardColors.Spade,   "♠");
    }

    private static final Map<CardValues, String> cardValueSigns;
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

    public String graphic() {
        String colorSign = cardColorSigns.get(this.color);
        String valueSign = cardValueSigns.get(this.value);
        String space = valueSign.length() > 1 ? "" : " ";

        StringBuilder builder = new StringBuilder(7 * 12);
        builder.append("┌─────────┐\n");
        builder.append("│ ").append(valueSign).append(space).append("      │\n");
        builder.append("│         │\n");
        builder.append("│    ").append(colorSign).append("    │\n");
        builder.append("│         │\n");
        builder.append("│      ").append(space).append(valueSign).append(" │\n");
        builder.append("└─────────┘\n");

        return builder.toString();
    }

    public String hiddenCard() {
        return "┌─────────┐\n"
             + "│░░░░░░░░░│\n"
             + "│░░░░░░░░░│\n"
             + "│░░░░░░░░░│\n"
             + "│░░░░░░░░░│\n"
             + "│░░░░░░░░░│\n"
             + "└─────────┘\n";
    }

    public boolean matches(Card other) {
        return this.value == other.value || this.color == other.color;
    }
}
