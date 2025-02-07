package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Level;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class SpellTest {

    private Spell spell;
    private Position position;
    private Level level;
    private Character character;
    private Hero hero;
    private Treasure treasure;

    @BeforeEach
    public void setUp() {
        position = mock(Position.class);
        level = mock(Level.class);
        hero = mock(Hero.class);
        spell = new Spell(position, "magic", 50, 30, level);
        character = mock(Character.class);
        treasure = mock(Treasure.class);
    }

    @Test
    public void testSpellConstructorThrowsIllegalArgumentExceptionForNegativeManaCost() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Spell(position, "magic", 60, -10, level);
        });
        assertEquals("Mana Cost can't be negative", exception.getMessage());
    }

    @Test
    public void testSpellConsumeHeroLearnsSpell() {
        spell.consume(hero);
        verify(hero, times(1)).learn(spell);
    }

    @Test
    public void testSpellConsumeNullCharacter() {
        spell.consume(null);

        verifyNoInteractions(hero);
    }

    @Test
    public void testSpellAcceptVisitor() {
        Visitor visitor = mock(Visitor.class);
        spell.accept(visitor);
        verify(visitor, times(1)).visitTreasure(spell);
    }

    @Test
    public void testSpellVisitCharacter() {
        spell.visitCharacter(hero);

        verify(hero, times(1)).learn(spell);
    }

    @Test
    public void testSpellVisitTreasure() {
        spell.visitTreasure(treasure);

    }

    @Test
    public void testSpellToChar() {
        assertEquals('S', spell.toChar());
    }

}
