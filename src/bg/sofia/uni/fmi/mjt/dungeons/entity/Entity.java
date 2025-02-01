package bg.sofia.uni.fmi.mjt.dungeons.entity;

public interface Entity {
    void accept(Visitor visitor);
}
