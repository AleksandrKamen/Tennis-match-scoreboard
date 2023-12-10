package current_matches.score.values;
public enum MatchValues implements Values<MatchValues> {
    ZERO,ONE,TWO;
    @Override
    public MatchValues getNextValues(){
         return MatchValues.values()[this.ordinal()+1];
    }
    public String getValue(){
        return switch (this){
            case ZERO -> "0";
            case ONE -> "1";
            case TWO -> "2";
        };
    }
}
