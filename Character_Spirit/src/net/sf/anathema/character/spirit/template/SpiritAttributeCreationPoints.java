package net.sf.anathema.character.spirit.template;

import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class SpiritAttributeCreationPoints extends ReflectionCloneableObject<IAttributeCreationPoints> implements IAttributeCreationPoints {

  @Override
  public int getCount(AttributeGroupPriority priority) {
    return 0;
  }

  @Override
  public int[] getCounts() {
    return new int[]{getPrimaryCount(), 0, 0};
  }

  @Override
  public int getFavorableTraitCount() {
    return 0;
  }

  @Override
  public int getPrimaryCount() {
    return 0;
  }

  @Override
  public int getSecondaryCount() {
    return 0;
  }

  @Override
  public int getTertiaryCount() {
    return 0;
  }

  @Override
  public int getDefaultDotCount() {
    return 0;
  }

  @Override
  public int getExtraFavoredDotCount() {
    return 0;
  }

  @Override
  public int getExtraGenericDotCount() {
    return 0;
  }

  @Override
  public int getFavoredDotCount() {
    return 0;
  }
}
