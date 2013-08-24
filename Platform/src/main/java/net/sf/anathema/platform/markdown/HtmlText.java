package net.sf.anathema.platform.markdown;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class HtmlText {
  private String htmlText;

  public HtmlText(String htmlText) {
    this.htmlText = htmlText;
  }

  public String getHtmlText() {
    return htmlText;
  }

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
    return htmlText;
  }
}
