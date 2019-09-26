package de.szut.simNil.binaryMaple;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractNode<T extends Comparable<T>> {
    @Nullable
    protected T value;

    public AbstractNode() {
    }

    public AbstractNode(@NotNull T value) {
        this.value = value;
    }

    @Nullable
    public T getValue() {
        return value;
    }

    public void setValue(@NotNull T value) {
        this.value = value;
    }

    public String toString() {
        return getValue() != null ? getValue().toString() : "null";
    }
}
