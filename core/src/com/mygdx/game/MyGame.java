package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.states.Menu;

public class MyGame extends Game {

    Menu menu;

	@Override
	public void create () {
        menu = new Menu(this);
        setScreen(menu);
	}

}
