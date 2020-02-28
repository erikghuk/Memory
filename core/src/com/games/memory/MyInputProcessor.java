package com.games.memory;

import com.badlogic.gdx.Gdx;

class MyInputProcessor  {
    private int x;
    private int y;

    boolean leftClick() {
        if(Gdx.input.justTouched()) {
            x = Gdx.input.getX();
            y = Gdx.input.getY();
            return true;
        }
        return false;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
