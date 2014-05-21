package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.magic.charm.Charm;

public interface CharmIdMap {
  Charm getCharmById(String charmId);
}