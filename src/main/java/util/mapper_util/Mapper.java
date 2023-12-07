package util.mapper_util;

public interface Mapper<F,T> {
    T mapFrom(F object);
}
