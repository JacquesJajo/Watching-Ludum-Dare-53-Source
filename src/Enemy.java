public abstract  class Enemy extends Entity {
    public Enemy(Main game, float x, float y, float z) {
        super(game, x, y, z);
    }

    public void kill() {
        active = false;
    }

    public boolean playerIntersection() {
        return game.getPlayer().inRadius(0.1f, this) && active;
    }
}
