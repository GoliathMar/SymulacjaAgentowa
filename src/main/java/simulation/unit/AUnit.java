package simulation.unit;

import simulation.attack.IAttack;
import simulation.map.ISimulationMap;
import simulation.position.IPosition;
import simulation.position.Position;

import java.util.*;

public abstract class AUnit implements IUnit {

    protected double hp;
    protected int range;
    protected boolean isA;
    protected ISimulationMap map;

    public AUnit(double hp, int range, boolean isA, ISimulationMap map) {
        this.hp = hp;
        this.range = range;
        this.isA = isA;
        this.map = map;
    }

    @Override
    public boolean getIsA() {
        return this.isA;
    }

    @Override
    public ISimulationMap getSimulationMap() {
        return this.map;
    }

    @Override
    public void setSimulationMap(ISimulationMap map) {
        this.map = map;
    }

    @Override
    public boolean isAlive() {
        if (this.hp > 0) return true;
        else return false;
    }

    @Override
    public boolean isFriendly(IUnit unit) {
        if(unit == null) return true;
        if (this.isA == unit.getIsA()) return true;
        else return false;
    }

    @Override
    public void attack(IUnit unit) {
        unit.takeDamage(this.dealDamage());
        this.takeDamage(unit.dealDamage());
    }

    @Override
    public List<IPosition> getSurroundings() {
        IPosition unitPosition = this.map.getUnitPosition(this);
        List<IPosition> surroundings = new ArrayList<>();

        int X = unitPosition.getX();
        int Y = unitPosition.getY();
        int xStart = X - range;
        int yStart = Y - range;

            for(int y = yStart; y <= yStart + 2*range; y++) {
                for(int x = xStart; x <= xStart + 2*range; x++) {
                    if(x >= 0 && y >= 0 && x < this.map.getMapSizeX() && y < this.map.getMapSizeY() && !(X == x && Y ==y)) surroundings.add(new Position(x, y));
                }
            }

        Collections.shuffle(surroundings);

        return surroundings;
    }

    @Override
    public IUnit searchForUnit() {
        List<IPosition> surroundings = this.getSurroundings();

        for(IPosition position : surroundings) {
            if(!this.isFriendly(this.map.getUnit(position)) && this.map.getUnit(position).isAlive()) return this.map.getUnit(position);
        }
        return null;
    }

    @Override
    public void move() {
       List<IPosition> surroundings = this.getSurroundings();

       for(IPosition position : surroundings) {
           if(this.map.getUnit(position) == null) {
               this.map.putUnit(this, position);
               break;
           }
       }
    }

    @Override
    public void doAction() {
        if(!this.isAlive()) return;
        IUnit unit = this.searchForUnit();
        if(unit != null) this.attack(unit);
        else this.move();
    }

    @Override
    public abstract void takeDamage(IAttack attack);

    @Override
    public abstract IAttack dealDamage();
}
