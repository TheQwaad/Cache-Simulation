package cache;



public class CacheValues {
    public static int CACHE_WAY = 4;
    public static int CACHE_LINE_SIZE = 32;
    public static int CACHE_SETS_COUNT = 32;
    public static int CACHE_LINE_COUNT = CACHE_WAY * CACHE_SETS_COUNT;
    public static int CACHE_SIZE = CACHE_LINE_SIZE * CACHE_LINE_COUNT;
    public static int ADDR_LEN = 16;
    public static int CACHE_OFFSET_LEN = 5; // log2(CACHE_LINE_SIZE)
    public static int CACHE_IDX_LEN = 5; // log2(CACHE_SETS_COUNT)
    public static int CACHE_TAG_LEN = ADDR_LEN - CACHE_OFFSET_LEN - CACHE_IDX_LEN;

    public static int ADDR1_BUS_LEN = ADDR_LEN;
    public static int ADDR2_BUS_LEN = ADDR1_BUS_LEN;
    public static int DATA1_BUS_LEN = 16;
    public static int DATA2_BUS_LEN = DATA1_BUS_LEN;
    public static int CTR1_BUS_LEN = 3; //ceil(log2(commands amount)) = ceil(log2(7))
    public static int CTR2_BUS_LEN = 2; //ceil(log2(commands amount)) = ceil(log2(  3))

    public static int CACHE_HIT_RESPONSE_TIME = 6;
    public static int CACHE_MISS_RESPONSE_TIME = 4;
    public static int MEMORY_RESPONSE_TIME = 100;

    public static int A_CAPACITY = 1;
    public static int D_CAPACITY = 16;
    public static int C_CAPACITY = 1;
}
