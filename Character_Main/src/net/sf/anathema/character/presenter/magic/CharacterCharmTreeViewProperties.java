package net.sf.anathema.character.presenter.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.NullSpecialCharm;
import net.sf.anathema.lib.resources.Resources;

public class CharacterCharmTreeViewProperties extends AbstractCharmTreeViewProperties implements ICharmIdMap {

  private final ICharmConfiguration configuration;

  public CharacterCharmTreeViewProperties(Resources resources, ICharmConfiguration configuration,
                                          MagicDescriptionProvider magicDescriptionProvider) {
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