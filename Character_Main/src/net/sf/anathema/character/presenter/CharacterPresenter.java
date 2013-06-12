package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.presenter.magic.MagicPresenter;
import net.sf.anathema.character.view.BackgroundView;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.character.view.IAdvantageViewFactory;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.IConceptAndRulesViewFactory;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.presenter.view.ContentView;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType.Abilities;
import static net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType.Advantages;
import static net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType.Concept;
import static net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType.Magic;
import static net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType.Miscellaneous;
import static net.sf.anathema.character.generic.framework.CharacterGenericsExtractor.getGenerics;

public class CharacterPresenter implements Presenter, MultipleContentViewPresenter {

  private final ICharacter character;
  private final CharacterView characterView;
  private final IApplicationModel anathemaModel;
  private final Resources resources;
  private final PointPresentationStrategy pointPresentation;
  private final CharacterContentInitializer initializer;
  private MultipleContentView miscView;

  public CharacterPresenter(ICharacter character, CharacterView view, Resources resources,
                            IApplicationModel anathemaModel, PointPresentationStrategy pointPresentation) {
    this.character = character;
    this.characterView = view;
    this.resources = resources;
    this.anathemaModel = anathemaModel;
    this.pointPresentation = pointPresentation;
    this.initializer = new CharacterContentInitializer(anathemaModel, resources, character, view);
  }

  @Override
  public void initPresentation() {
    initOutline();
    initPhysicalTraits();
    initSpiritualTraits();
    initMagic();
    initMiscellaneous();
    pointPresentation.initPresentation(this);
  }

  private void initOutline() {
    ICharacterDescriptionView view = characterView.createCharacterDescriptionView();
    DescriptionDetails descriptionDetails = new DescriptionDetails(character.getDescription(),
            character.getCharacterConcept(),
            character.getCharacterTemplate().getTemplateType().getCharacterType().isExaltType());
    IContentPresenter descriptionPresenter = new CharacterDescriptionPresenter(descriptionDetails, resources, view);

    IConceptAndRulesViewFactory viewFactory = characterView.createConceptViewFactory();
    IContentPresenter conceptPresenter = new CharacterConceptAndRulesPresenter(character, viewFactory, resources);

    String title = getString("CardView.Outline.Title");
    initializer.initContentPresentation(title, Concept, descriptionPresenter, conceptPresenter);
  }

  private void initPhysicalTraits() {
    IGroupedFavorableTraitViewFactory viewFactory = characterView.createGroupedFavorableTraitViewFactory();
    IContentPresenter attributes = new AttributesPresenter(character, resources, viewFactory);
    IContentPresenter abilities = new AbilitiesPresenter(character, resources, viewFactory);
    String title = getString("CardView.NaturalTraits.Title");
    initializer.initContentPresentation(title, Abilities, attributes, abilities);
  }

  private void initSpiritualTraits() {
    IAdvantageViewFactory viewFactory = characterView.createAdvantageViewFactory();
    IContentPresenter presenter = new BasicAdvantagePresenter(resources, character, viewFactory);
    String title = getString("CardView.SpiritualTraits.Title");
    initializer.initContentPresentation(title, Advantages, presenter);
  }

  private void initMagic() {
    ICharacterTemplate characterTemplate = character.getCharacterTemplate();
    if (!characterTemplate.getMagicTemplate().getCharmTemplate().canLearnCharms()) {
      return;
    }
    String magicViewHeader = getString("CardView.CharmConfiguration.Title");
    MagicPresenter presenter = new MagicPresenter(character, characterView.createMagicViewFactory(), resources,
            anathemaModel);
    initializer.initContentPresentation(magicViewHeader, Magic, presenter);
  }

  private void initMiscellaneous() {
    String title = getString("CardView.MiscellaneousConfiguration.Title");
    BackgroundView factory = characterView.createBackgroundView();
    IContentPresenter backgrounds = new BackgroundPresenter(resources,
            character.getTraitConfiguration().getBackgrounds(), character.getCharacterContext(), factory,
            getGenerics(anathemaModel).getBackgroundRegistry());
    this.miscView = initializer.initContentPresentation(title, Miscellaneous, backgrounds);
  }

  @Override
  public void addMiscellaneousView(String title, ContentView tabContent) {
    tabContent.addTo(miscView);
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }
}