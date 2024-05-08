package cache;

public interface Cache {
    Response request(int address, QueryType type, int size);
}