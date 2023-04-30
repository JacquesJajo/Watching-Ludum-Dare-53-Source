import org.newdawn.slick.GameContainer;

import java.util.Random;

public class Wave2 extends Wave {

    enum WaveState {
        APPROACH, FIGHT, END
    }

    private float camera_z, camera_y;
    private float scroll_speed = 0.01f;
    private float direction_switch = 32.0f;
    private float camera_y_vel = 1.0f, camera_z_vel = 0.0f;

    private Watcher watcher;
    private WaveState state;

    public Wave2(Main game, float spawn_rate) {
        super(game, spawn_rate);
    }

    public void start(GameContainer gc) {
        next_spawn = gc.getTime() + spawn_rate;
        watcher = new Watcher(game, 0.0f, 24.0f, 35.0f);
        game.getEntityManager().add(watcher);
        active = true;
        state = WaveState.APPROACH;
    }

    public void update(GameContainer gc, int delta) {
        if (!active) return;

        if (state == WaveState.APPROACH) {
            approach_boss(gc, delta);
        }
        else if (state == WaveState.FIGHT) {
            fight_boss(gc, delta);
        }
        else if (state == WaveState.END) {

        }

        if (next_spawn < gc.getTime() && state != WaveState.END) {
            for (int i = 0; i < 2; i++) {
                game.getEntityManager().add(new BasicEnemy(game, 2.0f*random.nextFloat() - 1.0f, 2.0f*random.nextFloat() - 1.0f, 10.0f));
            }
            game.getEntityManager().add(new DivebombEnemy(game, 2.0f*random.nextFloat() - 1.0f, 0.0f, 10.0f));
            next_spawn = gc.getTime() + spawn_rate;
        }
    }

    public void approach_boss(GameContainer gc, int delta) {
        camera_y += camera_y_vel * scroll_speed * 0.1f * delta;
        watcher.set_velocity(0, -scroll_speed * camera_y_vel, -scroll_speed * camera_z_vel);
        if (watcher.getY() <= -6.0f) {
            state = WaveState.FIGHT;
            camera_y_vel = 0.0f;
            camera_z_vel = 1.0f;
        }
    }

    public void fight_boss(GameContainer gc, int delta) {
        camera_z += camera_z_vel * scroll_speed * 0.1f * delta;
        camera_y += camera_y_vel * scroll_speed * 0.1f * delta;

        if (camera_z >= direction_switch) {
            camera_y_vel = -1.0f;
            camera_z_vel = 0.0f;
            scroll_speed = 0.05f;
            watcher.defeat();
            //state = WaveState.END;
        }
        System.out.println(watcher.getEyeHeight());
        if (watcher.getEyeHeight() >= 0.0f) {
            camera_y_vel = 0.0f;
            game.getPlayer().allowDeliver();
        }
        watcher.set_velocity(0, -scroll_speed * camera_y_vel, -scroll_speed * camera_z_vel);
    }

    public void stop() {
        active = false;
    }
}
