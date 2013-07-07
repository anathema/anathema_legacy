package net.sf.anathema.character.main.presenter.magic;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.charms.CharmsModel;
import net.sf.anathema.character.main.magic.display.view.charmtree.AbstractCharmTreeViewProperties;
import net.sf.anathema.character.main.magic.display.view.charmtree.NullSpecialCharm;
import net.sf.anathema.lib.resources.Resources;

public class CharacterCharmTreeViewProperties extends AbstractCharmTreeViewProperties implements CharmIdMap {

  private final CharmsModel configuration;

  public CharacterCharmTreeViewProperties(Resources resources, CharmsModel configuration, MagicDescriptionProvider magicDescriptionProvider) {
    super(resources, magicDescriptionProvider);
    this.configuration = configuration;
  }

  @Override
  public ICharm getCharmById(String id) {
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