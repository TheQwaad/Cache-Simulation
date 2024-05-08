package cache;

public class PLruCache extends AbstractCache {
    public PLruCache() {
        super();
    }

    @Override
    public void update(Address address, int skip) {
        lines[address.getSet()][skip].setFlag(1);
        int count = 0;
        for (int ch = 0; ch < CacheValues.CACHE_WAY; ch++) {
            count += lines[address.getSet()][ch].getFlag();
        }

        if (count == CacheValues.CACHE_WAY) {
            for (int ch = 0; ch < CacheValues.CACHE_WAY; ch++) {
                if (ch != skip) {
                    lines[address.getSet()][ch].setFlag(0);
                }
            }
        }
    }

    @Override
    public int findReplace(Address address) {
        int res = 0;
        for (int ch = 0; ch < CacheValues.CACHE_WAY; ch++) {
            if (lines[address.getSet()][ch].getFlag() == 0) {
                res = ch;
                break;
            }
        }
        return res;
    }
}
