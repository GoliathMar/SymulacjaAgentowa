package simulation;

import simulation.map.ISimulationMap;
import simulation.map.creator.ISimulationMapCreator;
import simulation.position.IPosition;
import simulation.position.Position;
import simulation.unit.IUnit;
import simulation.unit.creator.IUnitCreator;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Simulation {

    private ISimulationMap map;
    private Random random;
    private List<IUnit> units;
    private int maxIteration;

    public Simulation(ISimulationMapCreator mapCreator, IUnitCreator unitCreator, long seed, int maxIteration) {
        this.map = mapCreator.createMap();
        this.random = new Random(seed);
        this.units = unitCreator.createUnits(map);
        this.maxIteration = maxIteration;

        for (int i = 0; i < units.size(); i++) {
            IPosition position = new Position(random.nextInt(map.getMapSizeX()), random.nextInt(map.getMapSizeY()));
            while (!map.putUnit(units.get(i), position)) {
                position.setX(random.nextInt(map.getMapSizeX()));
                position.setY(random.nextInt(map.getMapSizeY()));
            }
        }
    }

    private void destroyUnits() {
        Iterator<IUnit> iterator = units.iterator();
        while (iterator.hasNext()) {
            IUnit unit = iterator.next();
            if (unit.getSimulationMap().destroyUnit(unit)) iterator.remove();
        }
    }

    public boolean isAnyoneAliveA() {
        int i = 0;
        for (IUnit unit : units) if (unit.getIsA()) i++;
        if (i > 0) return true;
        else return false;
    }

    public boolean isAnyoneAliveB() {
        int i = 0;
        for (IUnit unit : units) if (!unit.getIsA()) i++;
        if (i > 0) return true;
        else return false;
    }

    public void printMap() {
        IUnit[][] map = this.map.getMap();

        for (int i = map[0].length - 1; i >= 0; i--) {
            for (int j = 0; j < map.length; j++) {
                if (map[j][i] == null) {
                    System.out.print("# ");
                } else {
                    System.out.print(map[j][i].toString());
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void runSimulation() {
        this.printMap();
        System.out.println();
        for (int i = 0; i < maxIteration - 1; i++) {
            for (IUnit unit : units) {
                unit.doAction();
            }
            this.printMap();
            System.out.println();

            if (!this.isAnyoneAliveA() || !this.isAnyoneAliveB()) {
                return;
            }
            this.destroyUnits();
        }
    }
}