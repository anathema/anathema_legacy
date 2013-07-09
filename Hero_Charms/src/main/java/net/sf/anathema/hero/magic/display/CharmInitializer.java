package net.sf.anathema.hero.magic.display;

import net.sf.anathema.character.main.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.main.magic.display.view.charms.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmView;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateRegistry;
import net.sf.anathema.character.main.magic.model.charms.options.DefaultCharmTemplateRetriever;
import net.sf.anathema.character.main.presenter.initializers.HeroModelInitializer;
import net.sf.anathema.character.main.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.hero.magic.model.CharacterCharmModel;
import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.character.main.magic.display.view.charmtree.CharmDisplayPropertiesMap;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.hero.magic.display.tree.CharacterCharmTreePresenter;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

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
    boolean canLearnCharms = DefaultCharmTemplateRetriever.getNativeTemplate(hero).canLearnCharms();
    if (!canLearnCharms) {
      return;
    }
    ITemplateRegistry templateRegistry = CharacterGenericsExtractor.getGenerics(applicationModel).getTemplateRegistry();
    MagicDescriptionProvider provider = CharmDescriptionProviderExtractor.CreateFor(applicationModel, resources);
    CharacterCharmModel model = new CharacterCharmModel(hero, provider);
    HeroTemplate characterTemplate = hero.getTemplate();
    ITreePresentationProperties presentationProperties =
            characterTemplate.getPresentationProperties().getCharmPresentationProperties();
    CharmDisplayPropertiesMap propertiesMap = new CharmDisplayPropertiesMap(templateRegistry);
    String header = resources.getString("CardView.CharmConfiguration.CharmSelection.Title");
    CharmView charmView = sectionView.addView(header, CharmView.class, hero.getTemplate().getTemplateType().getCharacterType());
    CharacterCharmTreePresenter treePresenter = new CharacterCharmTreePresenter(resources, charmView, model, presentationProperties, propertiesMap);
    treePresenter.initPresentation();
    //MagicDetailPresenter detailPresenter = createMagicDetailPresenter();
    //new MagicAndDetailPresenter(detailPresenter, treePresenter).initPresentation();
  }
}
