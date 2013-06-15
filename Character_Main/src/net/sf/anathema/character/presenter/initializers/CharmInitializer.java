package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.CharacterCharmModel;
import net.sf.anathema.character.presenter.magic.charm.CharacterCharmTreePresenter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.charmtree.presenter.view.CharmDisplayPropertiesMap;
import net.sf.anathema.charmtree.presenter.view.ICharmView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

import static net.sf.anathema.character.model.CharacterModelGroup.Magic;
import static net.sf.anathema.character.model.CharacterModelGroup.NaturalTraits;

@RegisteredInitializer(Magic)
@Weight(weight = 0)
public class CharmInitializer implements CoreModelInitializer {
  private IApplicationModel applicationModel;

  public CharmInitializer(IApplicationModel applicationModel) {
    this.applicationModel = applicationModel;
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    boolean canLearnCharms = character.getCharacterTemplate().getMagicTemplate().getCharmTemplate().canLearnCharms();
    if (!canLearnCharms) {
      return;
    }
    ITemplateRegistry templateRegistry = CharacterGenericsExtractor.getGenerics(applicationModel).getTemplateRegistry();
    MagicDescriptionProvider provider = CharmDescriptionProviderExtractor.CreateFor(applicationModel, resources);
    CharacterCharmModel model = new CharacterCharmModel(character, provider);
    ICharacterTemplate characterTemplate = character.getCharacterTemplate();
    ITreePresentationProperties presentationProperties =
            characterTemplate.getPresentationProperties().getCharmPresentationProperties();
    CharmDisplayPropertiesMap propertiesMap = new CharmDisplayPropertiesMap(templateRegistry);
    String header = resources.getString("CardView.CharmConfiguration.CharmSelection.Title");
    ICharmView charmView = sectionView.addView(header, ICharmView.class, character.getCharacterType());
    CharacterCharmTreePresenter treePresenter = new CharacterCharmTreePresenter(resources, charmView, model, presentationProperties, propertiesMap);
    treePresenter.initPresentation();
    //MagicDetailPresenter detailPresenter = createMagicDetailPresenter();
    //new MagicAndDetailPresenter(detailPresenter, treePresenter).initPresentation();
  }
}
