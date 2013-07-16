package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.character.main.magic.charm.ICharmGroup;

public interface CharmGroupInformer {
  boolean hasGroupSelected();

  ICharmGroup getCurrentGroup();
}