package cache;

public class RoundRobinCache extends AbstractCache {

    @Override
    public void update(Address address, int skip) {
        int next = (skip + 1) % CacheValues.CACHE_WAY;
        for (int ch = 0; ch < CacheValues.CACHE_WAY; ch++) {
            if (ch != (skip + 1) % CacheValues.CACHE_WAY) {
                lines[address.getSet()][ch].setFlag(1);
            }
        }
        lines[address.getSet()][next].setFlag(0);
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
