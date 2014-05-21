package net.sf.anathema.hero.template.points;

import net.sf.anathema.lib.util.Identifier;

public enum AttributeGroupPriority implements Identifier {

  Primary {
    @Override
    public void accept(IAttributeGroupPriorityVisitor visitor) {
      visitor.acceptPrimary();
    }
  },
  Secondary {
    @Override
    public void accept(IAttributeGroupPriorityVisitor visitor) {
      visitor.acceptSecondary();
    }
  },
  Tertiary {
    @Override
    public void accept(IAttributeGroupPriorityVisitor visitor) {
      visitor.acceptTertiary();
    }
  };

  public abstract void accept(IAttributeGroupPriorityVisitor visitor);

  @Override
  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}