package current_matches.score.values;
public enum MatchValues implements Values<MatchValues> {
    ZERO,ONE,TWO, THREE;
    @Override
    public MatchValues getNextValues(){
        if (this == THREE){
            throw new IllegalArgumentException("Three point");
        } else {
            return MatchValues.values()[this.ordinal()+1];
        }
    }
}
