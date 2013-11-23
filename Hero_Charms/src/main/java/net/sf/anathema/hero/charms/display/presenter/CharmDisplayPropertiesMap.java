package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.hero.utilities.ForCharacterType;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

import java.util.Collection;

public class CharmDisplayPropertiesMap {
  private ObjectFactory objectFactory;

  public CharmDisplayPropertiesMap(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public TreePresentationProperties getDisplayProperties(CharacterType characterType) {
    Collection<CharmPresentationProperties> allObjects = objectFactory.instantiateAllImplementers(CharmPresentationProperties.class);
    for (CharmPresentationProperties properties : allObjects) {
      if (properties.getClass().getAnnotation(ForCharacterType.class).value().equals(characterType.getId())) {
        return properties;
      }
    }
    return new NullCharmPresentationProperties();
  }

}
