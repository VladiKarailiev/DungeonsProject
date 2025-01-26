package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.potion;

import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public class Potion implements Treasure {
    private final Integer amount;

    public Potion(Integer amount) {
        this.amount = amount;
    }

    @Override
    public void consume(Hero hero) {

    }
}
