package cache;

public abstract class AbstractCache implements Cache {

    Line[][] lines;

    public AbstractCache() {
        lines = new Line[CacheValues.CACHE_SETS_COUNT][CacheValues.CACHE_WAY];
        for (int i = 0; i < CacheValues.CACHE_SETS_COUNT; i++) {
            for (int j = 0; j < CacheValues.CACHE_WAY; j++) {
                lines[i][j] = new Line();
            }
        }
    }

    @Override
    public Response request(int address, QueryType type, int size) {
        int set = (address / CacheValues.CACHE_LINE_SIZE) % CacheValues.CACHE_SETS_COUNT;
        int tag = (address / CacheValues.CACHE_LINE_SIZE) / CacheValues.CACHE_SETS_COUNT;
        Address temp = new Address(set, tag);
        if (containsAddress(temp, type)) {
            return new Response(
                    true,
                    calcDTime(size) + CacheValues.CACHE_HIT_RESPONSE_TIME + CacheValues.C_CAPACITY
            );
        }
        return new Response(false, add(temp, type, size));
    }

    public int add(Address address, QueryType type, int size) {
        int time = 2 * calcDTime(size) + CacheValues.CACHE_MISS_RESPONSE_TIME + CacheValues.MEMORY_RESPONSE_TIME;
        for (int ch = 0; ch < CacheValues.CACHE_WAY; ch++) {
            if (!lines[address.getSet()][ch].isValid()) {
                write(address, ch, type);
                return time;
            }
        }
        int toOverwrite = findReplace(address);
        write(address, toOverwrite, type);
        if (lines[address.getSet()][toOverwrite].isDirty()) {
            time += CacheValues.MEMORY_RESPONSE_TIME +
                    calcDTime(size) +
                    CacheValues.CACHE_HIT_RESPONSE_TIME +
                    CacheValues.C_CAPACITY;
        }
        return time;
    }

    public void write(Address address, int ch, QueryType type) {
        lines[address.getSet()][ch].setValid(true);
        lines[address.getSet()][ch].setTag(address.getTag());
        lines[address.getSet()][ch].setDirty(type == QueryType.w);
        update(address, ch);
    }
    public boolean containsAddress(Address address, QueryType type) {
        for (int ch = 0; ch < CacheValues.CACHE_WAY; ch++) {
            if (lines[address.getSet()][ch].isValid() &&
                    lines[address.getSet()][ch].getTag() == address.getTag()) {
                if (type == QueryType.w) {
                    lines[address.getSet()][ch].setDirty(true);
                }
                if (lines[address.getSet()][ch].getFlag() != 0) {
                    lines[address.getSet()][ch].setFlag(0);
                    update(address, ch);
                }

                return true;
            }
        }
        return false;
    }
    private int calcDTime(int size) {
        return (size + CacheValues.D_CAPACITY - 1) / CacheValues.D_CAPACITY;
    }

    public abstract int findReplace(Address address);
    public abstract void update(Address address, int skip);
}
