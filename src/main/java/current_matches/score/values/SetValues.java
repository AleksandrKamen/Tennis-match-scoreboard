package current_matches.score.values;

public enum SetValues implements Values<SetValues>{
    ZERO,ONE,TWO,THREE;
    @Override
    public SetValues getNextValues() {
        return SetValues.values()[this.ordinal()+1];
    }
}
