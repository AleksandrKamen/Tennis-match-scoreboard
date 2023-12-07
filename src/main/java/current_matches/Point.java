package current_matches;
public enum Point {
    ZERO, FIFTEEN, THIRTY, FORTY, ADVANTAGE;
public Point getNextPoint(){
    if (this == ADVANTAGE){
        throw new IllegalArgumentException("ADVANTAGE point");
    } else {
        return Point.values()[this.ordinal()+1];
    }
}


}
