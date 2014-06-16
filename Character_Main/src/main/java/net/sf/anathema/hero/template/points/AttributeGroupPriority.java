package net.sf.anathema.hero.template.points;

import net.sf.anathema.lib.util.Identifier;

public enum AttributeGroupPriority implements Identifier {

  Primary, Secondary, Tertiary;

  @Override
  public String getId() {
    return name();
  }
}