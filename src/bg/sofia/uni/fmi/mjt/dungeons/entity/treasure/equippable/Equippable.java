package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Level;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public abstract class Equippable extends Entity implements Treasure {
    private String name;
    private int damage;
    private Level level;

    public Equippable(Position pos, String name, int damage, Level level) {
        super(pos);
        this.name = name;
        this.damage = damage;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' + ", damage=" + damage + '}';
    }
}
