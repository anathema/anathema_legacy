package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeGroupPriorityVisitor;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class AttributeCreationPoints extends ReflectionCloneableObject<IAttributeCreationPoints> implements IAttributeCreationPoints {

  private final int primary;
  private final int secondary;
  private final int tertiary;
  private final int favoredPicks;
  private final int favoredDots;
  private final int genericDots;

  public AttributeCreationPoints(int primary, int secondary, int tertiary) {
    this(primary, secondary, tertiary, 0, 0, 0);
  }

  public AttributeCreationPoints(int primary, int secondary, int tertiary, int picks, int favoredDots, int genericDots) {
    this.primary = primary;
    this.secondary = secondary;
    this.tertiary = tertiary;
    this.favoredPicks = picks;
    this.favoredDots = favoredDots;
    this.genericDots = genericDots;
  }

  @Override
  public int getPrimaryCount() {
    return primary;
  }

  @Override
  public int getSecondaryCount() {
    return secondary;
  }

  @Override
  public int getTertiaryCount() {
    return tertiary;
  }

  @Override
  public int getFavorableTraitCount() {
    return favoredPicks;
  }

  @Override
  public int getFavoredDotCount() {
    return 0;
  }

  @Override
  public int getExtraFavoredDotCount() {
    return favoredDots;
  }

  @Override
  public int getExtraGenericDotCount() {
    return genericDots;
  }

  @Override
  public int[] getCounts() {
    return new int[]{primary, secondary, tertiary};
  }

  @Override
  public int getCount(AttributeGroupPriority priority) {
    final int[] count = new int[1];
    priority.accept(new IAttributeGroupPriorityVisitor() {
      @Override
      public void acceptPrimary() {
        count[0] = primary;
      }

      @Override
      public void acceptSecondary() {
        count[0] = secondary;
      }

      @Override
      public void acceptTertiary() {
        count[0] = tertiary;
      }
    });
    return count[0];
  }

  @Override
  public AttributeCreationPoints clone() {
    return (AttributeCreationPoints) super.clone();
  }

  @Override
  public int getDefaultDotCount() {
    // TODO Auto-generated method stub
    return 0;
  }
}