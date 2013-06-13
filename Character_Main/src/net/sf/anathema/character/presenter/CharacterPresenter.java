package net.sf.anathema.character.presenter;

import com.google.common.base.Function;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
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
import net.sf.anathema.framework.presenter.view.SimpleViewContentView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.sf.anathema.character.model.CharacterModelGroup.SpiritualTraits;
import static net.sf.anathema.character.model.CharacterModelGroup.Outline;
import static net.sf.anathema.character.model.CharacterModelGroup.Miscellaneous;
import static net.sf.anathema.character.generic.framework.CharacterGenericsExtractor.getGenerics;
import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public class CharacterPresenter implements Presenter, MultipleContentViewPresenter {

  private final ICharacter character;
  private final CharacterView characterView;
  private final IApplicationModel anathemaModel;
  private final Resources resources;
  private final PointPresentationStrategy pointPresentation;
  private MultipleContentView miscView;

  public CharacterPresenter(ICharacter character, CharacterView view, Resources resources, IApplicationModel anathemaModel,
                            PointPresentationStrategy pointPresentation) {
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
    pointPresentation.initPresentation(this);
  }

  private void initOutline() {
    IContentPresenter descriptionPresenter = createDescriptionPresenter();
    IContentPresenter conceptPresenter = createConceptPresenter();
    String title = getString("CardView.Outline.Title");
    initMultipleContentPresentation(title, Outline, descriptionPresenter, conceptPresenter);
  }

  private void initPhysicalTraits() {
    IGroupedFavorableTraitViewFactory viewFactory = characterView.createGroupedFavorableTraitViewFactory();
    IContentPresenter attributes = new AttributesPresenter(character, resources, viewFactory);
    IContentPresenter abilities = new AbilitiesPresenter(character, resources, viewFactory);
    String title = getString("CardView.NaturalTraits.Title");
    initMultipleContentPresentation(title, CharacterModelGroup.NaturalTraits, attributes, abilities);
  }

  private void initSpiritualTraits() {
    IAdvantageViewFactory viewFactory = characterView.createAdvantageViewFactory();
    IContentPresenter presenter = new BasicAdvantagePresenter(resources, character, viewFactory);
    String title = getString("CardView.SpiritualTraits.Title");
    initMultipleContentPresentation(title, SpiritualTraits, presenter);
  }

  private void initMagic() {
    ICharacterTemplate characterTemplate = character.getCharacterTemplate();
    if (!characterTemplate.getMagicTemplate().getCharmTemplate().canLearnCharms()) {
      return;
    }
    String magicViewHeader = getString("CardView.CharmConfiguration.Title");
    MagicPresenter presenter = new MagicPresenter(character, characterView.createMagicViewFactory(), resources, anathemaModel);
    presenter.initPresentation();
    ContentView contentView = presenter.getTabContent();
    addMultipleContentViewGroup(magicViewHeader, CharacterModelGroup.Magic, contentView);
  }

  private void initMiscellaneous() {
    String title = getString("CardView.MiscellaneousConfiguration.Title");
    BackgroundView factory = characterView.createBackgroundView();
    IContentPresenter backgrounds =
            new BackgroundPresenter(resources, character.getTraitConfiguration().getBackgrounds(), character.getCharacterContext(), factory,
                    getGenerics(anathemaModel).getBackgroundRegistry());
    this.miscView = initMultipleContentPresentation(title, Miscellaneous, backgrounds);
  }

  private IContentPresenter createConceptPresenter() {
    IConceptAndRulesViewFactory viewFactory = characterView.createConceptViewFactory();
    return new CharacterConceptAndRulesPresenter(character, viewFactory, resources);
  }

  private IContentPresenter createDescriptionPresenter() {
    ICharacterDescriptionView view = characterView.createCharacterDescriptionView();
    return new CharacterDescriptionPresenter(resources, character.getDescription(), character.getCharacterConcept(), view,
            character.getCharacterTemplate().getTemplateType().getCharacterType().isExaltType());
  }

  private MultipleContentView initMultipleContentPresentation(String viewTitle, CharacterModelGroup type, IContentPresenter... corePresenters) {
    for (IContentPresenter presenter : corePresenters) {
      presenter.initPresentation();
    }
    ContentView[] coreViews = transform(corePresenters, ContentView.class, new Function<IContentPresenter, ContentView>() {
      @Override
      public ContentView apply(IContentPresenter input) {
        return input.getTabContent();
      }
    });
    return addMultipleContentViewGroup(viewTitle, type, coreViews);
  }

  @Override
  public void addMiscellaneousView(String title, ContentView tabContent) {
    tabContent.addTo(miscView);
  }

  private MultipleContentView addMultipleContentViewGroup(String viewTitle, CharacterModelGroup type, ContentView... coreViewViews) {
    MultipleContentView multipleContentView = characterView.addMultipleContentView(viewTitle);
    List<ContentView> contentViews = new ArrayList<>();
    Collections.addAll(contentViews, coreViewViews);
    IRegistry<String, IAdditionalViewFactory> factoryRegistry = getGenerics(anathemaModel).getAdditionalViewFactoryRegistry();
    for (IAdditionalModel model : character.getExtendedConfiguration().getAdditionalModels(type)) {
      IAdditionalViewFactory viewFactory = factoryRegistry.get(model.getTemplateId());
      if (viewFactory == null) {
        continue;
      }
      String viewName = getString("AdditionalTemplateView.TabName." + model.getTemplateId());
      ICharacterType characterType = character.getCharacterTemplate().getTemplateType().getCharacterType();
      IView additionalView = viewFactory.createView(model, resources, characterType);
      contentViews.add(new SimpleViewContentView(new ContentProperties(viewName), additionalView));
    }
    if (contentViews.size() == 0) {
      return multipleContentView;
    }
    for (ContentView contentView : contentViews) {
      contentView.addTo(multipleContentView);
    }
    return multipleContentView;
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }
}