import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class CavernBackground extends Background {

    private float scroll = 0.0f;
    private float scroll_speed = 3.0f;

    private boolean scrolling = true;
    public CavernBackground(Main game) {
        super(game);
    }

    @Override
    public void init(GameContainer gc) {

    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (scrolling)
            scroll += scroll_speed * 0.1f * delta;
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        Assets.rocks.draw(0, 0, gc.getWidth(), gc.getHeight(), 0.0f, scroll, gc.getWidth(), gc.getHeight()+scroll);
        Assets.rocks.draw(gc.getWidth()*0.25f, 0, gc.getWidth()*0.75f, gc.getHeight(), 0.0f, scroll*0.75f,
                gc.getWidth()*0.5f, gc.getHeight()+scroll*0.75f, Color.gray);
        Assets.rocks.draw(gc.getWidth()*0.375f, 0, gc.getWidth()*0.625f, gc.getHeight(), 0.0f, scroll*0.5f,
                gc.getWidth()*0.5f, gc.getHeight()+scroll*0.5f, Color.darkGray);
    }

    public void setScrolling(boolean scrolling) {
        this.scrolling = scrolling;
    }

    public void flipScrollSpeed() {
        this.scroll_speed = -scroll_speed;
    }
}
