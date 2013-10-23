package net.sf.anathema.framework.repository.access.printname;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SimpleRepositoryId implements RepositoryId {

  private String id;

  public SimpleRepositoryId(String id) {
    this.id = id;
  }

  @Override
  public String getStringRepresentation() {
    return id;
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }
}