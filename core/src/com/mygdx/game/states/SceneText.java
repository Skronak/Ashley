package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGame;

/**
 * Template de scene proposant des choix multiples
 */
// TODO: Classe dans laquelle sera affiché le texte. Non utilisée pour le moment
public class SceneText implements Screen {

    Stage stage;
    Texture background;
    MyGame game;
    Skin skin;
    BitmapFont fontmessage;
    int sceneId;
    String[] sceneParam;
    String text;
    String sceneType;
    String backgroundFile;
    private Table table;//ajout Table

    /**
     * Constructeur du SceneChoix
     * @param g
     * @param id
     */
    public SceneText(MyGame g, int id) {
        this.game = g;
        this.sceneId = id;
        fontmessage= new BitmapFont();
    }

    /**
     * Affichage de la scene
     */
    @Override
    public void show() {
        stage = new Stage();

        // Initialisation des parametres
        initScene();

        //Gestion des input
        Gdx.input.setInputProcessor(stage);
        //Skin
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // creation de la table
        table = new Table();//ajout Table
        table.setWidth(stage.getWidth());//ajout Table
        table.align(Align.top);//ajout Table
        table.setPosition(0, Gdx.graphics.getHeight());//ajout Table
        Label label = new Label(text, skin);
        label.setAlignment(0);
        label.setWidth(table.getWidth());
        //label.setWrap(true);
        //label.setAlignment();
        table.add(label).width(stage.getWidth()).height(stage.getHeight()/2);
        table.row();
        table.add(new Label("YOLO",skin)).width(stage.getWidth()).height(stage.getHeight()/2);
        table.debugAll();
        stage.addActor(table);//ajout Table

        }

    /**
     * Methode d'initialisation des variables de la scene via
     * les parametres du fichier scene
     */
    public void initScene() {
        sceneParam = game.getSceneFileManager().getScene(Integer.valueOf(sceneId)).split(";");
        this.sceneType=sceneParam[1];
        this.text = sceneParam[2];
        this.backgroundFile = sceneParam[3];
        background = new Texture(Gdx.files.internal(backgroundFile.concat(".jpg")));
    }

    /**
     * Methode de creation d'un bouton
     * @param text
     * @return
     */
    public TextButton createButton(String text, final int nextId) {
        final TextButton button = new TextButton(text, skin);

        ClickListener clikListnener = new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                SceneText sceneChoix = new SceneText(game, nextId);
                game.setScreen(sceneChoix);
                return false;
            }
        };
        button.addListener(clikListnener);
        return button;
    }




    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.5f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //fontmessage.draw(stage.getBatch(), text, 250, 400, 300, 1, true);
        stage.getBatch().end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        //Table.drawDebug(stage);
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
