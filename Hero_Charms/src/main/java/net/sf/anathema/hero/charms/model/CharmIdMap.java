package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.charm.Charm;

public interface CharmIdMap {
  Charm getCharmById(String charmId);
}