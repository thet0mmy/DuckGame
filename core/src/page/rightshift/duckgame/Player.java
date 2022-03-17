package page.rightshift.duckgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Actor {

    Player(Texture t) {
        this.tex = t;
        this.rect = new Rectangle();
        this.rect.setWidth(this.tex.getWidth());
        this.rect.setHeight(this.tex.getHeight());
    }
}
