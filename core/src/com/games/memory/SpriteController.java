package com.games.memory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteController {
    private  Texture texture;
    private  Sprite sprite;



    public  Sprite initSprite(String texturePath) {
        texture = new Texture(texturePath);
        sprite = new Sprite(texture);
        return sprite;
    }



    public  void disposeSprite() {
        texture.dispose();
    }

}