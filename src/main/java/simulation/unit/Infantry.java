package simulation.unit;

import simulation.attack.Attack;
import simulation.attack.IAttack;
import simulation.map.ISimulationMap;

public class Infantry extends AUnit {

    public Infantry(double hp, int range, boolean isA, ISimulationMap map) {
        super(hp, range, isA, map);
    }

    @Override
    public void takeDamage(IAttack attack) {
        if(attack.getType() == 1) this.hp -= 1.2 * attack.getDamage();
        else if(attack.getType() == 2) this.hp -= attack.getDamage();
        else if(attack.getType() == 3) this.hp -= 0.8 * attack.getDamage();
    }

    @Override
    public IAttack dealDamage() {
        return new Attack(20, 2);
    }

    @Override
    public String toString() {
        if(this.isA) return "I";
        else return "i";
    }
}
