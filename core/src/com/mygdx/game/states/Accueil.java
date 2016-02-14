package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyGame;

/**
 * Created by PC on 13/11/2015.
 */
public class Accueil implements Screen {

    Stage stage;
    Texture background;
    MyGame game;
    Skin skin;
    //SceneChoixOld sceneChoix;

    public Accueil(MyGame g) {
        this.game = g;
    }

    @Override
    public void show() {
        background = new Texture(Gdx.files.internal("ville2.jpg"));

        //Stage pb redimensions
        stage = new Stage();


        //Gestion des input
        Gdx.input.setInputProcessor(stage);

        //Skin
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        Label titre = new Label("Interface de jeu", skin);
        TextButton bouton = new TextButton("quitter",skin);
        bouton.setPosition(200, 100);
        //Click Listener
        ClickListener buttonListnener = new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return false;
            }
        };
        bouton.addListener(buttonListnener);

        TextButton bouton2 = new TextButton("jouer", skin);
        bouton2.setPosition(200, 150);
        //Click Listener
        ClickListener button2Listnener2 = new ClickListener(){
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                SceneChoix sceneChoix = new SceneChoix(game, 1);
                game.setScreen(sceneChoix);

            }
        };
        bouton2.addListener(button2Listnener2);

        //Widget
        stage.addActor(titre);
        stage.addActor(bouton);
        stage.addActor(bouton2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.5f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
        stage.dispose();
        background.dispose();
    }


}
