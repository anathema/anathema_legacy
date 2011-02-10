package net.sf.anathema.character.generic.template.points;

import net.sf.anathema.lib.lang.clone.ICloneable;

public interface IAttributeCreationPoints extends ICloneable<IAttributeCreationPoints>, IFavorableTraitCreationPoints  {

  public int getPrimaryCount();

  public int getSecondaryCount();

  public int getTertiaryCount();
  
  public int getFavorableTraitCount();

  public int getCount(AttributeGroupPriority priority);

  public int[] getCounts();
}