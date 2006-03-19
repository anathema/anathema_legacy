package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.lib.util.Identificate;

public class CharmAttribute extends Identificate implements ICharmAttribute {

  private final boolean visualized;

  public CharmAttribute(String id, boolean visualized) {
    super(id);
    this.visualized = visualized;
  }

  public boolean isVisualized() {
    return visualized;
  }
}