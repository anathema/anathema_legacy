package net.sf.anathema.character.presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.framework.presenter.view.ITabContent;
import net.sf.anathema.framework.presenter.view.SimpleViewTabContent;
import net.sf.anathema.framework.presenter.view.ViewTabContent;
import net.sf.anathema.framework.view.util.TabProperties;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

public class CharacterPresenter implements IPresenter {

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

  private ICharacterStatistics getStatistics() {
    return character.getStatistics();
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }

  private void initAbilityPresentation() {
    String basicAbilitiesHeader = getString("CardView.AbilityConfiguration.Title"); //$NON-NLS-1$
    IIdentifiedTraitTypeGroup[] traitTypeGroups = getStatistics().getTraitConfiguration().getAbilityTypeGroups();
    int groupCount = traitTypeGroups.length;
    int columnCount = groupCount / 2 + 1;
    IGroupedFavorableTraitConfigurationView abilityView = characterView.createGroupedFavorableTraitConfigurationView(columnCount);
    ITabContent basicAbilitiesTab = new FavorableTraitConfigurationPresenter(
        traitTypeGroups,
        getStatistics(),
        abilityView,
        resources).init("AbilityConfiguration", "AbilityGroup"); //$NON-NLS-1$//$NON-NLS-2$
    initMultiTabViewPresentation(basicAbilitiesHeader, AdditionalModelType.Abilities, basicAbilitiesTab);
  }

  private void initAdvantagePresentation() {
    String basicAdvantageHeader = getString("CardView.Advantages.Title"); //$NON-NLS-1$
    ITabContent basicAdvantageTab = new BasicAdvantagePresenter(
        resources,
        getStatistics(),
        characterView.createAdvantageViewFactory(),
        generics).init();
    initMultiTabViewPresentation(basicAdvantageHeader, AdditionalModelType.Advantages, basicAdvantageTab);
  }

  private void initAttributePresentation() {
    String title = getString("CardView.AttributeConfiguration.Title"); //$NON-NLS-1$
    IGroupedFavorableTraitConfigurationView attributeView = characterView.createGroupedFavorableTraitConfigurationView(1);
    IIdentifiedTraitTypeGroup[] attributeTypeGroups = getStatistics().getTraitConfiguration().getAttributeTypeGroups();
    ITabContent basicAbilitiesTab = new FavorableTraitConfigurationPresenter(
        attributeTypeGroups,
        getStatistics(),
        attributeView,
        resources).init("AttributeConfiguration", "AttributeGroupType.Name"); //$NON-NLS-1$//$NON-NLS-2$
    initMultiTabViewPresentation(title, AdditionalModelType.Attributes, basicAbilitiesTab);
  }

  private void initCharacterConceptPresentation() {
    String viewTitle = getString("CardView.CharacterConcept.Title"); //$NON-NLS-1$
    ICharacterConceptAndRulesViewFactory viewFactory = characterView.createConceptViewFactory();
    ITabContent conceptView = new CharacterConceptAndRulesPresenter(getStatistics(), viewFactory, resources).init();
    initMultiTabViewPresentation(viewTitle, AdditionalModelType.Concept, conceptView);
  }

  private void initCharacterDescriptionPresentation() {
    ICharacterDescriptionView descriptionView = characterView.createCharacterDescriptionView();
    String title = resources.getString("CardView.CharacterDescription.Title");//$NON-NLS-1$
    IPresenter presenter = new CharacterDescriptionPresenter(resources, character.getDescription(), descriptionView);
    initMultiTabViewPresentation(
        descriptionView,
        new TabProperties(title).needsScrollbar(),
        presenter,
        AdditionalModelType.Description);
  }

