import cache.*;

import java.util.List;

public class Main {
    private static final int M = 64;
    private static final int N = 60;
    private static final int K = 32;

    public static void main(String[] args) {
        SimulationList simulationList = new SimulationList(
                List.of(
                        new Simulator(new LruCache()),
                        new Simulator(new PLruCache()),
                        new Simulator(new RoundRobinCache())
                )
        );

        int pa = 0; //8 bit
        simulationList.initializeVariable();
        int pc = M * K + 2 * K * N; //32 bit
        simulationList.initializeVariable();

        simulationList.initializeVariable(); // cycle begins
        for (int y = 0; y < M; y++) {
            simulationList.initializeVariable(); // cycle begins
            for (int x = 0; x < N; x++) {
                int pb = M * K; //16 bit
                simulationList.initializeVariable();
                //int s = 0;
                simulationList.initializeVariable();

                simulationList.initializeVariable(); // cycle begins
                for (int k = 0; k < K; k++) {
                    //s += pa[k] * pb[x];
                    simulationList.cacheRequest(pa + k, QueryType.r, 8);
                    simulationList.cacheRequest(pb + x * 2, QueryType.r, 16);
                    simulationList.addVariable();
                    simulationList.multiplyVariable();
                    //pb += N;
                    simulationList.addVariable();

                    simulationList.addVariable(); // next iteration
                }
                //pc[x] = s;
                simulationList.initializeVariable();
                simulationList.cacheRequest(pc + 4 * x, QueryType.w, 32);

                simulationList.addVariable(); //next iteration
            }
            pa += K;
            simulationList.addVariable();
            pc += N * 4;
            simulationList.addVariable();

            simulationList.addVariable(); //next iteration
        }

        System.out.printf(
                "LRU:\thit perc. %3.4f%%\ttime: %d\npLRU:\thit perc. %3.4f%%\ttime: %d\nRR: \thit perc. %3.4f%%\ttime: %d\n",
                simulationList.getHitRate(0),
                simulationList.getTime(0),
                simulationList.getHitRate(1),
                simulationList.getTime(1),
                simulationList.getHitRate(2),
                simulationList.getTime(2)
        );
    }
}