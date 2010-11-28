package net.sf.anathema.character.db.aspect;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum DBAspect implements ICasteType {

  Air, Earth, Fire, Water, Wood;

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return name();
  }
}