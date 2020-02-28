package com.games.memory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Game  {

    private final int DECKS_COUNT = 2;
    private final int CARDS_COUNT = 3;
    private boolean equality;
    private Card card1, card2;
    private MyInputProcessor inputProcessor;
    private List<Deck> deckList;
    private int countOfPairs;

    private List<Card> globalList;
    private Deck deck, anotherDeck;

    Game(){
        inputProcessor = new MyInputProcessor();
        deckList = new LinkedList<>();

        deck = new Deck(CARDS_COUNT);
        globalList = new LinkedList<>();
        for (Card c : deck.getCardsList()) {
            c.setVisibility(false);
            c.setImageToCard();
        }
        for (int i = 0; i < DECKS_COUNT; i++) {
            deckList.add(deck);
            try {
                anotherDeck = (Deck) deck.clone();
                anotherDeck.shuffle();
                globalList.addAll(anotherDeck.getCardsList());
                deck = anotherDeck;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }


        Collections.shuffle(globalList);
    }

    void startGame(Card card) {
        if(countOfPairs == CARDS_COUNT) {
            System.out.println("WIN");
        }
        if (card1 == null) {
            card1 = openIfClicked(card);
        } else if(card2 == null) {
            if(card == card1) return;
            card2 = openIfClicked(card);
            compare(card1, card2);
        } else {
            openIfClicked(card);
        }
    }

    private void compare(Card c1, Card c2) {
        equality = c1.equals(c2);
    }

    private Card openIfClicked(Card card) {
        if (inputProcessor.leftClick()) {
            int mouseY = MemoryGame.HEIGHT - inputProcessor.getY();
            if ( (inputProcessor.getX() > card.getX() && inputProcessor.getX() < card.getX() + card.WIDTH) &&
                    (mouseY > card.getY() && mouseY < card.getY() + card.HEIGHT)) {
                if(card1 != null && card2 != null) {
                    if (!equality) {
                        closeCard(card1);
                        closeCard(card2);
                    } else {
                        countOfPairs++;
                    }

                    card1 = card;
                    card2 = null;
                }
                openCard(card);
                return card;
            }
        }
        return null;
    }

    private void openCard(Card card) {
        card.setVisibility(true);
        card.setImageToCard();
    }

    private void closeCard(Card card) {
        card.setVisibility(false);
        card.setImageToCard();
    }

    List<Deck> getDeckList() {
        return deckList;
    }

    public List<Card> getGlobalList() {
        return globalList;
    }
}