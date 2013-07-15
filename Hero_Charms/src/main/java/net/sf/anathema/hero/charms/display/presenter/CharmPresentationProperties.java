package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

public interface CharmPresentationProperties extends TreePresentationProperties {

  public boolean supportsCharacterType(CharacterType type);
}
