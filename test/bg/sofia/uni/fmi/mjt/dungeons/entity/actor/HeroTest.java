package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Level;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

class HeroTest {

    private Hero hero;
    private Minion minion;
    private Minion minion2;
    private Weapon weapon;
    private Character enemy;
    private Treasure treasure;

    @BeforeEach
    public void setUp() {
        Position position = mock(Position.class);
        Stats stats = mock(Stats.class);
        Spell spell = mock(Spell.class);
        weapon = mock(Weapon.class);
        hero = new Hero(position, "TestHero", new Stats(10, 10, 10, 10), spell, weapon);
        minion = new Minion(position, "Enemy", new Stats(1, 1, 1, 1), spell, weapon, new Level().addXP(100));
        minion2 = new Minion(position, "Enemy2", new Stats(100, 1, 100, 100), spell, weapon, new Level().addXP(100));
        treasure = mock(Treasure.class);
    }

    @Test
    public void testHeroVisitCharacterFightWon() {

        hero.visitCharacter(minion);
        assertEquals(100, hero.getXP(), "Hero should have received xp after winning");
    }

    @Test
    public void testHeroVisitCharacterFightLost() {

        hero.visitCharacter(minion2);
        assertFalse(hero.isAlive(), "Hero should be dead.");
    }

    @Test
    public void testHeroVisitTreasure() {

        hero.visitTreasure(treasure);

        verify(treasure, times(1)).consume(hero);
    }

    @Test
    public void testHeroToChar() {
        assertEquals('H', hero.toChar());
    }

}
