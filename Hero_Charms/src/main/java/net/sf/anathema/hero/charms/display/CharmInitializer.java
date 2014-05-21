package net.sf.anathema.hero.charms.display;

import net.sf.anathema.character.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.character.framework.display.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.display.model.CharmDisplayModel;
import net.sf.anathema.hero.charms.display.presenter.CharmDescriptionProviderExtractor;
import net.sf.anathema.hero.charms.display.presenter.CharmDisplayPropertiesMap;
import net.sf.anathema.hero.charms.display.tree.CharacterCharmTreePresenter;
import net.sf.anathema.hero.charms.display.view.CharmView;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.display.presenter.HeroModelInitializer;
import net.sf.anathema.hero.display.presenter.RegisteredInitializer;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtractor;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

import static net.sf.anathema.hero.display.HeroModelGroup.Magic;

@RegisteredInitializer(Magic)
@Weight(weight = 0)
public class CharmInitializer implements HeroModelInitializer {
  private final IApplicationModel applicationModel;
  private final HeroEnvironment heroEnvironment;

  public CharmInitializer(IApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
    this.heroEnvironment = HeroEnvironmentExtractor.getGenerics(applicationModel);
  }

  @Override
  public void initialize(SectionView sectionView, Hero hero, Environment environment) {
    boolean canLearnCharms = CharmsModelFetcher.fetch(hero) != null;
    if (!canLearnCharms) {
      return;
    }
    MagicDescriptionProvider provider = CharmDescriptionProviderExtractor.CreateFor(applicationModel, environment);
    CharmDisplayModel model = new CharmDisplayModel(hero, provider);
    CharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
    CharmDisplayPropertiesMap propertiesMap = new CharmDisplayPropertiesMap(environment);
    TreePresentationProperties presentationProperties = propertiesMap.getDisplayProperties(characterType);
    String header = environment.getString("CardView.CharmConfiguration.CharmSelection.Title");
    CharmView charmView = sectionView.addView(header, CharmView.class);
    CharmIdMap charmCache = getCharmIdMap();
    CharacterCharmTreePresenter treePresenter = new CharacterCharmTreePresenter(environment, charmView, model, presentationProperties, propertiesMap, charmCache, provider);
    treePresenter.initPresentation();
    //MagicDetailPresenter detailPresenter = createMagicDetailPresenter();
    //new MagicAndDetailPresenter(detailPresenter, treePresenter).initPresentation();
  }

  private CharmIdMap getCharmIdMap() {
    return heroEnvironment.getDataSet(CharmCache.class);
  }
}