package net.sf.anathema.character.main.magic.display.view.charmtree;

import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;

public interface CharmGroupInformer {
  boolean hasGroupSelected();

  ICharmGroup getCurrentGroup();
}