package net.sf.anathema.charmentry.model.data;

import net.sf.anathema.character.generic.magic.general.ICost;

public interface IConfigurableCost extends ICost {
  void setValue(String value);

  void setText(String text);
}