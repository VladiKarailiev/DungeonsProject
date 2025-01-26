package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure;

import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;

public interface Treasure {
    /**
     * Treasure is consumed by a hero.
     * Depending on the treasure type it has different implementations.
     *
     * @param hero the hero who will collect the item
     * @throws IllegalArgumentException if the Hero is null
     */
    void consume(Hero hero);

}
