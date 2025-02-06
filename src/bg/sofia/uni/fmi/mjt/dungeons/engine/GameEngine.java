package bg.sofia.uni.fmi.mjt.dungeons.engine;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Level;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Minion;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Stats;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.potion.HealthPotion;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.potion.ManaPotion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameEngine {

    public static final int MAX_PLAYERS = 9;
    private static final int MAP_SIZE = 11;
    private static final List<Position> SPAWN_POSITIONS =
        List.of(new Position(0, 0), new Position(0, 5), new Position(0, 10), new Position(5, 0), new Position(5, 5),
            new Position(5, 10), new Position(10, 0), new Position(10, 5), new Position(10, 10));
    private static final String DEFAULT_MAP_PATH = "map.txt";

    private Entity[][] map = new Entity[MAP_SIZE][MAP_SIZE];
    private Set<Position> obstacles = new HashSet<>();


    private static GameEngine singleInstance = null;

    private Entity[][] loadMap(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int rows = MAP_SIZE;
            int cols = MAP_SIZE;

            Entity[][] map = new Entity[rows][cols];

            int currentRow = 0;
            for (int row = 0; row < rows; row++) {
                line = br.readLine();
                for (int col = 0; col < cols; col++) {
                    char tile = line.charAt(col);
                    if (tile == '#') {
                        obstacles.add(new Position(currentRow, col));
                        continue;
                    }
                    map[currentRow][col] = createEntity(tile, new Position(currentRow, col));
                }
                currentRow++;
            }

            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Entity createEntity(char tile, Position pos) {
        Random random = new Random();
        switch (tile) {
            case 'h':
                return new HealthPotion(pos, random.nextInt(10));
            case 'm':
                return new ManaPotion(pos, random.nextInt(10));
            case 'W':
                return new Weapon(pos, "Orujie", random.nextInt(10),
                    new Level().addXP(random.nextInt(300)));
            case 'S':
                return new Spell(pos, "Magiika", random.nextInt(10), random.nextInt(5),
                    new Level().addXP(random.nextInt(300)));
            case 'M':
                Level lvl = new Level();
                return new Minion(pos, "Minionche",
                    new Stats(random.nextInt(10), random.nextInt(10), random.nextInt(10), random.nextInt(10)),
                    new Spell(pos, "Orujie", random.nextInt(10), random.nextInt(5), new Level().addXP(random.nextInt(300))),
                    new Weapon(pos, "Magiika", random.nextInt(10),
                        new Level().addXP(random.nextInt(300))),
                    new Level().addXP(random.nextInt(500)));
            case '*':
            default:
                return null;
        }
    }

    private GameEngine() {
        map = loadMap(DEFAULT_MAP_PATH);
    }

    public Position getNextFreeSpawn() {
        return SPAWN_POSITIONS.stream().filter(q -> map[q.x()][q.y()] == null && !isObstacle(q)).findAny().get();
    }

    public static GameEngine getInstance() {
        if (singleInstance == null) singleInstance = new GameEngine();

        return singleInstance;
    }

    public void moveEntity(Entity entity, Position target) {
        if (!canMoveTo(target) || map[target.x()][target.y()] == entity) return;
        if (map[target.x()][target.y()] != null) map[target.x()][target.y()].accept(entity);
        Position oldPos = entity.getPos();
        entity.setPos(target);
        map[target.x()][target.y()] = entity;
        map[oldPos.x()][oldPos.y()] = null;

    }

    private boolean canMoveTo(Position target) {
        return !isObstacle(target) && target.x() >= 0 && target.x() < map.length && target.y() >= 0 &&
            target.y() < map.length;
    }

    public void addEntity(Entity entity) {
        Position pos = entity.getPos();
        if (map[pos.x()][pos.y()] != null) throw new IllegalArgumentException("ima neshto veche tam");
        map[pos.x()][pos.y()] = entity;
    }

    public void removeEntity(Entity entity) {
        Position pos = entity.getPos();
        map[pos.x()][pos.y()] = null;
        entity.setPos(null);
    }

    private boolean isObstacle(Position target) {
        return obstacles.contains(target);
    }

    public Entity[][] getMap() {
        return map;
    }

    public String getStringifiedMap() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (isObstacle(new Position(i, j))) sb.append('#');
                else if (map[i][j] == null) sb.append('*');
                else sb.append(map[i][j].toChar());
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public void printMap() {
        System.out.print(getStringifiedMap());
    }

    public void printEntity(Entity entity) {
        System.out.println(entity);
    }
}