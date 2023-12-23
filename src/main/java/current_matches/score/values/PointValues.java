package current_matches.score.values;

public enum PointValues implements Values<PointValues> {
    ZERO, FIFTEEN, THIRTY, FORTY, ADVANTAGE;

    @Override
    public PointValues getNextValues() {
        if (this == ADVANTAGE) {
            throw new IllegalArgumentException("values = ADVANTAGE");
        } else {
            return PointValues.values()[this.ordinal() + 1];
        }
    }

    public String getString() {
        return switch (this) {
            case ZERO -> "0";
            case FIFTEEN -> "15";
            case THIRTY -> "30";
            case FORTY -> "40";
            case ADVANTAGE -> "AD";
        };
    }
}
