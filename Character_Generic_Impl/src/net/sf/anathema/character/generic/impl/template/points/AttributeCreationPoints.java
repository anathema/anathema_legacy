package net.sf.anathema.character.generic.impl.template.points;

import net.sf.anathema.character.generic.template.points.AttributeGroupPriority;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IAttributeGroupPriorityVisitor;
import net.sf.anathema.lib.lang.clone.ReflectionCloneableObject;

public class AttributeCreationPoints extends ReflectionCloneableObject<IAttributeCreationPoints> implements
    IAttributeCreationPoints {

  private final int primary;
  private final int secondary;
  private final int tertiary;

  public AttributeCreationPoints(int primary, int secondary, int tertiary) {
    this.primary = primary;
    this.secondary = secondary;
    this.tertiary = tertiary;
  }

  public int getPrimaryCount() {
    return primary;
  }

  public int getSecondaryCount() {
    return secondary;
  }

  public int getTertiaryCount() {
    return tertiary;
  }

  public int[] getCounts() {
    return new int[] { primary, secondary, tertiary };
  }

  public int getCount(AttributeGroupPriority priority) {
    final int[] count = new int[1];
    priority.accept(new IAttributeGroupPriorityVisitor() {
      public void acceptPrimary() {
        count[0] = primary;
      }

      public void acceptSecondary() {
        count[0] = secondary;
      }

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
}