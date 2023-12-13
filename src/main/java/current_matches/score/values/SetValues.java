package current_matches.score.values;

public enum SetValues implements Values<SetValues, String>{
    ZERO,ONE,TWO,THREE;
    @Override
    public SetValues getNextValues() {
        return SetValues.values()[this.ordinal()+1];
    }

    public String getValue(){
        return switch (this){
            case ZERO -> "0";
            case ONE -> "1";
            case TWO -> "2";
            case THREE -> "3";
        };
    }
}
