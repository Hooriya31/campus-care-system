package models;

public interface Saveable {
    void save(String filename);
    void load(String filename);
}
