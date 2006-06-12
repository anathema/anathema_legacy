package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;

public class MultipleEffectCharm implements IMultipleEffectCharm {

  private final String charmId;
  private final String[] effectIds;

  public MultipleEffectCharm(String charmId, String[] effectIds) {
    this.charmId = charmId;
    this.effectIds = effectIds;
  }

  public String[] getSubeffectIds() {
    return effectIds;
  }

  public void accept(ISpecialCharmVisitor visitor) {
    visitor.visitMultipleEffectCharm(this);
  }

  public String getCharmId() {
    return charmId;
  }
}