package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;

public class SubeffectCharm implements ISubeffectCharm {

  private final String charmId;
  private final String[] subeffectIds;

  public SubeffectCharm(String charmId, String[] subeffectIds) {
    this.charmId = charmId;
    this.subeffectIds = subeffectIds;
  }

  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitSubeffectCharm(this);
  }

  public String getCharmId() {
    return charmId;
  }

  public String[] getSubeffectIds() {
    return subeffectIds;
  }
}