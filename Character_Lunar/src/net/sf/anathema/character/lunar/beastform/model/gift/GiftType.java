package net.sf.anathema.character.lunar.beastform.model.gift;

import net.sf.anathema.character.library.quality.presenter.IQualityType;

public enum GiftType implements IQualityType {

  Gift;

  public String getId() {
    return name();
  }
}