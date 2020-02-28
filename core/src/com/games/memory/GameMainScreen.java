package com.games.memory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMainScreen implements Screen {
    Game newGame;
    private SpriteBatch batch;
    private MemoryGame game;
    private BitmapFont font;


    public GameMainScreen(MemoryGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.setColor(Color.RED);
        batch = new SpriteBatch();
        newGame = new Game();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.7f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
            int x1 = 20, y1 = 50;
            for (Card c: newGame.getGlobalList()) {
                c.setX(x1);
                c.setY(y1);
                newGame.startGame(c);
                if(newGame.isGameIsFinished()) {
                    batch.draw(newGame.getGameFinishSprite(), 250, 350);
                    font.draw(batch, "Your score is " + newGame.getScore(), 20, 600);
                }
                batch.draw(c.getCardSprite(), x1, y1);
                x1 += 90;
                if(x1 >= MemoryGame.WIDTH-80) {
                    x1 = 20;
                    y1 += 130;
                }
            }


        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
