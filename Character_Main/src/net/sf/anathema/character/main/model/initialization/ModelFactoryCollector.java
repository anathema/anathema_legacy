package net.sf.anathema.character.main.model.initialization;

import net.sf.anathema.character.main.model.CharacterModelFactory;

import java.util.Collection;

public interface ModelFactoryCollector {
  Collection<CharacterModelFactory> collect();
}