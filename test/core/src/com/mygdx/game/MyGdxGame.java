package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.swing.*;

public class MyGdxGame extends ApplicationAdapter {
    SpriteBatch batch;
    Background bg;
    Bird bird;
    Obstacles obstacles;
    boolean gameOver;
    Texture restartTexture;
    private Texture start;
    boolean gameStart;
    public static final String title = "Flappy Bird";

    @Override
    public void create () {
        batch = new SpriteBatch();
        bg = new Background();
        gameStart = false;
        start = new Texture("start.png");
        bird = new Bird();
        obstacles = new Obstacles();
        gameOver = false;
        restartTexture = new Texture("restartBtn.png");
    }

    @Override
    public void render () {
        update();
        batch.begin();
        bg.render(batch);
        batch.draw(start, 160, 250);
        obstacles.render(batch);

        if(!gameOver) {
            bird.render(batch);
        }else{
            batch.draw(restartTexture, 110, 250);
        }
        batch.end();
    }



    public void update(){
        bg.update();
        bird.update();
        obstacles.update();
        for (int i = 0; i < Obstacles.obs.length; i++) {
            if(bird.position.x > Obstacles.obs[i].position.x && bird.position.x < Obstacles.obs[i].position.x+50){
                if(!Obstacles.obs[i].emptySpace.contains(bird.position)){
                    gameOver = true;
                }
            }
        }
        if(bird.position.y <0 || bird.position.y > 600){
            gameOver = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && gameOver){
            recreate();
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
    }

    public void recreate(){
        bird.recreate();
        obstacles.recreate();
        gameOver = false;
    }
}