import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Payload extends Entity {
    private float lifetime = 500.0f;
    private float end = 0.0f;
    private float speed = 0.5f;

    public Payload(Main game, float x, float y, float z) {
        super(game, x, y, z);
        z_vel = speed;
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (!active) return;
        if (end == 0.0f) {
            end = gc.getTime() + lifetime;
        }

        accelerate(0, 0, 0.001f * 0.1f * delta);

        z += z_vel * 0.1f * delta;

        if (gc.getTime() > end) {
            active = false;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        if (!active) return;
        g.setColor(Color.blue);
        Assets.payload.draw(((0.5f + ((x-0.1f)/z)) * gc.getWidth()), ((0.5f + ((y-0.1f)/z)) * gc.getHeight()), gc.getWidth()*0.2f/z, gc.getScreenHeight()*0.2f/z);
    }
}
