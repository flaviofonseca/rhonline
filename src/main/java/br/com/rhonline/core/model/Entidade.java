package br.com.rhonline.core.model;

import java.util.Objects;

public abstract class Entidade {
    public abstract long getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entidade)) return false;
        Entidade that = (Entidade) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
