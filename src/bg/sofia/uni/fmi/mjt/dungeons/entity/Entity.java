package bg.sofia.uni.fmi.mjt.dungeons.entity;

public abstract class Entity implements Visitor {
    public abstract void accept(Visitor visitor);

    public abstract char toChar();
}
