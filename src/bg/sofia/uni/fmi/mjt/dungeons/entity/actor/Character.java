package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

public abstract class Character extends Entity implements Actor {
    private final String name;
    private Stats stats;
    private Spell spell;
    private Weapon weapon;
    //i level!

    public Character(Position position, String name, Stats stats, Spell spell, Weapon weapon) {
        super(position);
        this.name = name;
        this.stats = stats;
        this.spell = spell;
        this.weapon = weapon;
    }

    public boolean fightWith(Character character) {
        while (character.isAlive() && isAlive()) {
            character.takeDamage(stats.attack());
            if (character.isAlive()) takeDamage(character.stats.attack());
        }
        return isAlive();
    }

    @Override
    public void equip(Weapon weapon) {
        if (this.weapon == null || weapon.getDamage() > this.weapon.getDamage()) {
            this.weapon = weapon;
        }
    }

    @Override
    public void learn(Spell spell) { // trqq da checkvam dali imam dostatuchno mana
        if ((this.spell == null || spell.getDamage() > this.spell.getDamage())) {
            this.spell = spell;
        }
    }

    @Override
    public void takeHealing(Integer amount) {
        stats = new Stats(stats.health() + amount, stats.mana(), stats.attack(), stats.defense());
    }

    @Override
    public void takeMana(Integer amount) {

        stats = new Stats(stats.health(), stats.mana() + amount, stats.attack(), stats.defense());
    }

    @Override
    public void takeDamage(Integer amount) {

        stats = new Stats(stats.health() - amount, stats.mana(), stats.attack(), stats.defense());
        // tuka moje bi nqkuv check dali e umrql
    }

    @Override
    public boolean isAlive() {
        return stats.health() > 0;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitCharacter(this);
    }
}
