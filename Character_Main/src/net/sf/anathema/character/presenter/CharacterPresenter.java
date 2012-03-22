package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.presenter.magic.MagicPresenter;
import net.sf.anathema.character.view.IAdvantageViewFactory;
import net.sf.anathema.character.view.ICharacterConceptAndRulesViewFactory;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.character.view.IGroupedFavorableTraitViewFactory;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.view.IMultiContentView;
import net.sf.anathema.framework.presenter.view.IViewContent;
import net.sf.anathema.framework.presenter.view.SimpleViewContent;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.sf.anathema.character.generic.framework.CharacterGenericsExtractor.getGenerics;

public class CharacterPresenter implements Presenter, MultiTabViewPresenter {

  private final ICharacter character;
  private final ICharacterView characterView;
  private final IAnathemaModel anathemaModel;
  private final IResources resources;
  private final PointPresentationStrategy pointPresentation;

  public CharacterPresenter(ICharacter character, ICharacterView view, IResources resources,
                            IAnathemaModel anathemaModel, PointPresentationStrategy pointPresentation) {
    this.character = character;
    this.characterView = view;
    this.resources = resources;
    this.anathemaModel = anathemaModel;
    this.pointPresentation = pointPresentation;
  }

  private ICharacterStatistics getStatistics() {
    return character.getStatistics();
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }

  private void initAbilityPresentation() {
    IGroupedFavorableTraitViewFactory viewFactory = characterView.createGroupedFavorableTraitViewFactory();
    IContentPresenter presenter = new AbilitiesPresenter(getStatistics(), resources, viewFactory);
    String title = getString("CardView.AbilityConfiguration.Title"); //$NON-NLS-1$
    initMultiTabViewPresentation(title, presenter, AdditionalModelType.Abilities);
  }

  private void initAdvantagePresentation() {
    IAdvantageViewFactory viewFactory = characterView.createAdvantageViewFactory();
    IContentPresenter presenter = new BasicAdvantagePresenter(resources, getStatistics(), viewFactory, getGenerics(anathemaModel));
    String title = getString("CardView.Advantages.Title"); //$NON-NLS-1$
    initMultiTabViewPresentation(title, presenter, AdditionalModelType.Advantages);
  }

  private void initAttributePresentation() {
    IGroupedFavorableTraitViewFactory viewFactory = characterView.createGroupedFavorableTraitViewFactory();
    IContentPresenter presenter = new AttributesPresenter(getStatistics(), resources, viewFactory);
    String title = getString("CardView.AttributeConfiguration.Title"); //$NON-NLS-1$
    initMultiTabViewPresentation(title, presenter, AdditionalModelType.Attributes);
  }

  private void initCharacterConceptPresentation() {
    ICharacterConceptAndRulesViewFactory viewFactory = characterView.createConceptViewFactory();
    IContentPresenter presenter = new CharacterConceptAndRulesPresenter(getStatistics(), viewFactory, resources);
    String title = getString("CardView.CharacterConcept.Title"); //$NON-NLS-1$
    initMultiTabViewPresentation(title, presenter, AdditionalModelType.Concept);
  }

  private void initCharacterDescriptionPresentation() {
    ICharacterDescriptionView view = characterView.createCharacterDescriptionView();
    IContentPresenter presenter = new CharacterDescriptionPresenter(resources, character.getDescription(), view,
            getStatistics().getCharacterTemplate().getTemplateType().getCharacterType().isExaltType());
    String title = getString("CardView.CharacterDescription.Title"); //$NON-NLS-1$
    initMultiTabViewPresentation(title, presenter, AdditionalModelType.Description);
  }

  private void initMagicPresentation() {
    ICharacterTemplate characterTemplate = getStatistics().getCharacterTemplate();
    if (!characterTemplate.getMagicTemplate().getCharmTemplate().canLearnCharms()) {
      return;
    }
    String magicViewHeader = getString("CardView.CharmConfiguration.Title"); //$NON-NLS-1$
    MagicPresenter presenter = new MagicPresenter(getStatistics(), characterView.createMagicViewFactory(), resources,
            anathemaModel);
    presenter.initPresentation();
    IViewContent content = presenter.getTabContent();
    IDisposable disposable = content.getDisposable();
    if (disposable != null) {
      characterView.addDisposable(disposable);
    }
    initMultiTabViewPresentation(magicViewHeader, AdditionalModelType.Magic, content);
  }

  @Override
  public void initMultiTabViewPresentation(String viewTitle, IContentPresenter presenter,
                                           AdditionalModelType additionalModelType) {
    presenter.initPresentation();
    initMultiTabViewPresentation(viewTitle, additionalModelType, presenter.getTabContent());
  }

  private void initMultiTabViewPresentation(String viewTitle, AdditionalModelType additionalsType,
                                            IViewContent... coreViews) {
    List<IViewContent> contents = new ArrayList<IViewContent>();
    Collections.addAll(contents, coreViews);
    IRegistry<String, IAdditionalViewFactory> factoryRegistry = getGenerics(anathemaModel).getAdditionalViewFactoryRegistry();
    IAdditionalModel[] additionals = getStatistics().getExtendedConfiguration().getAdditionalModels(additionalsType);
    for (IAdditionalModel model : additionals) {
      IAdditionalViewFactory viewFactory = factoryRegistry.get(model.getTemplateId());
      if (viewFactory == null) {
        continue;
      }
      String tabName = getString("AdditionalTemplateView.TabName." + model.getTemplateId()); //$NON-NLS-1$
      IView additionalView = viewFactory.createView(model, resources,
              getStatistics().getCharacterTemplate().getTemplateType().getCharacterType());
      contents.add(new SimpleViewContent(new ContentProperties(tabName), additionalView));
    }
    if (contents.size() == 0) {
      return;
    }
    IMultiContentView multiTabView = characterView.addMultiContentView(viewTitle);
    for (IViewContent content : contents) {
      content.addTo(multiTabView);
    }
  }

  @Override
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
    pointPresentation.initPresentation(this);
  }
}
