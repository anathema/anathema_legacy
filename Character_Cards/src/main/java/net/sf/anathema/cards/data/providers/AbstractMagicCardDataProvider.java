package net.sf.anathema.cards.data.providers;

import net.sf.anathema.character.main.magic.description.MagicDescription;
import net.sf.anathema.character.main.magic.model.Magic;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractMagicCardDataProvider implements ICardDataProvider {

  private final IApplicationModel model;
  private final Resources resources;

  protected AbstractMagicCardDataProvider(IApplicationModel model, Resources resources) {
    this.model = model;
    this.resources = resources;
  }

  protected Resources getResources() {
    return resources;
  }

  protected MagicDescription getMagicDescription(Magic magic) {
    return CharmDescriptionProviderExtractor.CreateFor(model, resources).getCharmDescription(magic);
  }
}
