package com.games.memory;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Game  {
    private final int DECKS_COUNT = 4;
    private final int CARDS_COUNT = 11;

    private final String WIN_IMAGE_URL = "images/PNG/finish.png";
    private final String PLAGAIN_IMAGE_URL = "images/PNG/playAgain.jpg";
    private Sprite gameFinishSprite, playAgainSprite;
    private SpriteController spriteController;
    private Card firstOpenedCard, secondOpenCard;
    private MyInputProcessor inputProcessor;
    private Deck deck, anotherDeck;
    private List<Deck> deckList;
    private List<Card> globalList;
    private int countOfPairs;
    private int countOfFalses;
    private int score;
    private boolean equality;
    private boolean gameIsFinished;

    Game() {
        inputProcessor = new MyInputProcessor();
        deckList = new LinkedList<>();

        deck = new Deck(CARDS_COUNT);
        globalList = new LinkedList<>();
        for (Card c : deck.getCardsList()) {
            c.setVisibility(false);
        }
        for (int i = 0; i < DECKS_COUNT; i++) {
            deckList.add(deck);
            try {
                anotherDeck = (Deck) deck.clone();
                globalList.addAll(anotherDeck.getCardsList());
                deck = anotherDeck;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        Collections.shuffle(globalList);
    }

    void startGame(Card entireCard) {
        // is game finished
        if(countOfPairs == CARDS_COUNT * DECKS_COUNT/2) {
            finish();
            return;
        }
        if (firstOpenedCard == null) {
            firstOpenedCard = openIfClicked(entireCard);
        } else if(secondOpenCard == null) {
            if(entireCard == firstOpenedCard) return;
            secondOpenCard = openIfClicked(entireCard);
            compare(firstOpenedCard, secondOpenCard);
        } else {
            openIfClicked(entireCard);
        }
    }

    private void finish() {
        spriteController = new SpriteController();
        gameFinishSprite = spriteController.initSprite(WIN_IMAGE_URL);
        playAgainSprite = spriteController.initSprite(PLAGAIN_IMAGE_URL);
        playAgainSprite.setX(450);
        playAgainSprite.setY(250);
        gameIsFinished = true;

        if(countOfFalses == 0)
            countOfFalses = 1;
        score = ((DECKS_COUNT * CARDS_COUNT)*(DECKS_COUNT * CARDS_COUNT)/countOfFalses * 2);

    }

    private void compare(Card c1, Card c2) {
        equality = c1.equals(c2);
    }

    private Card openIfClicked(Card card) {
        // if there is click event
        if (inputProcessor.click()) {
            int mouseY = MemoryGame.HEIGHT - inputProcessor.getY();
            // if entire card is clicked and not opened
            if (inputProcessor.cardClicked(card)) {
                if(firstOpenedCard != null && secondOpenCard != null) {
                    if (!equality) {
                        firstOpenedCard.setVisibility(false);
                        secondOpenCard.setVisibility(false);
                    }
                    firstOpenedCard = card;
                    secondOpenCard = null;
                } else if(firstOpenedCard != null) {
                    if(firstOpenedCard.equals(card)) countOfPairs++;
                    else countOfFalses++;
                }
                card.setVisibility(true);
                return card;
            }
        }
        return null;
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

    public Sprite getPlayAgainSprite() {
        return playAgainSprite;
    }

    public int getScore() {
        return score;
    }

    public MyInputProcessor getInputProcessor() {
        return inputProcessor;
    }
}