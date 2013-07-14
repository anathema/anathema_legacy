package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.charms.display.presenter.AbstractCharmTreeViewProperties;
import net.sf.anathema.hero.charms.model.NullSpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.lib.resources.Resources;

public class CharacterCharmTreeViewProperties extends AbstractCharmTreeViewProperties implements CharmIdMap {

  private final CharmsModel configuration;

  public CharacterCharmTreeViewProperties(Resources resources, CharmsModel configuration, MagicDescriptionProvider magicDescriptionProvider) {
    super(resources, magicDescriptionProvider);
    this.configuration = configuration;
  }

  @Override
  public Charm getCharmById(String id) {
    if (isRequirementNode(id)) {
      return null;
    }
    return configuration.getCharmById(id);
  }

  @Override
  protected ISpecialCharm getSpecialCharm(String charmId) {
    for (ISpecialCharm special : configuration.getSpecialCharms()) {
      if (special.getCharmId().equals(charmId)) {
        return special;
      }
    }
    return new NullSpecialCharm();
  }
}