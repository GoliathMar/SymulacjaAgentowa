package simulation.unit;

import simulation.attack.Attack;
import simulation.attack.IAttack;
import simulation.map.ISimulationMap;

public class Artillery extends AUnit {

    public Artillery(double hp, int range, boolean isA, ISimulationMap map) {
        super(hp, range, isA, map);
    }

    @Override
    public void takeDamage(IAttack attack) {
        if(attack.getType() == 1) this.hp -= 0.8 * attack.getDamage();
        else if(attack.getType() == 2) this.hp -= 1.2 * attack.getDamage();
        else if(attack.getType() == 3) this.hp -= attack.getDamage();
    }

    @Override
    public IAttack dealDamage() {
        return new Attack(35, 3);
    }

    @Override
    public String toString() {
        if(this.isA) return "A";
        else return "a";
    }
}
