import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class EntityManager {
    private ArrayList<Entity> entities = new ArrayList<Entity>();

    public void update(GameContainer gc, int delta) {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            if (!e.active) {
                //System.out.println(entities.size());
                entities.remove(e);
                //System.out.println(entities.size());
            }
            else {
                e.update(gc, delta);
            }
        }
    }

    public void render(GameContainer gc, Graphics g) {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.render(gc, g);
        }
    }

    public void clear() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).active = false;
        }
    }

    public void add(Entity e) {
        entities.add(e);
    }

    public ArrayList<Entity> getList() {
        return entities;
    }
}
