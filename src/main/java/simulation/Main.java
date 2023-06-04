package simulation;

import simulation.map.creator.ISimulationMapCreator;
import simulation.map.creator.SimulationMapCreator;
import simulation.unit.creator.IUnitCreator;
import simulation.unit.creator.UnitCreator;

import java.util.Random;

public class Main {

    /**
     * Główna metoda przeprowadzająca działanie programu
     * @param args argumenty przekazywane na początku działaniu programu
     */
    public static void main(String[] args) {

        ISimulationMapCreator mapCreator = new SimulationMapCreator(10,5);
        IUnitCreator unitCreator = new UnitCreator(3,3,5,5,2,2);

        Random random = new Random();

        Simulation simulation = new Simulation(mapCreator, unitCreator, random.nextLong(), 100);
        simulation.runSimulation();
        System.out.println("KONIEC SYMULACJI");

    }
}
