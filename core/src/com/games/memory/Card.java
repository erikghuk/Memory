package com.games.memory;


import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.*;

public class Card implements Cloneable {
    private int x, y;
    public final int WIDTH = 69;
    public final int HEIGHT = 106;
    private String faceImageLink;
    private final String BACK_IMAGE_URL = "images/PNG/green_back.jpg";
    private SpriteController spriteController;
    private Sprite cardSprite;


    private boolean visibility;
    private List<CARDTYPE> cardTypeList;
    private List<CARDNUMBER> cardNumberList;
    private CARDTYPE cardType;
    private CARDNUMBER cardNumber;


    Card() {
        spriteController = new SpriteController();

        Random randomCard = new Random();
        cardTypeList = new ArrayList<>(EnumSet.allOf(CARDTYPE.class));
        cardNumberList = new ArrayList<>(EnumSet.allOf(CARDNUMBER.class));

        cardType = cardTypeList.get(randomCard.nextInt(cardTypeList.size()));
        cardNumber = cardNumberList.get(randomCard.nextInt(cardNumberList.size()));
        faceImageLink = "images/PNG/" + cardNumber.getValue() + cardType + ".jpg";
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public void setImageToCard() {
        cardSprite = visibility ? spriteController.initSprite(faceImageLink) : spriteController.initSprite(BACK_IMAGE_URL);
    }

    private enum CARDTYPE {
        /*PIQUES*/    S,
        /*TREFLES*/    C,
        /*CARREAUX*/  D,
        /*COEURS*/   H;
    }

    private enum CARDNUMBER {
        TWO("2"), TREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
        JACK("J"), QUEEN("Q"), KING("K"), ACE("A");

        private String value;

        CARDNUMBER(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    public CARDTYPE getCardType() {
        return cardType;
    }

    public CARDNUMBER getCardNumber() {
        return cardNumber;
    }

    public String getFaceImageLink() {
        return faceImageLink;
    }

    public void setFaceImageLink(String faceImageLink) {
        this.faceImageLink = faceImageLink;
    }

    public Sprite getCardSprite() {
        return cardSprite;
    }

    public void setCardSprite(Sprite cardSprite) {
        this.cardSprite = cardSprite;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Card {" +
                "cardType: " + cardType +
                ", cardNumber: " + cardNumber +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (cardType != card.cardType) return false;
        return cardNumber == card.cardNumber;
    }

    @Override
    public int hashCode() {
        int result = cardType != null ? cardType.hashCode() : 0;
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
