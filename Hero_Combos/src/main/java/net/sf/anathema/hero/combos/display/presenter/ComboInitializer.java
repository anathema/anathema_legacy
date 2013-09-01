package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.combos.model.ComboConfigurationModel;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.framework.environment.dependencies.Weight;

import static net.sf.anathema.hero.display.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 100)
public class ComboInitializer implements HeroModelInitializer {

  private IApplicationModel model;

  public ComboInitializer(IApplicationModel model) {
    this.model = model;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    boolean canLearnCharms = CharmsModelFetcher.fetch(hero) != null;
    if (!canLearnCharms) {
      return;
    }
    String header = environment.getString("CardView.CharmConfiguration.ComboCreation.Title");
    ComboConfigurationView comboView = sectionView.addView(header, ComboConfigurationView.class);
    MagicDescriptionProvider magicDescriptionProvider = CharmDescriptionProviderExtractor.CreateFor(model, environment);
    ComboConfigurationModel comboModel = new ComboConfigurationModel(hero, magicDescriptionProvider);
    new ComboConfigurationPresenter(hero, environment, comboModel, comboView).initPresentation();

  }
}
