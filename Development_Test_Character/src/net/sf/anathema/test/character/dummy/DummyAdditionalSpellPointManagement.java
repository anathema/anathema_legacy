package net.sf.anathema.test.character.dummy;

import java.util.List;

import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.impl.model.creation.bonus.IAdditionalMagicLearnPointManagement;

public class DummyAdditionalSpellPointManagement implements IAdditionalMagicLearnPointManagement {

  public List<IMagic> spendOn(List<IMagic> magicToHandle) {
    return magicToHandle;
  }

  public int getPointsSpent() {
    return 0;
  }

  public int getAdditionalPointsAmount() {
    return 0;
  }

  public void clear() {
    //Nothing to do
  }
}