package simulation.unit;

import simulation.attack.Attack;
import simulation.attack.IAttack;
import simulation.map.ISimulationMap;

public class Tank extends AUnit {

    public Tank(double hp, int range, boolean isA, ISimulationMap map) {
        super(hp, range, isA, map);
    }

    @Override
    public void takeDamage(IAttack attack) {
        if(attack.getType() == 1) this.hp -= attack.getDamage();
        else if(attack.getType() == 2) this.hp -= 0.8 * attack.getDamage();
        else if(attack.getType() == 3) this.hp -= 1.2 * attack.getDamage();
    }

    @Override
    public IAttack dealDamage() {
        return new Attack(30, 1);
    }

    @Override
    public String toString() {
        if(this.isA) return "T";
        else return "t";
    }
}
