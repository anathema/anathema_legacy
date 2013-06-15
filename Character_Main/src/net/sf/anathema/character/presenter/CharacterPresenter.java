package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.MagicPresenter;
import net.sf.anathema.character.view.BackgroundView;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.character.view.IBasicAdvantageView;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.character.view.concept.ICharacterConceptAndRulesView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.generic.framework.CharacterGenericsExtractor.getGenerics;
import static net.sf.anathema.character.model.CharacterModelGroup.Magic;
import static net.sf.anathema.character.model.CharacterModelGroup.Miscellaneous;
import static net.sf.anathema.character.model.CharacterModelGroup.NaturalTraits;
import static net.sf.anathema.character.model.CharacterModelGroup.Outline;
import static net.sf.anathema.character.model.CharacterModelGroup.SpiritualTraits;

public class CharacterPresenter implements Presenter {

  private final ICharacter character;
  private final CharacterView characterView;
  private final IApplicationModel anathemaModel;
  private final Resources resources;
  private final PointPresentationStrategy pointPresentation;

  public CharacterPresenter(ICharacter character, CharacterView view, Resources resources,
                            IApplicationModel anathemaModel, PointPresentationStrategy pointPresentation) {
    this.character = character;
    this.characterView = view;
    this.resources = resources;
    this.anathemaModel = anathemaModel;
    this.pointPresentation = pointPresentation;
  }

  @Override
  public void initPresentation() {
    initOutline();
    initPhysicalTraits();
    initSpiritualTraits();
    initMagic();
    initMiscellaneous();
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

    initialize(sectionView, Outline);
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

    initialize(sectionView, NaturalTraits);
  }

  private void initSpiritualTraits() {
    String sectionTitle = getString("CardView.SpiritualTraits.Title");
    SectionView sectionView = characterView.addSection(sectionTitle);

    String header = new BasicAdvantageViewProperties(resources).getOverallHeader();
    IBasicAdvantageView view = sectionView.addView(header, IBasicAdvantageView.class, characterType());
    new BasicAdvantagePresenter(resources, character, view).initPresentation();

    initialize(sectionView, SpiritualTraits);
  }

  private void initMagic() {
    ICharacterTemplate characterTemplate = character.getCharacterTemplate();
    ICharmTemplate charmTemplate = characterTemplate.getMagicTemplate().getCharmTemplate();
    if (!charmTemplate.canLearnCharms()) {
      return;
    }

    String sectionTitle = getString("CardView.CharmConfiguration.Title");
    SectionView sectionView = characterView.addSection(sectionTitle);
    new MagicPresenter(character, sectionView, resources, anathemaModel).initPresentation();

    initialize(sectionView, Magic);
  }

  private void initMiscellaneous() {
    String sectionTitle = getString("CardView.MiscellaneousConfiguration.Title");
    SectionView sectionView = characterView.addSection(sectionTitle);

    String backgroundHeader = resources.getString("CardView.BackgroundConfiguration.Title");
    BackgroundView view = sectionView.addView(backgroundHeader, BackgroundView.class, characterType());
    new BackgroundPresenter(resources,
            character.getTraitConfiguration().getBackgrounds(), character.getCharacterContext(), view,
            getGenerics(anathemaModel).getBackgroundRegistry()).initPresentation();

    initialize(sectionView, Miscellaneous);

    pointPresentation.initPresentation(sectionView);
  }

  public void initialize(SectionView sectionView, CharacterModelGroup group) {
    IRegistry<String, IAdditionalInitializer> factoryRegistry = getGenerics(
            anathemaModel).getAdditionalInitializerRegistry();
    for (IAdditionalModel model : character.getExtendedConfiguration().getAdditionalModels(group)) {
      IAdditionalInitializer initializer = factoryRegistry.get(model.getTemplateId());
      if (initializer == null) {
        continue;
      }
      String viewName = getString("AdditionalTemplateView.TabName." + model.getTemplateId());
      ICharacterType characterType = character.getCharacterTemplate().getTemplateType().getCharacterType();
      Object view = sectionView.addView(viewName, initializer.getViewClass(), characterType);
      initializer.initialize(model, resources, characterType, view);
    }
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }

  private ICharacterType characterType() {
    return character.getCharacterTemplate().getTemplateType().getCharacterType();
  }
}