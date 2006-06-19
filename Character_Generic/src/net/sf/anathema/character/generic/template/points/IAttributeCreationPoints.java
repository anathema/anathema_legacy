package net.sf.anathema.character.generic.template.points;

import net.sf.anathema.lib.lang.clone.ICloneable;

public interface IAttributeCreationPoints extends ICloneable<IAttributeCreationPoints> {

  public int getPrimaryCount();

  public int getSecondaryCount();

  public int getTertiaryCount();

  public int getCount(AttributeGroupPriority priority);

  public int[] getCounts();
}