package net.sf.anathema.hero.dummy.models;

import net.sf.anathema.hero.concept.CasteSelection;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.lib.control.ChangeListener;

public class NullCasteSelection implements CasteSelection{
  @Override
  public CasteType getType() {
    return CasteType.NULL_CASTE_TYPE;
  }

  @Override
  public void setType(CasteType type) {
    //nothing to do
  }

  @Override
  public void addChangeListener(ChangeListener listener) {
    //nothing to do
  }

  @Override
  public boolean isNotSelected() {
    return true;
  }
}