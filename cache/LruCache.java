package cache;

public class LruCache extends AbstractCache {

    public LruCache() {
        super();
    }
    @Override
    public void update(Address address, int skip) {
        lines[address.getSet()][skip].setFlag(0);
        for (int ch = 0; ch < CacheValues.CACHE_WAY; ch++) {
            if (ch != skip && lines[address.getSet()][ch].isValid()) {
                lines[address.getSet()][ch].addLru();
            }
        }
    }

    public int findReplace(Address address) {
        int toOverwrite = 0;
        for (int ch = 1; ch < CacheValues.CACHE_WAY; ch++) {
            if (lines[address.getSet()][ch].getFlag() > lines[address.getSet()][toOverwrite].getFlag()) {
                toOverwrite = ch;
            }
        }
        return toOverwrite;
    }
}
