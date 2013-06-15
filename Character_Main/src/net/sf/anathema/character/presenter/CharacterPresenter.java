package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.presenter.magic.MagicPresenter;
import net.sf.anathema.character.view.BackgroundView;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.presenter.view.ContentView;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.generic.framework.CharacterGenericsExtractor.getGenerics;
import static net.sf.anathema.character.model.CharacterModelGroup.Magic;
import static net.sf.anathema.character.model.CharacterModelGroup.Miscellaneous;
import static net.sf.anathema.character.model.CharacterModelGroup.NaturalTraits;
import static net.sf.anathema.character.model.CharacterModelGroup.Outline;
import static net.sf.anathema.character.model.CharacterModelGroup.SpiritualTraits;

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
    String sectionTitle = getString("CardView.Outline.Title");
    SectionView sectionView = characterView.addSection(sectionTitle);

    String descriptionHeader = resources.getString("CardView.CharacterDescription.Title");
    ICharacterDescriptionView descriptionView = sectionView.addView(descriptionHeader, ICharacterDescriptionView.class, characterType());
    DescriptionDetails descriptionDetails = new DescriptionDetails(character.getDescription(),
            character.getCharacterConcept(),
            characterType().isExaltType());
    new CharacterDescriptionPresenter(descriptionDetails, resources, descriptionView).initPresentation();

    String conceptHeader = resources.getString("CardView.CharacterConcept.Title");
    ICharacterConceptAndRulesView conceptView = sectionView.addView(conceptHeader, ICharacterConceptAndRulesView.class, characterType());
    new CharacterConceptAndRulesPresenter(character, conceptView, resources).initPresentation();

    initializer.initializeAdditionalContent(sectionView, Outline);
  }

  private void initPhysicalTraits() {

    String sectionTitle = getString("CardView.NaturalTraits.Title");
    SectionView sectionView = characterView.addSection(sectionTitle);

    String attributeHeader = resources.getString("CardView.AttributeConfiguration.Title");
    IGroupedFavorableTraitConfigurationView attributeView = sectionView.addView(attributeHeader, IGroupedFavorableTraitConfigurationView.class, characterType());
    new AttributesPresenter(character, resources, attributeView).initPresentation();

    String abilityHeader = resources.getString("CardView.AbilityConfiguration.Title");
    IGroupedFavorableTraitConfigurationView abilityView = sectionView.addView(abilityHeader, IGroupedFavorableTraitConfigurationView.class, characterType());
    new AbilitiesPresenter(character, resources, abilityView).initPresentation();

    initializer.initializeAdditionalContent(sectionView, NaturalTraits);
  }

  private void initSpiritualTraits() {
    String sectionTitle = getString("CardView.SpiritualTraits.Title");
    SectionView sectionView = characterView.addSection(sectionTitle);

    String header = new BasicAdvantageViewProperties(resources).getOverallHeader();
    IBasicAdvantageView view = sectionView.addView(header, IBasicAdvantageView.class, characterType());
    new BasicAdvantagePresenter(resources, character, view).initPresentation();

    initializer.initializeAdditionalContent(sectionView, SpiritualTraits);
  }

  private void initMagic() {
    ICharacterTemplate characterTemplate = character.getCharacterTemplate();
    ICharmTemplate charmTemplate = characterTemplate.getMagicTemplate().getCharmTemplate();
    if (!charmTemplate.canLearnCharms()) {
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

  private ICharacterType characterType() {
    return character.getCharacterTemplate().getTemplateType().getCharacterType();
  }
}