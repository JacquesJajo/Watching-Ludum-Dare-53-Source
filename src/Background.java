import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public abstract class Background {

    protected Main game;

    public Background(Main game) {
        this.game = game;
    }

    public abstract void init(GameContainer gc);
    public abstract void update(GameContainer gc, int delta);
    public abstract void render(GameContainer gc, Graphics g);
}
