package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;

public class NullCharmSet implements CharmSet {

  @Override
  public Charm[] getCharms() {
    return new Charm[0];
  }

  @Override
  public Charm[] getMartialArtsCharms() {
    return new Charm[0];
  }
}