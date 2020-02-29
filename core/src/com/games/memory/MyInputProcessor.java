package com.games.memory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

class MyInputProcessor  {
    private int x;
    private int y;

    boolean click() {
        if(Gdx.input.justTouched()) {
            x = Gdx.input.getX();
            y = Gdx.input.getY();
            return true;
        }
        return false;
    }

    boolean cardClicked(Card card) {
        int mouseY = MemoryGame.HEIGHT - y;
        return !card.isVisibility() && (x > card.getX() && x < card.getX() + card.WIDTH) &&
                (mouseY > card.getY() && mouseY < card.getY() + card.HEIGHT);
    }

    boolean spriteClicked(Sprite sprite) {
        int mouseY = MemoryGame.HEIGHT - y;
        return (x > sprite.getX() && x < sprite.getX() + sprite.getWidth()) &&
                (mouseY > sprite.getY() && mouseY < sprite.getY() + sprite.getHeight());
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
