package net.sf.anathema.hero.combos.display;

import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.charm.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.main.presenter.magic.combo.ComboConfigurationModel;
import net.sf.anathema.character.main.presenter.magic.combo.ComboConfigurationPresenter;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.character.main.view.magic.IComboConfigurationView;
import net.sf.anathema.framework.IApplicationModel;
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
    IComboConfigurationView comboView = sectionView.addView(header, IComboConfigurationView.class, hero.getTemplate().getTemplateType().getCharacterType());
    MagicDescriptionProvider magicDescriptionProvider = CharmDescriptionProviderExtractor.CreateFor(model, resources);
    ComboConfigurationModel comboModel = new ComboConfigurationModel(hero, magicDescriptionProvider);
    new ComboConfigurationPresenter(hero, resources, comboModel, comboView).initPresentation();

  }
}
