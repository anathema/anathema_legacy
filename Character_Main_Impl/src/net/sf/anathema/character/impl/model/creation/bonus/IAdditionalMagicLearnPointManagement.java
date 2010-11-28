package net.sf.anathema.character.impl.model.creation.bonus;

import java.util.List;

import net.sf.anathema.character.generic.magic.IMagic;

public interface IAdditionalMagicLearnPointManagement {

  public List<IMagic> spendOn(List<IMagic> magicToHandle);

  public int getPointsSpent();

  public int getAdditionalPointsAmount();

  public void clear();
}