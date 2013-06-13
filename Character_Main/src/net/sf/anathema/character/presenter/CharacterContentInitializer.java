package net.sf.anathema.character.presenter;

import com.google.common.base.Function;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalViewFactory;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.presenter.view.ContentView;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.presenter.view.SimpleViewContentView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.sf.anathema.character.generic.framework.CharacterGenericsExtractor.getGenerics;
import static net.sf.anathema.lib.lang.ArrayUtilities.transform;

public class CharacterContentInitializer {


  private final IApplicationModel anathemaModel;
  private final Resources resources;
  private final ICharacter character;
  private final CharacterView characterView;

  public CharacterContentInitializer(IApplicationModel anathemaModel, Resources resources, ICharacter character,
                                     CharacterView characterView) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
    this.character = character;
    this.characterView = characterView;
  }

  public MultipleContentView initContentPresentation(String viewTitle, CharacterModelGroup type,
                                                     IContentPresenter... corePresenters) {
    for (IContentPresenter presenter : corePresenters) {
      presenter.initPresentation();
    }
    ContentView[] coreViews = transform(corePresenters, ContentView.class,
            new Function<IContentPresenter, ContentView>() {
              @Override
              public ContentView apply(IContentPresenter input) {
                return input.getTabContent();
              }
            });
    return addMultipleContentViewGroup(viewTitle, type, coreViews);
  }

  public MultipleContentView addMultipleContentViewGroup(String viewTitle, CharacterModelGroup type,
                                                         ContentView... coreViewViews) {
    List<ContentView> contentViews = collectAllContentViews(type, coreViewViews);
    return createMultipleContentViewWithViews(viewTitle, contentViews);
  }

  private MultipleContentView createMultipleContentViewWithViews(String viewTitle, List<ContentView> contentViews) {
    MultipleContentView multipleContentView = characterView.addMultipleContentView(viewTitle);
    for (ContentView contentView : contentViews) {
      contentView.addTo(multipleContentView);
    }
    return multipleContentView;
  }

  private List<ContentView> collectAllContentViews(CharacterModelGroup type, ContentView[] coreViewViews) {
    List<ContentView> contentViews = new ArrayList<>();
    Collections.addAll(contentViews, coreViewViews);
    contentViews.addAll(additionalViews(type));
    return contentViews;
  }

  private List<ContentView> additionalViews(CharacterModelGroup type) {
    IRegistry<String, IAdditionalViewFactory> factoryRegistry = getGenerics(
            anathemaModel).getAdditionalViewFactoryRegistry();
    List<ContentView> additionalViews = new ArrayList<>();
    for (IAdditionalModel model : character.getExtendedConfiguration().getAdditionalModels(type)) {
      IAdditionalViewFactory viewFactory = factoryRegistry.get(model.getTemplateId());
      if (viewFactory == null) {
        continue;
      }
      String viewName = getString("AdditionalTemplateView.TabName." + model.getTemplateId());
      ICharacterType characterType = character.getCharacterTemplate().getTemplateType().getCharacterType();
      IView additionalView = viewFactory.createView(model, resources, characterType);
      SimpleViewContentView simpleView = new SimpleViewContentView(new ContentProperties(viewName), additionalView);
      additionalViews.add(simpleView);
    }
    return additionalViews;
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }
}