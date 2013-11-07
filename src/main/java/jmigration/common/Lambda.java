package jmigration.common;

public interface Lambda<T, R> {
    R execute(T arg);
}
