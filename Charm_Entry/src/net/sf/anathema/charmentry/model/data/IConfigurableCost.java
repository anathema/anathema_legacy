package net.sf.anathema.charmentry.model.data;

import net.sf.anathema.character.generic.magic.general.ICost;

public interface IConfigurableCost extends ICost {
  public void setValue(String value);

  public void setText(String text);
}