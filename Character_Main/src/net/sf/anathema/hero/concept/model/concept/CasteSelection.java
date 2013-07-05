package net.sf.anathema.hero.concept.model.concept;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.lib.control.ChangeListener;

public interface CasteSelection {

  CasteType getType();

  void setType(CasteType type);

  void addChangeListener(ChangeListener listener);

  boolean isNotSelected();
}