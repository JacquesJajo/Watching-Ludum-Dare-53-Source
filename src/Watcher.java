import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Polygon;

public class Watcher extends Enemy {

    enum State {
        WAIT, TELEGRAPH_BLAST, BLAST, SWEEP
    }

    private float eye_x, eye_y;

    private float attack_interval = 2000;
    private float next_attack;
    private boolean initialised = false;

    private State state = State.WAIT;

    private float blast_x, blast_y, blast_z;
    private float blast_duration = 1000;
    private float blast_end;
    private float blast_radius = 0.15f;

    private float telegraph_time = 300;
    private float telegraph_end;
    private float sweep_speed = 0.02f;

    private boolean defeated = false;

    public Watcher(Main game, float x, float y, float z) {
        super(game, x, y, z);
        eye_x = x;
        eye_y = y - 2.5f;
    }

    public void defeat() {
        defeated = true;
    }

    @Override
    public void update(GameContainer gc, int delta) {
        if (!active) return;

        if (!initialised) {
            next_attack = gc.getTime() + attack_interval;
            initialised = true;
        }

        y += y_vel * 0.1f * delta;
        z += z_vel * 0.1f * delta;

        eye_x = x;
        eye_y = y - 2.5f;

        switch (state) {
            case WAIT:
                if (gc.getTime() > next_attack) {
                    if (defeated) break;
                    state = State.TELEGRAPH_BLAST;

                    int roll = game.getRandom().nextInt(3);
                    System.out.println(roll);

                    if (roll < 2) {
                        blast_x = game.getPlayer().x;
                        state = State.TELEGRAPH_BLAST;
                    }
                    else {
                        blast_x = -1.0f;
                        Assets.laser.play();
                        state = State.SWEEP;
                    }

                    blast_y = game.getPlayer().y;
                    blast_z = game.getPlayer().z;
                    telegraph_end = gc.getTime() + telegraph_time;
                }

                break;
            case TELEGRAPH_BLAST:
                if (gc.getTime() > telegraph_end) {
                    blast_end = gc.getTime() + blast_duration;
                    state = State.BLAST;
                    Assets.laser.play();
                }
                break;
            case BLAST:
                if (Util.inRadius(blast_x, blast_y, blast_z, blast_radius, game.getPlayer().x, game.getPlayer().y, game.getPlayer().z)) {
                    System.out.println("hit by watcher");
                    game.getPlayer().damage();
                    game.getPlayer().makeInvulnerable(gc);
                }

                if (gc.getTime() > blast_end) {
                    next_attack = gc.getTime() + attack_interval;
                    state = State.WAIT;
                }
                break;
            case SWEEP:
                blast_x += sweep_speed * 0.1f * delta;

                if (Util.inRadius(blast_x, blast_y, blast_z, blast_radius, game.getPlayer().x, game.getPlayer().y, game.getPlayer().z)) {
                    System.out.println("hit by watcher");
                    game.getPlayer().damage();
                    game.getPlayer().makeInvulnerable(gc);
                    //next_attack = gc.getTime() + attack_interval;
                    //state = State.WAIT;
                }

                if (blast_x >= 1.0f) {
                    next_attack = gc.getTime() + attack_interval;
                    state = State.WAIT;
                }

                break;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        if (!active) return;

        if (game.getPlayer().isPayloadFired()) {
            Assets.watcher_dead.draw(((0.5f + ((x - 2.5f) / z)) * gc.getWidth()), ((0.5f + ((y - 5.0f) / z)) * gc.getHeight()),
                    gc.getWidth() * 5.0f / z, gc.getScreenHeight() * 10.0f / z);
        }
        else {
            Assets.watcher.draw(((0.5f + ((x - 2.5f) / z)) * gc.getWidth()), ((0.5f + ((y - 5.0f) / z)) * gc.getHeight()),
                    gc.getWidth() * 5.0f / z, gc.getScreenHeight() * 10.0f / z);
        }

        if (state == State.TELEGRAPH_BLAST) {
            Assets.cross.draw((0.5f + ((blast_x-blast_radius*0.25f)/blast_z)) * gc.getWidth(), (0.5f + ((blast_y-blast_radius*0.5f)/blast_z)) * gc.getHeight(),
                    gc.getWidth()*blast_radius*0.5f, gc.getHeight()*blast_radius);
        }

        if (state == State.BLAST || state == State.SWEEP) {
            Polygon blast_shape = new Polygon();
            blast_shape.addPoint(((0.5f + (eye_x/z)) * gc.getWidth()), ((0.5f + (eye_y/z)) * gc.getHeight()));
            blast_shape.addPoint((0.5f + ((blast_x-blast_radius*0.5f)/blast_z)) * gc.getWidth(), (0.5f + (blast_y/blast_z)) * gc.getHeight());
            blast_shape.addPoint((0.5f + ((blast_x+blast_radius*0.5f)/blast_z)) * gc.getWidth(), (0.5f + (blast_y/blast_z)) * gc.getHeight());
            //g.setColor(Color.red);
            ShapeFill fill = new GradientFill((0.5f + eye_x/z) * gc.getWidth(), (0.5f + eye_y/z) * gc.getHeight(), new Color(0xd03c32),
                    (0.5f + blast_x/blast_z) * gc.getWidth(), (0.5f + blast_y/blast_z) * gc.getHeight(), new Color(0xfba64c));

            g.fill(blast_shape, fill);

            Assets.blast.draw((0.5f + ((blast_x-blast_radius*0.5f)/blast_z)) * gc.getWidth(), (0.5f + ((blast_y-blast_radius*0.25f)/blast_z)) * gc.getHeight(),
                    gc.getWidth()*blast_radius, gc.getHeight()*blast_radius*0.5f);
        }
    }

    public float getEyeHeight() {
        return eye_y;
    }
}
