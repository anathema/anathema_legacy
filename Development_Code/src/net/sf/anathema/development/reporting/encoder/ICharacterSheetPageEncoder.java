package net.sf.anathema.development.reporting.encoder;

import org.dom4j.Element;

public interface ICharacterSheetPageEncoder {

  public int encodeBand(Element bandElement);

  public String getGroupName();

  public String getPrintWhenExpression();
}