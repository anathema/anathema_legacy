package net.sf.anathema.character.main.hero.initialization;

import net.sf.anathema.lib.util.Identifier;

public interface ModelTreeEntry {

  Iterable<Identifier> getRequiredModelIds();

  Identifier getModelId();
}
