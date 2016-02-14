package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.Accueil;
import com.mygdx.game.utility.SceneFileManager;

import java.util.ArrayList;

public class MyGame extends Game {

    Accueil accueil;
    SpriteBatch batch;
    AssetManager manager;
    ArrayList<String> liste;
    SceneFileManager sceneFileManager;


    /**
     * Initialisation du jeu
     */
    @Override
	public void create () {
        batch = new SpriteBatch();
        manager = new AssetManager();
        sceneFileManager = new SceneFileManager();
        accueil = new Accueil(this);

        setScreen(accueil);
    }

    /**
     * Liberation de la memoire
     */
    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
        batch.dispose();
    }

    /**
     *getter du SceneFileManager
     * @return
     */
    public SceneFileManager getSceneFileManager() {

        return sceneFileManager;
    }

    /**
     * setter du SceneFileManager
     * @param sceneFileManager
     */
    public void setSceneFileManager(SceneFileManager sceneFileManager) {
        this.sceneFileManager = sceneFileManager;
    }
}
