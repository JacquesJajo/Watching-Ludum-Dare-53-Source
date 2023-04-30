import org.newdawn.slick.*;

public class DivebombEnemy extends Enemy {
    private Animation animation;
    private float speed = 0.05f;

    public DivebombEnemy(Main game, float x, float y, float z) {
        super(game, x, y, z);
        animation = new Animation(new Image[] {Assets.winged_eye_gold1, Assets.winged_eye_gold2}, 250);
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (!active) return;

        float to_player_x = game.getPlayer().x - x;
        float to_player_y = game.getPlayer().y - y;
        float to_player_z = game.getPlayer().z - z;
        float magnitude = (float) Math.sqrt(to_player_x*to_player_x + to_player_y*to_player_y + to_player_z*to_player_z);

        x_vel = to_player_x / magnitude * speed;
        y_vel = to_player_y / magnitude * speed;
        z_vel = to_player_z / magnitude * speed;

        x += x_vel * 0.1f * delta;
        y += y_vel * 0.1f * delta;
        z += z_vel * 0.1f * delta;

        if (playerIntersection()) {
            System.out.println("hit by divebomb");
            active = false;
            game.getPlayer().damage();

        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        if (!active) return;
        g.setColor(Color.red);
        //g.fillRect(((0.5f + ((x-0.05f)/z)) * gc.getWidth()), ((0.5f + ((y-0.05f)/z)) * gc.getHeight()), gc.getWidth()*0.05f/z, gc.getScreenHeight()*0.05f/z);
        animation.draw(((0.5f + ((x-0.05f)/z)) * gc.getWidth()), ((0.5f + ((y-0.05f)/z)) * gc.getHeight()), gc.getWidth()*0.3f/z, gc.getScreenHeight()*0.15f/z);
    }
}
