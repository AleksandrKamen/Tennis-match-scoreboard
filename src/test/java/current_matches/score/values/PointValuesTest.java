package current_matches.score.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointValuesTest {
    @Test
    @DisplayName("test getNextPoint for ZERO")
    void getFifteen(){
        var zero = PointValues.ZERO;
        assertEquals(zero.getNextValues(), PointValues.FIFTEEN);
    }
    @Test
    @DisplayName("test getNextPoint for FIFTEEN")
    void getThirty(){
        var fifteen = PointValues.FIFTEEN;
        assertEquals(fifteen.getNextValues(), PointValues.THIRTY);
    }
    @Test
    @DisplayName("test getNextPoint for THIRTY")
    void getForty(){
        var thirty = PointValues.THIRTY;
        assertEquals(thirty.getNextValues(), PointValues.FORTY);
    }
    @Test
    @DisplayName("test getNextPoint for FORTY")
    void getAdvantage(){
        var forty = PointValues.FORTY;
        assertEquals(forty.getNextValues(), PointValues.ADVANTAGE);
    }
    @Test
    @DisplayName("test getNextPoint for ADVANTAGE")
    void getException(){
        var advantage = PointValues.ADVANTAGE;
        assertThrows(IllegalArgumentException.class,()-> advantage.getNextValues());
    }

}