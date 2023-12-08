package current_matches.score.values;

public enum GameValues implements Values<GameValues> {
    ZERO,ONE,TWO, THREE, FOUR, FIVE, SIX,SEVEN;

    @Override
    public GameValues getNextValues(){
        if (this == SEVEN){
            throw new IllegalArgumentException("values = SEVEN");
        } else {
            return GameValues.values()[this.ordinal()+1];
        }
    }
}
