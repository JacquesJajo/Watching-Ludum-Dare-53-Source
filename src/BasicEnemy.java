import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

public class BasicEnemy extends Enemy {
    private Animation animation;

    public BasicEnemy(Main game, float x, float y, float z) {
        super(game, x, y, z);
        animation = new Animation(new Image[] {Assets.winged_eye1, Assets.winged_eye2}, 250);
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (!active) return;

        if (animation.isStopped()) {
            animation.start();
        }

        accelerate(0.0f, 0.0f, -0.005f / delta);
        //y += y_vel / delta;
        z += z_vel * 0.1f * delta;

        if (playerIntersection()) {
            System.out.println("hit by basic");
            active = false;
            game.getPlayer().damage();

        }

        if (z < 0) active = false;
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        if (!active) return;
        g.setColor(Color.white);
        //g.fillRect(((0.5f + ((x-0.05f)/z)) * gc.getWidth()), ((0.5f + ((y-0.05f)/z)) * gc.getHeight()), gc.getWidth()*0.05f/z, gc.getScreenHeight()*0.05f/z);
        animation.draw(((0.5f + ((x-0.05f)/z)) * gc.getWidth()), ((0.5f + ((y-0.05f)/z)) * gc.getHeight()), gc.getWidth()*0.3f/z, gc.getScreenHeight()*0.15f/z);
    }
}
