package page.rightshift.duckgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Egg extends Actor {
    Egg() {
        this.tex = new Texture("egg.png");
        this.rect = new Rectangle();
        this.fixRectangle();

        Random rand = new Random();
        this.rect.setY(rand.nextInt((320 - 48) + 1));
        this.rect.setX(640-48);
        System.out.println(this.rect.getY());
    }

    @Override
    public void update() {
        this.rect.x -= 300 * Gdx.graphics.getDeltaTime();
    }
}
