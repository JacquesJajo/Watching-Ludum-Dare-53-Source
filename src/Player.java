import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;
import java.awt.Font;

public class Player extends Entity {
    private float speed = 0.015f;

    private float crosshair_distance = 3.0f;

    private boolean can_deliver = false;
    private boolean fired_payload = false;

    private float payload_scene_length = 300;
    private float payload_scene_end;
    private Font font;
    private TrueTypeFont ttf;

    private float health;
    private boolean invulnerable = false;
    private float invulnerable_duration = 300;
    private float invulnerable_end;

    public Player(Main game, float x, float y, float z) {
        super(game, x, y, z);
        font = new Font("Verdana", Font.BOLD, 40);
        ttf = new TrueTypeFont(font, true);
        health = 15;
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (gc.getInput().isKeyDown(Input.KEY_W) && y > -0.45f) {
            y_vel = -speed;
        }
        else if (gc.getInput().isKeyDown(Input.KEY_S) && y < 0.45f) {
            y_vel = speed;
        }

        if (gc.getInput().isKeyDown(Input.KEY_A) && x > -0.45f) {
            x_vel = -speed;
        }
        else if (gc.getInput().isKeyDown(Input.KEY_D) && x < 0.45f) {
            x_vel = speed;
        }

        x += x_vel * 0.1f * delta;
        y += y_vel * 0.1f * delta;

        set_velocity(0,0,0);

        if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
            game.getEntityManager().add(new Projectile(game, x, y, z));
            Assets.shoot.play();
        }
        if (gc.getInput().isKeyPressed(Input.KEY_X) && can_deliver) {
            game.getEntityManager().add(new Payload(game, x, y, z));
            fired_payload = true;
            can_deliver = false;
            payload_scene_end = gc.getTime() + payload_scene_length;
            Assets.shoot.play();
            Assets.kill.play();
        }

        if (gc.getTime() > payload_scene_end && fired_payload) {
            game.end();
        }

        if (invulnerable && gc.getTime() > invulnerable_end) {
            invulnerable = false;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        // Draw Crosshair
        g.setColor(Color.yellow);
        g.drawOval((0.5f + ((x-0.05f)/(z+crosshair_distance))) * gc.getWidth(), (0.5f + ((y-0.05f)/(z+crosshair_distance))) * gc.getHeight(),
                gc.getWidth()*0.1f/(z+crosshair_distance), gc.getScreenHeight()*0.1f/(z+crosshair_distance));

        // Draw Player
        g.setColor(Color.cyan);
        Polygon shape = new Polygon();
        shape.addPoint(((0.5f + ((x-0.05f)/z)) * gc.getWidth()), ((0.5f + (y/z)) * gc.getHeight()));
        shape.addPoint(((0.5f + ((x+0.05f)/z)) * gc.getWidth()), ((0.5f + (y/z)) * gc.getHeight()));
        shape.addPoint(((0.5f + (x/(z+0.1f))) * gc.getWidth()), ((0.5f + (y/(z+0.1f))) * gc.getHeight()));
        g.fill(shape);

        Assets.engine.draw(((0.5f + ((x-0.05f)/z)) * gc.getWidth()), ((0.5f + ((y-0.0125f-0.0025f)/z)) * gc.getHeight()),
                gc.getWidth()*0.1f/z, gc.getScreenHeight()*0.025f/z);

        g.setColor(Color.green);
        ttf.drawString(32, gc.getHeight() - 128.0f, "Health: " + health);

        if (can_deliver) {
            g.setColor(Color.yellow);

            ttf.drawString(32, 32, "Press X to deliver payload!");
        }
    }

    public void damage() {
        if (invulnerable) return;

        health--;
        Assets.hurt2.play();
        if (health <= 0) {
            health = 15;
            game.setPause(true);
        }
    }

    public void allowDeliver() {
        can_deliver = true;
    }

    public boolean isPayloadFired() {
        return fired_payload;
    }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    public void makeInvulnerable(GameContainer gc) {
        invulnerable = true;
        invulnerable_end = gc.getTime() + invulnerable_duration;
    }
}
