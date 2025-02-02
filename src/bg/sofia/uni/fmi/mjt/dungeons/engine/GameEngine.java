package bg.sofia.uni.fmi.mjt.dungeons.engine;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;

public class GameEngine {

    private static final int MAP_SIZE = 5;

    private Entity[][] map = new Entity[MAP_SIZE][MAP_SIZE];
    private Boolean[][] obstacles = new Boolean[MAP_SIZE][MAP_SIZE];

    private static GameEngine singleInstance = null;

    private GameEngine() {

    }

    public Position getNextFreeSpawn() {
        return new Position(1, 1);
    }

    public static GameEngine getInstance() {
        if (singleInstance == null)
            singleInstance = new GameEngine();

        return singleInstance;
    }

    public void moveEntity(Position start, Position target) {
        if (map[start.x()][start.y()] == null) throw new IllegalArgumentException("nqma nishto tam");
        if (map[target.x()][target.y()] != null) map[target.x()][target.y()].accept(map[start.x()][start.y()]);

        map[target.x()][target.y()] = map[start.x()][start.y()];
        map[start.x()][start.y()] = null;

    }

    public void addEntity(Position pos, Entity entity) {
        if (map[pos.x()][pos.y()] != null) throw new IllegalArgumentException("ima neshto veche tam");
        map[pos.x()][pos.y()] = entity;
    }

    private boolean isObstacle(Position target) {
        return false;
    }

    public Entity[][] getMap() {
        return map;
    }

    public String getStringifiedMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == null) sb.append('*');
                else sb.append(map[i][j].toChar());
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public void printMap() {
        System.out.print(getStringifiedMap());
    }

}
/***
 * TODO: MOJE DA E SINGLETON?
 * TODO: SBIH SE  SUS SEBE SI ???
 */