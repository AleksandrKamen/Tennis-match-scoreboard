package current_matches.score.values;
public enum MatchValues implements Values<MatchValues> {
    ZERO,ONE,TWO;
    @Override
    public MatchValues getNextValues(){
         return MatchValues.values()[this.ordinal()+1];
    }
}
