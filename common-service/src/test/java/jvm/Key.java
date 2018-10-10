package jvm;

/**
 * @author hy9902
 * @create 2018-10-08 10:18
 */
public class Key {
    Integer id;

    public Key(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
