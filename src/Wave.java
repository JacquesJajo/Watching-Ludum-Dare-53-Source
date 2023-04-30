import org.newdawn.slick.GameContainer;

import java.util.Random;

public abstract class Wave {
    protected Main game;
    protected float spawn_rate;
    protected float next_spawn;

    protected Random random;

    protected boolean active = false;

    public Wave(Main game, float spawn_rate) {
        this.game = game;
        this.spawn_rate = spawn_rate;
        random = new Random();
    }

    public abstract void start(GameContainer gc);
    public abstract void update(GameContainer gc, int delta);
    public abstract void stop();
}
