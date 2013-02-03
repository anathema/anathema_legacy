package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.perspective.CharacterGridPresenter;
import net.sf.anathema.character.perspective.CharacterStackBridge;
import net.sf.anathema.character.perspective.CharacterStackPresenter;
import net.sf.anathema.character.perspective.model.CharacterSystemModel;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.swing.character.perspective.CharacterStackSwingBridge;
import net.sf.anathema.swing.character.perspective.CharacterSystemView;

@PerspectiveAutoCollector
@Weight(weight = 1)
public class CharacterSystemPerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon("group-icon.png");
    toggle.setTooltip("Character");
  }

  @Override
  public void initContent(Container container, IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    CharacterSystemModel systemModel = new CharacterSystemModel(model);
    CharacterSystemView view = new CharacterSystemView();
    initPresentation(model, systemModel, view);
    initInteractionPresentation(systemModel, new InteractionView());
    container.setSwingContent(view.getComponent());
  }

  private void initPresentation(IAnathemaModel model, CharacterSystemModel systemModel, CharacterSystemView view) {
    CharacterStackBridge bridge = new CharacterStackSwingBridge(model, view.getStackView());
    CharacterStackPresenter stackPresenter = new CharacterStackPresenter(bridge, systemModel);
    CharacterGridPresenter gridPresenter = new CharacterGridPresenter(systemModel, view.getGridView(), stackPresenter);
    gridPresenter.initPresentation();
  }

  private void initInteractionPresentation(CharacterSystemModel systemModel, InteractionView interactionView) {
    new InteractionPresenter(systemModel, interactionView).initPresentation();
  }
}
