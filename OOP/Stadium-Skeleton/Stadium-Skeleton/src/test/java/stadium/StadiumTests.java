package stadium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StadiumTests {
    private Stadium stadium;
    private Athlete athlete1;
    private Athlete athlete2;

    @BeforeEach
    void setUp() {
        stadium = new Stadium("National Stadium", 2);
        athlete1 = new Athlete("Ivanko", 80);
        athlete2 = new Athlete("Sasho", 90);
    }

    @Test
    void constructor_ShouldThrow_WhenNameIsNull() {
        NullPointerException ex = assertThrows(NullPointerException.class,
                () -> new Stadium(null, 5));
        assertEquals("Invalid stadium name!", ex.getMessage());
    }

    @Test
    void constructor_ShouldThrow_WhenNameIsEmpty() {
        NullPointerException ex = assertThrows(NullPointerException.class,
                () -> new Stadium("   ", 5));
        assertEquals("Invalid stadium name!", ex.getMessage());
    }

    @Test
    void constructor_ShouldThrow_WhenCapacityIsNegative() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Stadium("Test", -1));
        assertEquals("Invalid capacity!", ex.getMessage());
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    void addAthlete_ShouldAdd_WhenValidData() {
        stadium.addAthlete(athlete1);
        assertEquals(1, stadium.getCount());
        assertEquals(athlete1, stadium.getAthlete("Ivanko"));
    }

    @Test
    void addAthlete_ShouldThrow_WhenStadiumIsFull() {
        stadium.addAthlete(athlete1);
        stadium.addAthlete(athlete2);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> stadium.addAthlete(new Athlete("Petur", 70)));
        assertEquals("No more space in the stadium!", ex.getMessage());
    }

    @Test
    void addAthlete_ShouldThrow_WhenAthleteExists() {
        stadium.addAthlete(athlete1);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> stadium.addAthlete(new Athlete("Ivanko", 50)));
        assertEquals("This athlete is already in the stadium!", ex.getMessage());
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    void getAthlete_ShouldReturnAthlete_WhenExists() {
        stadium.addAthlete(athlete1);
        Athlete result = stadium.getAthlete("Ivanko");
        assertEquals(athlete1, result);
    }

    @Test
    void getAthlete_ShouldReturnNull_WhenNotFound() {
        assertNull(stadium.getAthlete("Unknown"));
    }

    @Test
    void getFittestAthlete_ShouldReturnWithHighestStamina() {
        stadium.addAthlete(athlete1);
        stadium.addAthlete(athlete2);
        assertEquals(athlete2, stadium.getFittestAthlete());
    }

    @Test
    void getFittestAthlete_ShouldReturnNull_WhenEmpty() {
        assertNull(stadium.getFittestAthlete());
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    void removeAthlete_ShouldReturnTrue_WhenRemoved() {
        stadium.addAthlete(athlete1);
        boolean removed = stadium.removeAthlete("Ivanko");
        assertTrue(removed);
        assertEquals(0, stadium.getCount());
    }

    @Test
    void removeAthlete_ShouldReturnFalse_WhenNotFound() {
        boolean removed = stadium.removeAthlete("Unknown");
        assertFalse(removed);
    }

    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    void getName_ShouldReturnCorrectName() {
        assertEquals("National Stadium", stadium.getName());
    }

    @Test
    void getCapacity_ShouldReturnCorrectCapacity() {
        assertEquals(2, stadium.getCapacity());
    }

    @Test
    void getCount_ShouldReturnCorrectNumberOfAthletes() {
        assertEquals(0, stadium.getCount());
        stadium.addAthlete(athlete1);
        assertEquals(1, stadium.getCount());
    }
}
