package net.sf.anathema.lib.file;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class RelativePath {

  public final String relativePath;

  public RelativePath(String relativePath) {
    this.relativePath = relativePath;
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return relativePath.hashCode();
  }

  @Override
  public String toString() {
    return relativePath;
  }
}