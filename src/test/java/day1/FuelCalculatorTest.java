package day1;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FuelCalculatorTest {

    @Nested
    class FuelCalulatorTests_calculateFuelForModuleByMassTests {

        FuelCalulator unitUnderTest = new FuelCalulator();

        @Test
        void mass_of_negative_should_throw_IllegalArgumentException() {
            assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> this.unitUnderTest.calculateFuelForModuleBy(-1));
        }
        @Test
        void mass_of_12_should_Calculate_2() {
            assertThat(this.unitUnderTest.calculateFuelForModuleBy(12)).isEqualTo(2);
        }

        @Test
        void mass_of_14_should_calculate_2() {
            assertThat(this.unitUnderTest.calculateFuelForModuleBy(14)).isEqualTo(2);
        }

        @Test
        void mass_of_1969_should_calculate_654() {
            assertThat(this.unitUnderTest.calculateFuelForModuleBy(1969));
        }

        @Test
        void mass_of_100756_should_calculate_33583() {
            assertThat(this.unitUnderTest.calculateFuelForModuleBy(33583));
        }
    }
    
    @Nested
    class FuelCalulatorTests_calculateFuelRequirementsForFuelTests {
        private FuelCalulator unitUnderTest = new FuelCalulator();

        @Test
        public void fuelRequirementsOf2_shouldBtwo() {
            assertThat(unitUnderTest.calculateFuelRequirementsForFuel(2)).isEqualTo(2);
        }


        @Test
        public void fuelRequirementsOf1969_shouldBe966() {
            assertThat(unitUnderTest.calculateFuelRequirementsForFuel(unitUnderTest.calculateFuelForModuleBy(1969))).isEqualTo(966);
        }

        @Test
        public void fuelRequirementsOf100756_shouldBe50346() {
            assertThat(unitUnderTest.calculateFuelRequirementsForFuel(unitUnderTest.calculateFuelForModuleBy(100756))).isEqualTo(50346);
        }
    }
    @Test
    public void verification_star_one() throws IOException {
        FuelCalulator fuelCalulator = new FuelCalulator();
        Path inputPath = Paths.get("src/test/java/day1/input"); //Input must only have valid long values
        assertThat(Files.exists(inputPath)).isTrue();
        long result = Files.readAllLines(inputPath).stream().mapToLong(massOfModule -> fuelCalulator.calculateFuelForModuleBy(Long.parseLong(massOfModule))).sum();
       assertThat(result).isEqualTo(3456641L);

    }

    @Test
    public void verification_star_two() throws IOException {
        FuelCalulator fuelCalulator = new FuelCalulator();
        Path inputPath = Paths.get("src/test/java/day1/input"); //Input must only have valid long values
        assertThat(Files.exists(inputPath)).isTrue();
        long result = Files.readAllLines(inputPath).stream()
                .mapToLong(massOfModule -> fuelCalulator.calculateFuelRequirementsForFuel(fuelCalulator.calculateFuelForModuleBy(Long.parseLong(massOfModule)))).sum();
        assertThat(result).isEqualTo(5182078L);
    }
}
