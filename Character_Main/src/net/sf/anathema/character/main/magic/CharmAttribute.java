package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.main.magic.charms.ICharmAttribute;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class CharmAttribute extends SimpleIdentifier implements ICharmAttribute {

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

  @Override
  public boolean isVisualized() {
    return visualized;
  }

  @Override
  public String getValue() {
    return value;
  }
}