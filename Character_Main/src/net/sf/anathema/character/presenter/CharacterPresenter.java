package net.sf.anathema.character.presenter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.presenter.advance.ExperienceConfigurationPresenter;
import net.sf.anathema.character.presenter.charm.MagicPresenter;
import net.sf.anathema.character.presenter.overview.CreationOverviewPresenter;
import net.sf.anathema.character.presenter.overview.ExperiencedOverviewPresenter;
import net.sf.anathema.character.view.ICharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.character.view.IGroupedFavorableTraitConfigurationView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.framework.presenter.view.IMultiTabView;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class CharacterPresenter implements IPresenter{

  private final ICharacter character;
  private final ICharacterView characterView;
  private final ICharacterGenerics generics;
  private final IResources resources;
  private final IBonusPointManagement bonusPointManagement;
  private final IExperiencePointManagement experiencePointManagement;

  public CharacterPresenter(
      ICharacter character,
      ICharacterView view,
      IResources resources,
      ICharacterGenerics generics,
      IBonusPointManagement bonusPointManagement,
      IExperiencePointManagement experiencePointManagement) {
    this.character = character;
    this.characterView = view;
    this.resources = resources;
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
          @Override
          public void experiencedChanged(boolean experienced) {
            initExperiencePointPresentation(experienced);
            setOverviewView(experienced);
          }
        });
  }

  private void initOverviewPresentation() {
    IOverviewView creationPointView = characterView.addCreationOverviewView();
    new CreationOverviewPresenter(resources, getStatistics(), creationPointView, bonusPointManagement).init();
    IOverviewView experiencePointView = characterView.addExperienceOverviewView();
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
    initMultiTabViewPresentation(miscellaneousTitle, AdditionalModelType.Miscellaneous);
  }

  private void initMultiTabViewPresentation(
      String viewTitle,
      AdditionalModelType additionalModelType,
      TabContent... coreViews) {
    IRegistry<String, IAdditionalViewFactory> factoryRegistry = generics.getAdditionalViewFactoryRegistry();
    IAdditionalModel[] additionalModels = getStatistics().getExtendedConfiguration().getAdditionalModels(
        additionalModelType);
    Map<IAdditionalModel, IAdditionalViewFactory> additionalViewFactories = new LinkedHashMap<IAdditionalModel, IAdditionalViewFactory>();
    for (IAdditionalModel model : additionalModels) {
      IAdditionalViewFactory viewFactory = factoryRegistry.get(model.getTemplateId());
      if (viewFactory != null) {
        additionalViewFactories.put(model, viewFactory);
      }
    }
    if (coreViews.length <= 0 && additionalViewFactories.size() <= 0) {
      return;
    }
    IMultiTabView multiTabView = characterView.addMultiTabView(viewTitle);
    for (TabContent coreView : coreViews) {
      coreView.addTo(multiTabView);
    }
    for (Entry<IAdditionalModel, IAdditionalViewFactory> entry : additionalViewFactories.entrySet()) {
      IAdditionalModel model = entry.getKey();
      String tabName = getString("AdditionalTemplateView.TabName." + model.getTemplateId()); //$NON-NLS-1$
      multiTabView.addTabView(entry.getValue().createView(
          model,
          resources,
          getStatistics().getCharacterTemplate().getTemplateType().getCharacterType()), tabName);
    }
    multiTabView.initGui(null);
  }

  private void initMagicPresentation() {
    final ICharacterTemplate characterTemplate = getStatistics().getCharacterTemplate();
    if (!characterTemplate.getMagicTemplate().getCharmTemplate().knowsCharms(getStatistics().getRules())) {
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
    initMultiTabViewPresentation(magicViewHeader, AdditionalModelType.Magic, basicMagicViews);
  }

  private ICharacterStatistics getStatistics() {
    return character.getStatistics();
  }

  private void initCharacterConceptPresentation() {
    String viewTitle = getString("CardView.CharacterConcept.Title"); //$NON-NLS-1$
    ICharacterConceptAndRulesViewFactory viewFactory = characterView.createConceptViewFactory();
    TabContent conceptView = new CharacterConceptAndRulesPresenter(getStatistics(), viewFactory, resources).init();
    initMultiTabViewPresentation(viewTitle, AdditionalModelType.Concept, conceptView);
  }

  private void initAttributePresentation() {
    String title = getString("CardView.AttributeConfiguration.Title"); //$NON-NLS-1$
    IGroupedFavorableTraitConfigurationView attributeView = characterView.addGroupedFavorableTraitConfigurationTab(
        title,
        1);
    IIdentifiedTraitTypeGroup[] attributeTypeGroups = getStatistics().getTraitConfiguration().getAttributeTypeGroups();
    new FavorableTraitConfigurationPresenter(attributeTypeGroups, getStatistics(), attributeView, resources).init(
        "AttributeConfiguration", //$NON-NLS-1$
        "AttributeGroupType.Name", //$NON-NLS-1$
        false);
  }

  private void initAdvantagePresentation() {
    String basicAdvantageHeader = getString("CardView.Advantages.Title"); //$NON-NLS-1$
    TabContent basicAdvantageTab = new BasicAdvantagePresenter(
        resources,
        getStatistics(),
        characterView.createAdvantageViewFactory(),
        generics).init();
    initMultiTabViewPresentation(basicAdvantageHeader, AdditionalModelType.Advantages, basicAdvantageTab);
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }

  private void initAbilityPresentation() {
    String basicAbilitiesHeader = getString("CardView.AbilityConfiguration.Title"); //$NON-NLS-1$
    IIdentifiedTraitTypeGroup[] traitTypeGroups = getStatistics().getTraitConfiguration().getAbilityTypeGroups();
    int groupCount = traitTypeGroups.length;
    int columnCount = groupCount / 2 + 1;
    IGroupedFavorableTraitConfigurationView abilityView = characterView.addGroupedFavorableTraitConfigurationView(
        getString("CardView.AbilityConfiguration.Title"), columnCount); //$NON-NLS-1$
    TabContent basicAbilitiesTab = new FavorableTraitConfigurationPresenter(
        traitTypeGroups,
        getStatistics(),
        abilityView,
        resources).init("AbilityConfiguration", "AbilityGroup", //$NON-NLS-1$ //$NON-NLS-2$
        true);
    initMultiTabViewPresentation(basicAbilitiesHeader, AdditionalModelType.Abilities, basicAbilitiesTab);
  }

  private void initCharacterDescriptionPresentation() {
    ICharacterDescriptionView descriptionView = characterView.addCharacterDescriptionView(resources.getString("CardView.CharacterDescription.Title")); //$NON-NLS-1$
    new CharacterDescriptionPresenter(resources, character.getDescription(), descriptionView).init();
  }
}