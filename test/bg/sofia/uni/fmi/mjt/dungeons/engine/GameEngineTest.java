package bg.sofia.uni.fmi.mjt.dungeons.engine;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Stats;
import bg.sofia.uni.fmi.mjt.dungeons.exceptions.NoSpawnPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameEngineTest {

    private GameEngine engine;
    private Hero hero;

    @BeforeEach
    public void setUp() throws NoSpawnPositionException {
        engine = GameEngine.getInstance();
        Position spawnPos = engine.getNextFreeSpawn();
        hero = new Hero(spawnPos, "Hero", new Stats(1, 1, 1, 1), null, null);
    }

    @Test
    public void testGetInstance() {
        GameEngine engine2 = GameEngine.getInstance();
        assertEquals(engine, engine2, "Engine should be singleton");
    }

    @Test
    public void testGetNextFreeSpawn() throws NoSpawnPositionException {
        Position freeSpawn = engine.getNextFreeSpawn();
        assertNotNull(freeSpawn, "Engine should return a free spawn");
    }

    @Test
    public void testAddEntity() {
        engine.addEntity(hero);
        Entity[][] map = engine.getMap();
        assertEquals(hero, map[hero.getPos().x()][hero.getPos().y()], "Entity should be added");
    }

    @Test
    public void testRemoveEntity() {
        engine.addEntity(hero);
        Position wasHere = hero.getPos();
        engine.removeEntity(hero);
        Entity[][] map = engine.getMap();
        assertNull(map[wasHere.x()][wasHere.y()], "Entity should be successfully removed");
    }

    @Test
    public void testMoveEntity() {
        Position target = new Position(0, 1);
        engine.addEntity(hero);
        engine.moveEntity(hero, target);
        assertEquals(target, hero.getPos(), "Entity should be moved to target");
    }

    @Test
    public void testGetStringifiedMap() {
        String mapString = engine.getStringifiedMap();
        assertTrue(mapString.contains("*"), "Map should be represented by a string");
    }

    @Test
    public void testAddEntityWhenNull() {
        assertThrows(IllegalArgumentException.class, () -> engine.addEntity(null), "Can't add null entity!");
    }

    @Test
    public void testRemoveEntityWhenNull() {
        assertThrows(IllegalArgumentException.class, () -> engine.removeEntity(null), "Can't add null entity!");
    }

}
