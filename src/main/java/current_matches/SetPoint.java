package current_matches;

public enum SetPoint {
    ZERO,ONE,TWO, THREE, FOUR, FIVE, SIX,SEVEN;

    public SetPoint getNextSetPoint(){
        if (this == SEVEN){
            throw new IllegalArgumentException("SEVEN point");
        } else {
            return SetPoint.values()[this.ordinal()+1];
        }
    }
}
