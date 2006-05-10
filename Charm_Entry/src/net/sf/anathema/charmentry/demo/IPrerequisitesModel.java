package net.sf.anathema.charmentry.demo;

import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IPrerequisitesModel {

  public ITraitType[] getPrimaryPrerequisiteTypes();

  public void setEssenceMinimum(int minimum);

  public void addModelListener(IChangeListener listener);

  public int getEssenceMinimum();

  public IGenericTrait getPrimaryPrerequisite();

  public void setPrimaryPrerequisite(ITraitType type, int value);

  public boolean isPermanentCharm();
}