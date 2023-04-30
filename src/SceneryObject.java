import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class SceneryObject extends Entity {

    private Image image;

    public SceneryObject(Main game, float x, float y, float z, Image image) {
        super(game, x, y, z);
        this.image = image;

        z_vel = -game.getCameraSpeed();
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (!active) return;

        z += z_vel * 0.1f * delta;

        if (z < 0.0f) {
            x = 8.0f*game.getRandom().nextFloat() - 4.0f;
            z = 50.0f + 8.0f*game.getRandom().nextFloat() - 4.0f;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        if (!active) return;

        image.draw(((0.5f + ((x-0.25f)/z)) * gc.getWidth()), ((game.getHorizon() + ((y-0.125f)/z)) * gc.getHeight()),
                gc.getWidth()*0.5f/z, gc.getScreenHeight()*0.25f/z);
    }
}
