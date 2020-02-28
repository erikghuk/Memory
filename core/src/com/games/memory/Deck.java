package com.games.memory;


import java.util.*;


public class Deck implements Cloneable {
    private final int MAX_SIZE = 52;
    private int count;
    private Set<Card> cards;
    private List<Card> cardsList;

    Deck(int count) {
        this.count = count >= 0 && count <= MAX_SIZE ? count : 0;
        cards = new HashSet<>();

        while (true) {
            cards.add(new Card());
            if(cards.size() == count) {
                break;
            }
        }
        cardsList = new LinkedList<>(cards);
    }

    Deck() {
        cards = new HashSet<>();
        while (true) {
            cards.add(new Card());
            if(cards.size() == 52) {
                break;
            }
        }
        cardsList = new LinkedList<>(cards);
    }


    void shuffle() {
        Collections.shuffle(cardsList);
    }

    List<Card> getCardsList() {
        return cardsList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Deck d = (Deck) super.clone();
        d.cardsList = new ArrayList<>(cardsList.size());
        for (Card card : cardsList) {
            d.getCardsList().add((Card) card.clone());
        }
        return d;
    }
}
