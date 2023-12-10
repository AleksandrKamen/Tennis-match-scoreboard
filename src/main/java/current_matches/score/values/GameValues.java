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

    public String getValue(){
        return switch (this){
            case ZERO -> "0";
            case ONE -> "1";
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
            case SIX -> "6";
            case SEVEN -> "7";
        };
    }
}
