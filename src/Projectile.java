import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Projectile extends Entity {

    private float lifetime = 500.0f;
    private float end = 0.0f;

    private float speed = 0.5f;

    public Projectile(Main game, float x, float y, float z) {
        super(game, x, y, z);
        z_vel = speed;
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (!active) return;
        if (end == 0.0f) {
            end = gc.getTime() + lifetime;
        }

        accelerate(0, 0, 0.001f / delta);

        z += z_vel * 0.1f * delta;
        for (int i = 0; i < game.getEntityManager().getList().size(); i++) {
            Entity e = game.getEntityManager().getList().get(i);
            if (inRadius(0.3f, e) && Enemy.class.isInstance(e)) {
                active = false;
                Enemy enemy = (Enemy) e;
                enemy.kill();
                Assets.hurt.play();
            }
        }

        if (gc.getTime() > end) {
            active = false;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        if (!active) return;
        g.setColor(Color.yellow);
        Assets.projectile.draw(((0.5f + ((x-0.025f)/z)) * gc.getWidth()), ((0.5f + ((y-0.025f)/z)) * gc.getHeight()), gc.getWidth()*0.05f/z, gc.getScreenHeight()*0.05f/z);
    }
}
