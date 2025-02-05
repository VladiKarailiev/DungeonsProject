package bg.sofia.uni.fmi.mjt.dungeons.entity;

public abstract class Entity implements Visitor {

    Position pos;

    public Entity(Position position) {
        pos = position;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public abstract void accept(Visitor visitor);

    public abstract char toChar();
}
