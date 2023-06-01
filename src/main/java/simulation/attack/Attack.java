package simulation.attack;

public class Attack implements IAttack {

    private double damage;
    private int type;

    public Attack(double damage, int type) {
        this.damage = damage;
        this.type = type;
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(double damage, int type) {
        this.damage = damage;
        this.type = type;
    }

    @Override
    public int getType() {
        return this.type;
    }
}
