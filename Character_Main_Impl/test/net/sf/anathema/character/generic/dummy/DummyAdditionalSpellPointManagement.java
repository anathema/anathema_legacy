package net.sf.anathema.character.generic.dummy;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.impl.model.creation.bonus.IAdditionalMagicLearnPointManagement;

import java.util.List;

public class DummyAdditionalSpellPointManagement implements IAdditionalMagicLearnPointManagement {

  @Override
  public List<IMagic> spendOn(List<IMagic> magicToHandle) {
    return magicToHandle;
  }

  @Override
  public int getPointsSpent() {
    return 0;
  }

  @Override
  public int getAdditionalPointsAmount() {
    return 0;
  }

  @Override
  public void clear() {
    //Nothing to do
  }
}