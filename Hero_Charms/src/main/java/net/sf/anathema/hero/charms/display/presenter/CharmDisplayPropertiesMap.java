package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.hero.utilities.CharacterSpecificsMap;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

public class CharmDisplayPropertiesMap {
  private final CharacterSpecificsMap<CharmPresentationProperties> map;

  public CharmDisplayPropertiesMap(ObjectFactory objectFactory) {
    this.map = new CharacterSpecificsMap<>(objectFactory, CharmPresentationProperties.class, new NullCharmPresentationProperties());
  }

  public TreePresentationProperties getDisplayProperties(CharacterType characterType) {
    return map.getForCharacterType(characterType);
  }
}