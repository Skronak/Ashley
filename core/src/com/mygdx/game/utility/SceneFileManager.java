package com.mygdx.game.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestionnaire de fichier de scene.
 * Permet de renvoyer les variable d'une scene depuis le fichier scene
 * Created by Skronak on 13/02/2016.
 */
public class SceneFileManager {

    // Id de la scene a charger
    private int id;

    // Liste de string contenant les lignes du fichier
    private List<String> sceneFile = new ArrayList<String>();

    /**
     * Constructeur du SceneFileManager
     */
    public SceneFileManager() {
        loadFile();
    }


    /**
     * Methode de chargement initial du fichier
     * Lecture du fichier ligne par ligne et insertion dans une liste
     */
    private void loadFile() {
        try {
            FileHandle file = Gdx.files.internal("scene.txt");
            BufferedReader br = new BufferedReader(file.reader());
            for (String line; (line = br.readLine()) != null; ) {
                sceneFile.add(line);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // handle exception
        }
    }

    /**
     * Methode de renvoier des parametres d'une scene
     * @param id
     * @return String
     */
    public String getScene(int id) {
        return sceneFile.get(id);
    }
}
