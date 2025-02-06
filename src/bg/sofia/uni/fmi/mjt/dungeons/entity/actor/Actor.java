package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

public interface Actor {

    /**
     * Tries to assign a weapon to Actor
     *
     * @param weapon the weapon to be equipped
     * @return nothing
     */
    void equip(Weapon weapon);

    /**
     * Tries to assign a spell to Actor
     *
     * @param spell the spell to be learned
     * @return nothing
     */
    void learn(Spell spell);

    /**
     * Adds health points to Actor
     *
     * @param amount the amount to be added
     * @return nothing
     */
    void takeHealing(Integer amount);

    /**
     * Adds mana points to Actor
     *
     * @param amount the amount to be added
     */
    void takeMana(Integer amount);

    /**
     * Applies damage to Actor
     *
     * @param amount the amount to be applied
     */
    void takeDamage(Integer amount);

    /**
     * Checks the vital signs of the Actor ;)
     *
     * @return if the Actor is alive
     */
    boolean isAlive();
}
