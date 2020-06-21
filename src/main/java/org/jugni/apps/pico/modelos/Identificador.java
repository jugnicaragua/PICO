package org.jugni.apps.pico.modelos;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Clase que proporciona identificador a entidades 
 * @author :Gustavo Castro <gacsnic75@gmail.com>
 * @param <T>
 * @license : GPLv3
 */
@MappedSuperclass
public abstract class Identificador<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 //   @GeneratedValue(generator = "default_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "Id")
    protected T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Identificador<?> other = (Identificador<?>) obj;
        return Objects.equals(this.id, other.id);
    }

}
