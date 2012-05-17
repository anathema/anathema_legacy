package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
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
import net.sf.anathema.framework.presenter.view.ContentView;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.presenter.view.SimpleViewContentView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.swing.IDisposable;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.sf.anathema.character.generic.framework.CharacterGenericsExtractor.getGenerics;

public class CharacterPresenter implements Presenter, MultipleContentViewPresenter {

  private final ICharacter character;
  private final ICharacterView characterView;
  private final IAnathemaModel anathemaModel;
  private final IResources resources;
  private final PointPresentationStrategy pointPresentation;

  public CharacterPresenter(ICharacter character, ICharacterView view, IResources resources, IAnathemaModel anathemaModel,
                            PointPresentationStrategy pointPresentation) {
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
    String title = getString("CardView.AbilityConfiguration.Title");
    initMultipleContentViewPresentation(title, presenter, AdditionalModelType.Abilities);
  }

  private void initAdvantagePresentation() {
    IAdvantageViewFactory viewFactory = characterView.createAdvantageViewFactory();
    IContentPresenter presenter = new BasicAdvantagePresenter(resources, getStatistics(), viewFactory, getGenerics(anathemaModel));
    String title = getString("CardView.Advantages.Title");
    initMultipleContentViewPresentation(title, presenter, AdditionalModelType.Advantages);
  }

  private void initAttributePresentation() {
    IGroupedFavorableTraitViewFactory viewFactory = characterView.createGroupedFavorableTraitViewFactory();
    IContentPresenter presenter = new AttributesPresenter(getStatistics(), resources, viewFactory);
    String title = getString("CardView.AttributeConfiguration.Title");
    initMultipleContentViewPresentation(title, presenter, AdditionalModelType.Attributes);
  }

  private void initCharacterConceptPresentation() {
    ICharacterConceptAndRulesViewFactory viewFactory = characterView.createConceptViewFactory();
    IContentPresenter presenter = new CharacterConceptAndRulesPresenter(getStatistics(), viewFactory, resources);
    String title = getString("CardView.CharacterConcept.Title");
    initMultipleContentViewPresentation(title, presenter, AdditionalModelType.Concept);
  }

  private void initCharacterDescriptionPresentation() {
    ICharacterDescriptionView view = characterView.createCharacterDescriptionView();
    IContentPresenter presenter = new CharacterDescriptionPresenter(resources, character.getDescription(), view,
            getStatistics().getCharacterTemplate().getTemplateType().getCharacterType().isExaltType());
    String title = getString("CardView.CharacterDescription.Title");
    initMultipleContentViewPresentation(title, presenter, AdditionalModelType.Description);
  }

  private void initMagicPresentation() {
    ICharacterTemplate characterTemplate = getStatistics().getCharacterTemplate();
    if (!characterTemplate.getMagicTemplate().getCharmTemplate().canLearnCharms()) {
      return;
    }
    String magicViewHeader = getString("CardView.CharmConfiguration.Title");
    MagicPresenter presenter = new MagicPresenter(getStatistics(), characterView.createMagicViewFactory(), resources, anathemaModel);
    presenter.initPresentation();
    ContentView contentView = presenter.getTabContent();
    IDisposable disposable = contentView.getDisposable();
    if (disposable != null) {
      characterView.addDisposable(disposable);
    }
    initMultipleContentViewPresentation(magicViewHeader, AdditionalModelType.Magic, contentView);
  }

  @Override
  public void initMultipleContentViewPresentation(String viewTitle, IContentPresenter presenter, AdditionalModelType additionalModelType) {
    presenter.initPresentation();
    initMultipleContentViewPresentation(viewTitle, additionalModelType, presenter.getTabContent());
  }

  private void initMultipleContentViewPresentation(String viewTitle, AdditionalModelType type, ContentView... coreViewViews) {
    MultipleContentView multipleContentView = characterView.addMultipleContentView(viewTitle);
    List<ContentView> contentViews = new ArrayList<ContentView>();
    Collections.addAll(contentViews, coreViewViews);
    IRegistry<String, IAdditionalViewFactory> factoryRegistry = getGenerics(anathemaModel).getAdditionalViewFactoryRegistry();
    for (IAdditionalModel model : getStatistics().getExtendedConfiguration().getAdditionalModels(type)) {
      IAdditionalViewFactory viewFactory = factoryRegistry.get(model.getTemplateId());
      if (viewFactory == null) {
        continue;
      }
      String viewName = getString("AdditionalTemplateView.TabName." + model.getTemplateId());
      ICharacterType characterType = getStatistics().getCharacterTemplate().getTemplateType().getCharacterType();
      IView additionalView = viewFactory.createView(model, resources, characterType);
      contentViews.add(new SimpleViewContentView(new ContentProperties(viewName), additionalView));
    }
    if (contentViews.size() == 0) {
      return;
    }
    for (ContentView contentView : contentViews) {
      contentView.addTo(multipleContentView);
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
    initMultipleContentViewPresentation(getString("CardView.MiscellaneousConfiguration.Title"), AdditionalModelType.Miscellaneous);
    pointPresentation.initPresentation(this);
  }
}
