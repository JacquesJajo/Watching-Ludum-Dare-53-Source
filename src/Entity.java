import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity {

    protected float x;
    protected float y;
    protected float z;

    protected float x_vel;
    protected float y_vel;
    protected float z_vel;

    protected boolean active = true;

    protected Main game;

    public Entity(Main game, float x, float y, float z) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public abstract void update(GameContainer gc, int delta);
    public abstract void render(GameContainer gc, Graphics g);

    public void set_velocity(float x_vel, float y_vel, float z_vel) {
        this.x_vel = x_vel;
        this.y_vel = y_vel;
        this.z_vel = z_vel;
    }

    // Ensure to relate to delta
    public void accelerate(float x_acc, float y_acc, float z_acc) {
        x_vel += x_acc;
        y_vel += y_acc;
        z_vel += z_acc;
    }

    public boolean inRadius(float radius, Entity o) {
        return (x-o.x)*(x-o.x) + (y-o.y)*(y-o.y) + (z-o.z)*(z-o.z) < radius*radius && o.active;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
