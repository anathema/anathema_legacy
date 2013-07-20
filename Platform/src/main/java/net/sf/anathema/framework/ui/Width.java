package net.sf.anathema.framework.ui;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Width {

  public final int width;

  public Width(int width) {
    this.width = width;
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return width;
  }
}