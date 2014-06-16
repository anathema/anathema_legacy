package net.sf.anathema.hero.template.points;

import net.sf.anathema.lib.lang.clone.ICloneable;

public interface IAttributeCreationPoints extends ICloneable<IAttributeCreationPoints>, IFavorableTraitCreationPoints  {

  @Override
  int getFavorableTraitCount();

  int getCount(AttributeGroupPriority priority);

  int[] getCounts();
}