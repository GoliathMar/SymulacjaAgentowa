package simulation.map.creator;

import simulation.map.ISimulationMap;
import simulation.map.SimulationMap;

public class SimulationMapCreator implements ISimulationMapCreator {

    private int mapSizeX;
    private int mapSizeY;

    public SimulationMapCreator(int mapSizeX, int mapSizeY) {
        this.mapSizeX = mapSizeX;
        this.mapSizeY = mapSizeY;
    }

    @Override
    public ISimulationMap createMap() {
        return new SimulationMap(mapSizeX, mapSizeY);
    }
}
