package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure;

import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;

public interface Treasure {
    /**
     * Treasure is consumed by a hero.
     * Depending on the treasure type it has different implementations.
     *
     * @param character the character who will collect the item
     */
    void consume(Character character);

}
