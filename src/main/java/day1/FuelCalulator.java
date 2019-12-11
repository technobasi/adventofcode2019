package day1;

public class FuelCalulator {
        public static final long SUBSTRACT_TWO = 2;
        public static final double DIVIDE_BY_THREE = 3.0;

        public long calculateFuelForModuleBy(long mass) {
            if(mass < 0) {
                throw new IllegalArgumentException("mass should not be negative");
            }
            return (long) Math.floor(mass / DIVIDE_BY_THREE) - SUBSTRACT_TWO;
        }

        public long calculateFuelRequirementsForFuel(long fuel) {
            long fuelRequirementsForFuel = calculateFuelForModuleBy(fuel);
            if(fuelRequirementsForFuel <= 0) {
                return fuel;
            } else {
                return calculateFuelRequirementsForFuel(fuelRequirementsForFuel) + fuel;
            }
        }
}
