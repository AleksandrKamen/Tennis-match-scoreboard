package current_matches;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @Test
    @DisplayName("test getNextPoint -  return FIFTEEN")
    void getFifteen(){
        var zero = Point.ZERO;
        assertEquals(zero.getNextPoint(),Point.FIFTEEN);
    }
    @Test
    @DisplayName("test getNextPoint -  return THIRTY")
    void getThirty(){
        var fifteen = Point.FIFTEEN;
        assertEquals(fifteen.getNextPoint(),Point.THIRTY);
    }
    @Test
    @DisplayName("test getNextPoint -  return FORTY")
    void getForty(){
        var thirty = Point.THIRTY;
        assertEquals(thirty.getNextPoint(),Point.FORTY);
    }
    @Test
    @DisplayName("test getNextPoint -  return ADVANTAGE")
    void getAdvantage(){
        var forty = Point.FORTY;
        assertEquals(forty.getNextPoint(),Point.ADVANTAGE);
    }
    @Test
    @DisplayName("test getNextPoint -  return ADVANTAGE")
    void getException(){
        var advantage = Point.ADVANTAGE;
        assertThrows(IllegalArgumentException.class,()-> advantage.getNextPoint());
    }

}