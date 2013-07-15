package net.sf.anathema.hero.charms.display;

import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.display.tree.CharacterCharmTreePresenter;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

import static net.sf.anathema.hero.display.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 0)
public class CharmInitializer implements HeroModelInitializer {
  private IApplicationModel applicationModel;

  public CharmInitializer(IApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Resources resources) {
    boolean canLearnCharms = CharmsModelFetcher.fetch(hero) != null;
    if (!canLearnCharms) {
      return;
    }
    MagicDescriptionProvider provider = CharmDescriptionProviderExtractor.CreateFor(applicationModel, resources);
    CharmDisplayModel model = new CharmDisplayModel(hero, provider);
    CharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
    CharmDisplayPropertiesMap propertiesMap = new CharmDisplayPropertiesMap(applicationModel.getObjectFactory());
    TreePresentationProperties presentationProperties = propertiesMap.getDisplayProperties(characterType);
    String header = resources.getString("CardView.CharmConfiguration.CharmSelection.Title");
    CharmView charmView = sectionView.addView(header, CharmView.class, characterType);
    CharacterCharmTreePresenter treePresenter = new CharacterCharmTreePresenter(resources, charmView, model, presentationProperties, propertiesMap);
    treePresenter.initPresentation();
    //MagicDetailPresenter detailPresenter = createMagicDetailPresenter();
    //new MagicAndDetailPresenter(detailPresenter, treePresenter).initPresentation();
  }
}
