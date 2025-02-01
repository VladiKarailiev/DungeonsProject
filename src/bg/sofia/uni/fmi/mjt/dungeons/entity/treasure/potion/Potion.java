package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.potion;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public abstract class Potion extends Entity implements Treasure {
    protected final Integer amount;

    public Potion(Integer amount) {
        this.amount = amount;
    }

    public abstract  void consume(Hero hero);


}
