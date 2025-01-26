package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

import java.util.ArrayList;

public class Hero extends Character {

    private ArrayList<Treasure> backpack;

    public Hero(String name, Stats stats, Spell spell,
                Weapon weapon) {
        super(name, stats, spell, weapon);
    }
}
