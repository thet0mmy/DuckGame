package page.rightshift.duckgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Actor {
    public Texture tex;
    public Rectangle rect;

    public void dispose() {
        this.tex.dispose();
    }
    Actor() {
        this.tex = new Texture("default.png");
    }

    protected void fixRectangle() {
        this.rect.setWidth(tex.getWidth());
        this.rect.setHeight(tex.getHeight());
    }

    public void update() { return; }

    Actor(Texture t) {
        this.tex = t;
        this.rect.width = t.getWidth();
        this.rect.height = t.getHeight();
    }
}
