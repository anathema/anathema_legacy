package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.impl.module.RegExCharacterPrintNameFileScanner;
import net.sf.anathema.character.perspective.CharacterGridPresenter;
import net.sf.anathema.character.perspective.CharacterStackBridge;
import net.sf.anathema.character.perspective.CharacterStackPresenter;
import net.sf.anathema.character.perspective.model.model.ItemSelectionModel;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.resources.IStringResourceHandler;
import net.sf.anathema.swing.character.perspective.CharacterStackSwingBridge;
import net.sf.anathema.swing.character.perspective.CharacterSystemView;
import net.sf.anathema.swing.character.perspective.InteractionView;

@PerspectiveAutoCollector
@Weight(weight = 1)
public class CharacterSystemPerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon("King-icon.png");
    toggle.setTooltip("Character");
  }

  @Override
  public void initContent(Container container, IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    CharacterSystemModel systemModel = new CharacterSystemModel(model);
    CharacterSystemView view = new CharacterSystemView(resources);
    initPresentation(model, systemModel, view, resources);
    initInteractionPresentation(systemModel, view.getInteractionView(), resources);
    container.setSwingContent(view.getComponent());
  }

  private void initPresentation(IAnathemaModel model, CharacterSystemModel systemModel, CharacterSystemView view, IStringResourceHandler resources) {
    CharacterStackBridge bridge = new CharacterStackSwingBridge(model, view.getStackView());
    CharacterStackPresenter stackPresenter = new CharacterStackPresenter(bridge, systemModel);
    RegExCharacterPrintNameFileScanner fileScanner = new RegExCharacterPrintNameFileScanner(CharacterGenericsExtractor.getGenerics(model).getCasteCollectionRegistry(), model.getRepository().getRepositoryFileResolver());
    CharacterGridPresenter gridPresenter = new CharacterGridPresenter(systemModel, view.getGridView(), stackPresenter, fileScanner, resources);
    gridPresenter.initPresentation();
  }

  private void initInteractionPresentation(ItemSelectionModel systemModel, InteractionView interactionView, IResources resources) {
    new InteractionPresenter(systemModel, interactionView, resources).initPresentation();
  }
}
