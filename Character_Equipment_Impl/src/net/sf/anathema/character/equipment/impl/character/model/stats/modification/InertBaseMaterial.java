package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

public class InertBaseMaterial implements BaseMaterial {

  @Override
  public boolean isOrichalcumBased() {
    return false;
  }

  @Override
  public boolean isJadeBased() {
    return false;
  }

  @Override
  public boolean isMoonsilverBased() {
    return false;
  }

  @Override
  public boolean isStarmetalBased() {
    return false;
  }

  @Override
  public boolean isSoulsteelBased() {
    return false;
  }

  @Override
  public boolean isAdamantBased() {
    return false;
  }
  
  @Override
  public boolean equals(Object obj) {
	  return obj instanceof InertBaseMaterial;
  }
}