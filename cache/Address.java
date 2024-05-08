package cache;

public class Address {
    private final int tag;
    private final int set;

    public Address(int tag, int set) {
        this.tag = tag;
        this.set = set;
    }

    public int getTag() {
        return tag;
    }

    public int getSet() {
        return set;
    }
}
