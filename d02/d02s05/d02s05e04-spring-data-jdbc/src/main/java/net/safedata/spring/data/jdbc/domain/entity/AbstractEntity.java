package net.safedata.spring.data.jdbc.domain.entity;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

    public abstract void setId(final Integer id);
}
