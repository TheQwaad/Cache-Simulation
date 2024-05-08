package cache;

import java.util.List;

public class SimulationList {
    List <Simulator> simulators;
    int size;

    public SimulationList (List <Simulator> simulators) {
        this.simulators = simulators;
        size = simulators.size();
    }

    public void cacheRequest(int address, QueryType type, int size) {
        for (Simulator el: simulators) {
            el.cacheRequest(address, type, size);
        }
    }

    public void addVariable() {
        for (Simulator el: simulators) {
            el.addVariable();
        }
    }

    public void initializeVariable() {
        for (Simulator el: simulators) {
            el.initializeVariable();
        }
    }

    public void multiplyVariable() {
        for (Simulator el: simulators) {
            el.multiplyVariable();
        }
    }

    public void addTime(int time) {
        for (Simulator el: simulators) {
            el.addTime(time);
        }
    }

    public double getHitRate(int i) {
        return simulators.get(i).getHitRate();
    }

    public int getTime(int i) {
        return simulators.get(i).getTime();
    }
}
