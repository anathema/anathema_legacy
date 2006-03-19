package net.sf.anathema.character.meritsflaws.model.perk;

import net.sf.anathema.character.library.quality.presenter.IQualityType;

public enum PerkType implements IQualityType {
  Merit, Flaw;

  public String getId() {
    return name();
  }
}