  private void initExperiencePointPresentation(boolean experienced) {
    if (experienced) {
      IExperienceConfigurationView experienceView = characterView.createExperienceConfigurationView();
      IPresenter presenter = new ExperienceConfigurationPresenter(
          resources,
          getStatistics().getExperiencePoints(),
          experienceView);
      String title = getString("CardView.ExperienceConfiguration.Title");//$NON-NLS-1$
      initMultiTabViewPresentation(experienceView, presenter, title, AdditionalModelType.Experience);
    }
  }

  private void initMagicPresentation() {
    final ICharacterTemplate characterTemplate = getStatistics().getCharacterTemplate();
    if (!characterTemplate.getMagicTemplate().getCharmTemplate().knowsCharms(getStatistics().getRules())) {
      return;
    }
    String magicViewHeader = getString("CardView.CharmConfiguration.Title"); //$NON-NLS-1$
    ITabContent[] basicMagicViews = new MagicPresenter(
        getStatistics(),
        characterView.createMagicViewFactory(),
        resources,
        generics.getTemplateRegistry(),
        generics.getCharmProvider()).init();
    for (ITabContent magicTab : basicMagicViews) {
      IDisposable disposable = magicTab.getDisposable();
      if (disposable != null) {
        characterView.addDisposable(disposable);
      }
    }
    initMultiTabViewPresentation(magicViewHeader, AdditionalModelType.Magic, basicMagicViews);
  }

  private void initMultiTabViewPresentation(
      ISimpleTabView view,
      IPresenter presenter,
      String title,
      AdditionalModelType modelType) {
    presenter.initPresentation();
    initMultiTabViewPresentation(title, modelType, new SimpleViewTabContent(title, view));
  }

  private void initMultiTabViewPresentation(
      IView view,
      TabProperties tabProperties,
      IPresenter presenter,
      AdditionalModelType modelType) {
    presenter.initPresentation();
    initMultiTabViewPresentation(tabProperties.getName(), modelType, new ViewTabContent(view, tabProperties));
  }

  private void initMultiTabViewPresentation(
      String viewTitle,
      AdditionalModelType additionalsType,
      ITabContent... coreViews) {
    List<ITabContent> contents = new ArrayList<ITabContent>();
    Collections.addAll(contents, coreViews);
    IRegistry<String, IAdditionalViewFactory> factoryRegistry = generics.getAdditionalViewFactoryRegistry();
    IAdditionalModel[] additionals = getStatistics().getExtendedConfiguration().getAdditionalModels(additionalsType);
    for (IAdditionalModel model : additionals) {
      IAdditionalViewFactory viewFactory = factoryRegistry.get(model.getTemplateId());
      if (viewFactory == null) {
        continue;
      }
      String tabName = getString("AdditionalTemplateView.TabName." + model.getTemplateId()); //$NON-NLS-1$
      ISimpleTabView additionalView = viewFactory.createView(model, resources, getStatistics().getCharacterTemplate()
          .getTemplateType()
          .getCharacterType());
      contents.add(new SimpleViewTabContent(tabName, additionalView));
    }
    if (contents.size() == 0) {
      return;
    }
    IMultiTabView multiTabView = characterView.addMultiTabView(viewTitle);
    for (ITabContent content : contents) {
      content.addTo(multiTabView);
    }
    multiTabView.initGui(null);
  }

  private void initOverviewPresentation() {
    IOverviewView creationPointView = characterView.addCreationOverviewView();
    new CreationOverviewPresenter(resources, getStatistics(), creationPointView, bonusPointManagement).initPresentation();
    IOverviewView experiencePointView = characterView.addExperienceOverviewView();
    new ExperiencedOverviewPresenter(resources, getStatistics(), experiencePointView, experiencePointManagement).initPresentation();
    setOverviewView(getStatistics().isExperienced());
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
    initMultiTabViewPresentation(getString("CardView.MiscellaneousConfiguration.Title"), //$NON-NLS-1$
        AdditionalModelType.Miscellaneous);
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

  private void setOverviewView(boolean experienced) {
    characterView.toogleOverviewView(experienced);
  }
}