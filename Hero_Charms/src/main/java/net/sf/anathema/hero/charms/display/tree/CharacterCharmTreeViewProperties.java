package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.display.view.AbstractCharmTreeViewProperties;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmsModel;

public class CharacterCharmTreeViewProperties extends AbstractCharmTreeViewProperties implements CharmIdMap {

  private final CharmsModel configuration;

  public CharacterCharmTreeViewProperties(CharmsModel configuration) {
    this.configuration = configuration;
  }

  @Override
  public Charm getCharmById(String id) {
    if (isRequirementNode(id)) {
      return null;
    }
    return configuration.getCharmById(id);
  }

}