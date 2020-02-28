package com.games.memory;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Game  {
    private final String WIN_IMAGE_URL = "images/PNG/finish.png";
    private final int DECKS_COUNT = 2;
    private final int CARDS_COUNT = 8;
    private boolean equality;
    private Card card1, card2;
    private MyInputProcessor inputProcessor;
    private List<Deck> deckList;
    private int countOfPairs;
    private int countOfFalses;
    private int score;
    private Sprite gameFinishSprite;
    private SpriteController spriteController;

    private List<Card> globalList;
    private Deck deck, anotherDeck;
    private boolean gameIsFinished;

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
        if(countOfPairs == CARDS_COUNT * DECKS_COUNT/2) {
            spriteController = new SpriteController();
            gameFinishSprite = spriteController.initSprite(WIN_IMAGE_URL);
            gameIsFinished = true;
            score = ((DECKS_COUNT * CARDS_COUNT)*(DECKS_COUNT * CARDS_COUNT)/countOfFalses * 2);
            System.out.println("You Win. You have a : " + score + " points");
            return;
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
            if (!card.isVisibility() && (inputProcessor.getX() > card.getX() && inputProcessor.getX() < card.getX() + card.WIDTH) &&
                    (mouseY > card.getY() && mouseY < card.getY() + card.HEIGHT)) {
                if(card1 != null && card2 != null) {
                    if (!equality) {
                        closeCard(card1);
                        closeCard(card2);
                    }

                    card1 = card;
                    card2 = null;
                } else if(card1 != null && card2 == null) {
                    if(card1.equals(card)) {
                        countOfPairs++;
                        System.out.println("countOfPairs - " + countOfPairs);
                    } else {
                        countOfFalses++;
                        System.out.println("countOfFalses - " + countOfFalses);
                    }
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

    public boolean isGameIsFinished() {
        return gameIsFinished;
    }

    public List<Card> getGlobalList() {
        return globalList;
    }

    public Sprite getGameFinishSprite() {
        return gameFinishSprite;
    }

    public void setGameFinishSprite(Sprite gameFinishSprite) {
        this.gameFinishSprite = gameFinishSprite;
    }

    public int getScore() {
        return score;
    }
}