package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.IChangeListener;

public interface IPrerequisitesModel {

  ITraitType[] getPrimaryPrerequisiteTypes();

  void setEssenceMinimum(int minimum);

  void addModelListener(IChangeListener listener);

  int getEssenceMinimum();

  IGenericTrait getPrimaryPrerequisite();

  void setPrimaryPrerequisite(ITraitType type, int value);

  boolean isPermanentCharm();
}