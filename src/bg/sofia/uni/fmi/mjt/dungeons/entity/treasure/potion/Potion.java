package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.potion;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public abstract class Potion extends Entity implements Treasure {
    protected final Integer amount;

    public Potion(Position pos, Integer amount) {
        super(pos);
        this.amount = amount;
    }

    public abstract  void consume(Hero hero);


}
