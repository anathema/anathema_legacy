package net.sf.anathema.character.impl.model.creation.bonus;

import net.sf.anathema.character.generic.magic.IMagic;

import java.util.List;

public interface IAdditionalMagicLearnPointManagement {

  List<IMagic> spendOn(List<IMagic> magicToHandle);

  int getPointsSpent();

  int getAdditionalPointsAmount();

  void clear();
}