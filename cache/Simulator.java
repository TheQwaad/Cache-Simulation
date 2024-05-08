package cache;

public class Simulator {
    private static final int MULTIPLY_PRICE = 5;
    private static final int ADD_PRICE = 1;
    private static final int INITIALIZE_PRICE = 1;

    Cache cache;
    private int time;
    private int cacheUsages;
    private int cacheHits;

    public Simulator(Cache cache) {
        this.cache = cache;
        time = 0;
        cacheUsages = 0;
        cacheHits = 0;
    }
    public void cacheRequest(int address, QueryType type, int size) {
        Response response = cache.request(address, type, size);
        addTime(response.getTime());
        cacheHits += (response.isHit() ? 1 : 0);
        cacheUsages++;
    }

    public void addVariable() {
        time += ADD_PRICE;
    }

    public void initializeVariable() {
        time += INITIALIZE_PRICE;
    }

    public void multiplyVariable() {
        time += MULTIPLY_PRICE;
    }

    public void addTime(int time) {
        this.time += time;
    }

    public double getHitRate() {
        return (100.0 * cacheHits) / cacheUsages;
    }

    public int getTime() {
        return time;
    }
}
