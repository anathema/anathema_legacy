package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.model.nature.INatureProvider;
import net.sf.anathema.character.presenter.advance.ExperienceConfigurationPresenter;
import net.sf.anathema.character.presenter.charm.MagicPresenter;
import net.sf.anathema.character.presenter.overview.CreationOverviewPresenter;
import net.sf.anathema.character.presenter.overview.ExperiencedOverviewPresenter;
import net.sf.anathema.character.view.ICharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.overview.ICreationOverviewView;
import net.sf.anathema.character.view.overview.IExperienceOverviewView;
import net.sf.anathema.framework.presenter.view.IMultiTabView;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class CharacterPresenter {

  private final ICharacter character;
  private final ICharacterView characterView;
  private final INatureProvider natureProvider;
  private final ICharacterGenerics generics;
  private final IResources resources;
  private final IBonusPointManagement bonusPointManagement;
  private final IExperiencePointManagement experiencePointManagement;

  public CharacterPresenter(
      ICharacter character,
      ICharacterView view,
      IResources resources,
      INatureProvider natureProvider,
      ICharacterGenerics generics,
      IBonusPointManagement bonusPointManagement,
      IExperiencePointManagement experiencePointManagement) {
    this.character = character;
    this.characterView = view;
    this.resources = resources;
    this.natureProvider = natureProvider;
    this.generics = generics;
    this.bonusPointManagement = bonusPointManagement;
    this.experiencePointManagement = experiencePointManagement;
  }

  public void initPresentation() {
    initCharacterDescriptionPresentation();
    initStatisticsPresentation();
  }

  private void initStatisticsPresentation() {
    if (!character.hasStatistics()) {
      return;
    }
    initCharacterConceptPresentation();
    initAttributePresentation();
    initAbilityPresentation();
    initAdvantagePresentation();
    initMagicPresentation();
    initMiscellaneousPresentation();
    initOverviewPresentation();
    initExperiencePointPresentation(getStatistics().isExperienced());
    getStatistics().getCharacterContext().getCharacterListening().addChangeListener(
        new DedicatedCharacterChangeAdapter() {
          public void experiencedChanged(boolean experienced) {
            initExperiencePointPresentation(experienced);
            setOverviewView(experienced);
          }
        });
  }

  private void initOverviewPresentation() {
    ICreationOverviewView creationPointView = characterView.addCreationOverviewView();
    new CreationOverviewPresenter(resources, getStatistics(), creationPointView, bonusPointManagement).init();
    IExperienceOverviewView experiencePointView = characterView.addExperienceOverviewView();
    new ExperiencedOverviewPresenter(resources, getStatistics(), experiencePointView, experiencePointManagement).init();
    setOverviewView(getStatistics().isExperienced());
  }

  private void setOverviewView(boolean experienced) {
    characterView.toogleOverviewView(experienced);
  }

  private void initExperiencePointPresentation(boolean experienced) {
    if (experienced) {
      IExperienceConfigurationView experienceView = characterView.addExperienceConfigurationView(getString("CardView.ExperienceConfiguration.Title")); //$NON-NLS-1$
      new ExperienceConfigurationPresenter(resources, getStatistics().getExperiencePoints(), experienceView).initPresentation();
    }
  }

  private void initMiscellaneousPresentation() {
    String miscellaneousTitle = getString("CardView.MiscellaneousConfiguration.Title"); //$NON-NLS-1$
    initMultiTabViewPresentation(miscellaneousTitle, new TabContent[0], AdditionalModelType.Miscellaneous);
  }

  private void initMultiTabViewPresentation(
      String viewTitle,
      TabContent[] coreViews,
      AdditionalModelType additionalModelType) {
    IAdditionalModel[] additionalModels = getStatistics().getExtendedConfiguration().getAdditionalModels(
        additionalModelType);
    if (coreViews.length <= 0 && additionalModels.length <= 0) {
      return;
    }
    IMultiTabView multiTabView = characterView.addMultiTabView(viewTitle);
    for (TabContent coreView : coreViews) {
      coreView.addTo(multiTabView);
    }
    IRegistry<String, IAdditionalViewFactory> factoryRegistry = generics.getAdditionalViewFactoryRegistry();
    for (IAdditionalModel model : additionalModels) {
      IAdditionalViewFactory viewFactory = factoryRegistry.get(model.getTemplateId());
      String tabName = getString("AdditionalTemplateView.TabName." + model.getTemplateId()); //$NON-NLS-1$
      multiTabView.addTabView(viewFactory.createView(model, resources, getStatistics().getCharacterTemplate()
          .getTemplateType()
          .getCharacterType()), tabName);
    }
    multiTabView.initGui(null);
  }

  private void initMagicPresentation() {
    if (!getStatistics().getCharacterTemplate().getMagicTemplate().getCharmTemplate().knowsCharms()) {
      return;
    }
    String magicViewHeader = getString("CardView.CharmConfiguration.Title"); //$NON-NLS-1$
    TabContent[] basicMagicViews = new MagicPresenter(
        getStatistics(),
        characterView.createMagicViewFactory(),
        resources,
        generics.getTemplateRegistry(),
        generics.getCharmProvider()).init();
    for (TabContent magicTab : basicMagicViews) {
      IDisposable disposable = magicTab.getDisposable();
      if (disposable != null) {
        characterView.addDisposable(disposable);
      }
    }
    initMultiTabViewPresentation(magicViewHeader, basicMagicViews, AdditionalModelType.Magic);
  }

  private ICharacterStatistics getStatistics() {
    return character.getStatistics();
  }

  private void initCharacterConceptPresentation() {
    String viewTitle = getString("CardView.CharacterConcept.Title"); //$NON-NLS-1$
    ICharacterConceptAndRulesViewFactory characterConceptView = characterView.createConceptViewFactory();
    TabContent[] conceptViews = new CharacterConceptAndRulesPresenter(
        getStatistics(),
        characterConceptView,
        resources,
        natureProvider).init();
    initMultiTabViewPresentation(viewTitle, conceptViews, AdditionalModelType.Concept);
  }

  public void initAttributePresentation() {
    String title = getString("CardView.AttributeConfiguration.Title"); //$NON-NLS-1$
    IGroupedFavorableTraitConfigurationView attributeView = characterView.addGroupedFavorableTraitConfigurationView(
        title,
        1);
    IIdentifiedTraitTypeGroup[] attributeTypeGroups = getStatistics().getTraitConfiguration().getAttributeTypeGroups();
    new FavorableTraitConfigurationPresenter(attributeTypeGroups, getStatistics(), attributeView, resources).init(
        "AttributeGroupType.Name", //$NON-NLS-1$
        false);
  }

  private void initAdvantagePresentation() {
    String basicAdvantageHeader = getString("CardView.Advantages.Title"); //$NON-NLS-1$
    TabContent[] basicAdvantageTabs = new BasicAdvantagePresenter(
        resources,
        getStatistics(),
        characterView.createAdvantageViewFactory(),
        generics).init();
    initMultiTabViewPresentation(basicAdvantageHeader, basicAdvantageTabs, AdditionalModelType.Advantages);
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }

  public void initAbilityPresentation() {
    IIdentifiedTraitTypeGroup[] traitTypeGroups = getStatistics().getTraitConfiguration().getAbilityTypeGroups();
    int groupCount = traitTypeGroups.length;
    int columnCount = groupCount / 2 + 1;
    IGroupedFavorableTraitConfigurationView abilityView = characterView.addGroupedFavorableTraitConfigurationView(
        getString("CardView.AbilityConfiguration.Title"), columnCount); //$NON-NLS-1$
    new FavorableTraitConfigurationPresenter(traitTypeGroups, getStatistics(), abilityView, resources).init(
        "AbilityGroup", //$NON-NLS-1$
        true);
  }

  private void initCharacterDescriptionPresentation() {
    ICharacterDescriptionView descriptionView = characterView.addCharacterDescriptionView(resources.getString("CardView.CharacterDescription.Title")); //$NON-NLS-1$
    new CharacterDescriptionPresenter(resources, character.getDescription(), descriptionView).init();
  }
}