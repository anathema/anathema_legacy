package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.lib.util.Identificate;

public class CharmAttribute extends Identificate implements ICharmAttribute {

  private final boolean visualized;
  private final String value;

  public CharmAttribute(String id, boolean visualized) {
    this(id, visualized, null);
  }
  
  public CharmAttribute(String id, boolean visualized, String value) {
    super(id);
    this.visualized = visualized;
    this.value = value;
  }

  public boolean isVisualized() {
    return visualized;
  }
  
  public String getValue() {
    return value;
  }
}