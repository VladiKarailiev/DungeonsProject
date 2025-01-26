package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Direction;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

public interface Actor {

    /**
     * Tries to assign a weapon to Actor
     *
     * @param weapon the weapon to be equipped
     * @return nothing
     * @throws IllegalArgumentException if the weapon is null
     */
    void equip(Weapon weapon);

    /**
     * Tries to assign a spell to Actor
     *
     * @param spell the spell to be learned
     * @return nothing
     * @throws IllegalArgumentException if the spell is null
     */
    void learn(Spell spell);

    /**
     * Adds healing points to Actor
     *
     * @param amount the amount to be added
     * @return nothing
     * @throws IllegalArgumentException if the amount is not in [0,1000]
     */
    void takeHealing(Integer amount);

    /**
     * Applies damage to Actor
     *
     * @param amount the amount to be applied
     * @return nothing
     * @throws IllegalArgumentException if the amount is not in [0,1000]
     */
    void takeDamage(Integer amount);

    /**
     * Checks the vital signs of the Actor ;)
     *
     * @return if the Actor is alive
     */
    boolean isAlive();

    /**
     * Moves the Actor in a direction
     * @param direction the direction of the move
     * @return if the Actor is alive
     * @throws IllegalArgumentException if the direction is null
     */
    boolean move(Direction direction);
}
