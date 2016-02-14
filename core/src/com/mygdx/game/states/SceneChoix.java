package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGame;

/**
 * Template de scene proposant des choix multiples
 */
public class SceneChoix implements Screen {

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
    String button1Text;
    String button1NextId;
    String button2Text;
    String button2NextId;
    String button3Text;
    String button3NextId;
    String button4Text;
    String button4NextId;

    /**
     * Constructeur du SceneChoix
     * @param g
     * @param id
     */
    public SceneChoix(MyGame g, int id) {
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
        background = new Texture(Gdx.files.internal(backgroundFile.concat(".jpg")));
        //Gestion des input
        Gdx.input.setInputProcessor(stage);
        //Skin
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        //Options
        //Table table = new Table();
        //table.setFillParent(true);
        if ((!button1Text.isEmpty() || button3Text.isEmpty()) && (button1Text!=null || button3Text==null)) {
            TextButton bouton1 = createButton(button1Text, Integer.valueOf(button1NextId));
            bouton1.setPosition(50, 70);
            bouton1.setSize(200, 50);
            TextButton bouton2 = createButton(button2Text, Integer.valueOf(button2NextId));
            bouton2.setPosition(390, 70);
            bouton2.setSize(200,50);
            stage.addActor(bouton1);
            stage.addActor(bouton2);
        } else {
            if (!button3Text.isEmpty() || button3Text!=null) {
                if (button4Text.isEmpty()) {
                    TextButton bouton1 = createButton(button1Text, Integer.valueOf(button1NextId));
                    bouton1.setPosition(50, 70);
                    bouton1.setSize(200, 50);
                    TextButton bouton2 = createButton(button2Text, Integer.valueOf(button2NextId));
                    bouton2.setPosition(390, 70);
                    bouton2.setSize(200, 50);
                    TextButton bouton3 = createButton(button3Text, Integer.valueOf(button4NextId));
                    bouton3.setPosition(220,15);
                    bouton3.setSize(200, 50);
                    stage.addActor(bouton1);
                    stage.addActor(bouton2);
                    stage.addActor(bouton3);
                } else {
                    TextButton bouton1 = createButton(button1Text, Integer.valueOf(button1NextId));
                    bouton1.setPosition(50, 70);
                    bouton1.setSize(200, 50);
                    TextButton bouton2 = createButton(button2Text, Integer.valueOf(button2NextId));
                    bouton2.setPosition(390, 70);
                    bouton2.setSize(200, 50);
                    TextButton bouton3 = createButton(button3Text, Integer.valueOf(button3NextId));
                    bouton3.setPosition(50, 15);
                    bouton3.setSize(200, 50);
                    TextButton bouton4 = createButton(button4Text, Integer.valueOf(button4NextId));
                    bouton4.setPosition(390,15);
                    bouton4.setSize(200, 50);
                    stage.addActor(bouton1);
                    stage.addActor(bouton2);
                    stage.addActor(bouton3);
                    stage.addActor(bouton4);
                  }
            }
        }
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
        this.button1Text = sceneParam[4];
        this.button1NextId = sceneParam[5];
        this.button2Text = sceneParam[6];
        this.button2NextId = sceneParam[7];
        this.button3Text = sceneParam[8];
        this.button3NextId = sceneParam[9];
        this.button4Text = sceneParam[10];
        this.button4NextId = sceneParam[11];
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
                SceneChoix sceneChoix = new SceneChoix(game, nextId);
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
        fontmessage.setColor(Color.WHITE);
        fontmessage.draw(stage.getBatch(), text, 250, 400, 300, 1, true);

     // TODO: Test affichage d'un text, A deplacer dans le show
/*        String debug = "I took one, one cause you left me\n"
                + "Two, two for my family\n"
                + "Three, three for my heartache\n"
                + "Four, four for my headaches\n"
                + "Five, five for my sorrow\n";
        fontmessage.setColor(Color.BROWN);
        fontmessage.draw(stage.getBatch(), debug, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
*/
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
