package net.sf.anathema.framework.preferences.persistence;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuppressWarnings("FieldCanBeLocal")
public class PreferenceKey {
  public final String key;

  public PreferenceKey(String key) {
    this.key = key;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(o, this);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}