package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.display.view.FunctionalNodeProperties;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmsModel;

public class CharacterCharmIdMap implements CharmIdMap {

  private final CharmsModel configuration;
  private FunctionalNodeProperties functionalNodeProperties;

  public CharacterCharmIdMap(CharmsModel configuration, FunctionalNodeProperties functionalNodeProperties) {
    this.configuration = configuration;
    this.functionalNodeProperties = functionalNodeProperties;
  }

  @Override
  public Charm getCharmById(String id) {
    if (functionalNodeProperties.isRequirementNode(id)) {
      return null;
    }
    return configuration.getCharmById(id);
  }

}