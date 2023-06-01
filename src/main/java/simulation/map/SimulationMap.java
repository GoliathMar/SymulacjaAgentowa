package simulation.map;

import simulation.position.IPosition;
import simulation.position.Position;
import simulation.unit.IUnit;

import java.util.HashMap;
import java.util.Map;

public class SimulationMap implements ISimulationMap {

    private IUnit[][] map;
    private Map<IUnit, IPosition> unitsPositions;

    public SimulationMap(int mapSizeX, int mapSizeY) {
        map = new IUnit[mapSizeX][mapSizeY];
        unitsPositions = new HashMap<>();

    }

    @Override
    public IUnit getUnit(IPosition position) {
        return map[position.getX()][position.getY()];
    }

    @Override
    public int getMapSizeX() {
        return map.length;
    }

    @Override
    public int getMapSizeY() {
        return map[0].length;
    }

    @Override
    public int getSize() {
        return map.length * map[0].length;
    }

    @Override
    public IUnit[][] getMap() {
        return map;
    }

    @Override
    public boolean putUnit(IUnit unit, IPosition position) {
        IPosition actualPosition = getUnitPosition(unit);

        if(map[position.getX()][position.getY()] != null) return false;

        if(actualPosition.getX() >= 0 && actualPosition.getY() >= 0) map[actualPosition.getX()][actualPosition.getY()] = null;

        unit.setSimulationMap(this);
        map[position.getX()][position.getY()] = unit;
        unitsPositions.put(unit, position);

        return true;
    }

    @Override
    public IPosition getUnitPosition(IUnit unit) {
        IPosition position = unitsPositions.get(unit);
        if(position == null) return new Position(-1,-1);
        else return position;
    }

    @Override
    public boolean destroyUnit(IUnit unit) {
        if(!unit.isAlive()) {
            map[unit.getSimulationMap().getUnitPosition(unit).getX()][unit.getSimulationMap().getUnitPosition(unit).getY()] = null;
            unitsPositions.remove(unit);
            return true;
        }
        return false;
    }
}
