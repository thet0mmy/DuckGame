package page.rightshift.duckgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class DuckGame extends ApplicationAdapter {
	SpriteBatch mainBatch;
	SpriteBatch uiBatch;

	OrthographicCamera camera;
	Player player;
	Array<Egg> eggs;

	Stage stage;
	BitmapFont font;
	Label label;
	Label.LabelStyle style;

	long lastEggTime;
	
	@Override
	public void create () {
		mainBatch = new SpriteBatch();
		uiBatch = new SpriteBatch();

		player = new Player(new Texture("duck.png"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		eggs = new Array<Egg>();

		stage = new Stage();
		font = new BitmapFont();
		style = new Label.LabelStyle();
		style.font = font;
		label = new Label("test", style);
		label.setX(0);
		label.setY(460);
		stage.addActor(label);
	}

	public void newEgg() {
		Egg newEgg = new Egg();
		newEgg.fixRectangle(); // just to be sure
		eggs.add(newEgg);
		lastEggTime = TimeUtils.nanoTime();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.35f, 0.35f, 1, 1);
		if(TimeUtils.nanoTime() - lastEggTime > 1000000000) newEgg();

		uiBatch.begin();
		stage.draw();
		uiBatch.end();

		mainBatch.setProjectionMatrix(camera.combined);
		mainBatch.begin();
		for(Egg egg: eggs) {
			mainBatch.draw(egg.tex, egg.rect.getX(), egg.rect.getY());
		}
		mainBatch.draw(player.tex, player.rect.getX(), player.rect.getY());
		mainBatch.end();

		if(Gdx.input.isKeyPressed(Input.Keys.UP)) player.rect.y += 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) player.rect.y -= 200 * Gdx.graphics.getDeltaTime();

		for(Iterator<Egg> eggIterator = eggs.iterator(); eggIterator.hasNext(); ) {
			Egg egg = eggIterator.next();
			egg.update();
			if(egg.rect.x + 48 < 0) {
				eggIterator.remove();
			}
		}
	}
	
	@Override
	public void dispose () {
		mainBatch.dispose();
		player.dispose();
		for(Egg egg: eggs) {
			egg.dispose();
		}
	}
}
