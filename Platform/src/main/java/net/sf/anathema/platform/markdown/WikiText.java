package net.sf.anathema.platform.markdown;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class WikiText {

  private String canonicalText;

  public WikiText(String canonicalText) {
    this.canonicalText = canonicalText;
  }

  public String getCanonicalText() {
    return canonicalText;
  }

  @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return canonicalText;
  }
}
