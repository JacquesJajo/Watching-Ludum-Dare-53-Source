import org.newdawn.slick.GameContainer;

import java.util.Random;

public class Wave1 extends Wave {

    private boolean active = false;

    private float duration = 60000;
    private float timer;

    public Wave1(Main game, float spawn_rate) {
        super(game, spawn_rate);
    }

    public void start(GameContainer gc) {
        next_spawn = gc.getTime() + spawn_rate;
        timer = gc.getTime() + duration;

        active = true;
    }

    public void update(GameContainer gc, int delta) {
        if (!active) return;

        if (gc.getTime() > timer) {
            game.nextWave(gc);
            stop();
        }

        if (next_spawn < gc.getTime()) {
            for (int i = 0; i < 2; i++) {
                game.getEntityManager().add(new BasicEnemy(game, 2.0f*random.nextFloat() - 1.0f, 2.0f*random.nextFloat() - 1.0f, 10.0f));
            }
            game.getEntityManager().add(new DivebombEnemy(game, 2.0f*random.nextFloat() - 1.0f, 0.0f, 10.0f));
            next_spawn = gc.getTime() + spawn_rate;
        }
    }

    public void stop() {
        active = false;
    }
}
