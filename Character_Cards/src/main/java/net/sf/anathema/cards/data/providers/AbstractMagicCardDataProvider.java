package net.sf.anathema.cards.data.providers;

import net.sf.anathema.character.magic.description.MagicDescription;
import net.sf.anathema.character.magic.basic.Magic;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.framework.environment.Resources;

public abstract class AbstractMagicCardDataProvider implements ICardDataProvider {

  private final IApplicationModel model;
  private final Environment environment;

  protected AbstractMagicCardDataProvider(IApplicationModel model, Environment environment) {
    this.model = model;
    this.environment = environment;
  }

  protected Resources getResources() {
    return environment;
  }

  protected MagicDescription getMagicDescription(Magic magic) {
    return CharmDescriptionProviderExtractor.CreateFor(model, environment).getCharmDescription(magic);
  }
}
