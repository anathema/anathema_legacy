package net.sf.anathema.character.main.magic.display.view.charmtree;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.charms.display.AbstractCharmPresentationProperties;
import net.sf.anathema.hero.charms.display.CharmPresentationProperti2;
import net.sf.anathema.hero.charms.display.RegisteredCharmPresentationProperties;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

import java.util.Collection;

public class CharmDisplayPropertiesMap {
  private ObjectFactory objectFactory;

  public CharmDisplayPropertiesMap(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public TreePresentationProperties getDisplayProperties(CharacterType characterType) {
    Collection<CharmPresentationProperti2> allObjects = objectFactory.instantiateAll(RegisteredCharmPresentationProperties.class);
    for (CharmPresentationProperti2 properties : allObjects) {
      if (properties.supportsCharacterType(characterType)) {
        return properties;
      }
    }
    return new AbstractCharmPresentationProperties() {
      @Override
      public boolean supportsCharacterType(CharacterType type) {
        return true;
      }
    };
  }
}
