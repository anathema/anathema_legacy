package net.sf.anathema.character.main.template.points;

import net.sf.anathema.lib.lang.clone.ICloneable;

public interface IAttributeCreationPoints extends ICloneable<IAttributeCreationPoints>, IFavorableTraitCreationPoints  {

  int getPrimaryCount();

  int getSecondaryCount();

  int getTertiaryCount();
  
  @Override
  int getFavorableTraitCount();

  int getCount(AttributeGroupPriority priority);

  int[] getCounts();
}