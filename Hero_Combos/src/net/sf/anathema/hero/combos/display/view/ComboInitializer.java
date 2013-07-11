package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.display.view.charms.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.combos.display.presenter.ComboConfigurationPresenter;
import net.sf.anathema.hero.combos.display.presenter.ComboConfigurationView;
import net.sf.anathema.hero.combos.model.ComboConfigurationModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.hero.display.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 100)
public class ComboInitializer implements HeroModelInitializer {

  private IApplicationModel model;

  public ComboInitializer(IApplicationModel model) {
    this.model = model;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    boolean canLearnCharms = DefaultCharmTemplateRetriever.getNativeTemplate(hero).canLearnCharms();
    if (!canLearnCharms) {
      return;
    }
    String header = resources.getString("CardView.CharmConfiguration.ComboCreation.Title");
    ComboConfigurationView comboView = sectionView.addView(header, ComboConfigurationView.class, hero.getTemplate().getTemplateType().getCharacterType());
    MagicDescriptionProvider magicDescriptionProvider = CharmDescriptionProviderExtractor.CreateFor(model, resources);
    ComboConfigurationModel comboModel = new ComboConfigurationModel(hero, magicDescriptionProvider);
    new ComboConfigurationPresenter(hero, resources, comboModel, comboView).initPresentation();

  }
}
