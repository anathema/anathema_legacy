package net.sf.anathema.charmtree.view;

import net.sf.anathema.character.generic.magic.charms.ICharmGroup;

public interface CharmGroupInformer {
  boolean hasGroupSelected();

  ICharmGroup getCurrentGroup();
}