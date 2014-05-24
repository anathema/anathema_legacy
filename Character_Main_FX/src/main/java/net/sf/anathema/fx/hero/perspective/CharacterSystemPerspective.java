package net.sf.anathema.fx.hero.perspective;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.framework.view.perspective.Container;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.framework.view.perspective.PerspectiveToggle;
import net.sf.anathema.hero.framework.perspective.CharacterGridPresenter;
import net.sf.anathema.hero.framework.perspective.CharacterStackBridge;
import net.sf.anathema.hero.framework.perspective.CharacterStackPresenter;
import net.sf.anathema.hero.framework.perspective.CharacterSystemModel;
import net.sf.anathema.hero.framework.perspective.InteractionPresenter;
import net.sf.anathema.hero.framework.perspective.ShowOnSelect;
import net.sf.anathema.hero.platform.CharacterGenericsExtension;
import net.sf.anathema.lib.file.RelativePath;

@PerspectiveAutoCollector
@Weight(weight = 1)
public class CharacterSystemPerspective implements Perspective {

  @Override
  public void configureToggle(PerspectiveToggle toggle) {
    toggle.setIcon(new RelativePath("icons/King-icon.png"));
    toggle.setTooltip("CharacterSystem.Perspective.Name");
  }

  @Override
  public void initContent(Container container, IApplicationModel model, Environment environment) {
    initializeCharacterSystem(model, environment);
    CharacterSystemModel systemModel = new CharacterSystemModel(model);
    CharacterSystemView view = new CharacterSystemView();
    container.setContent(view.getNode());
    CharacterViewFactory viewFactory = new CharacterViewFactory(environment, model);
    CharacterStackBridge bridge = new CharacterStackFxBridge(viewFactory, view.getStackView());
    CharacterStackPresenter stackPresenter = new CharacterStackPresenter(bridge, systemModel);
    ShowOnSelect showOnSelect = new ShowOnSelect(stackPresenter);
    CharacterGridPresenter gridPresenter = new CharacterGridPresenter(systemModel, view.getGridView(), showOnSelect, environment);
    gridPresenter.initPresentation();
    new InteractionPresenter(systemModel, view.getInteractionView(), environment, view.getGridView(), showOnSelect).initPresentation();
  }

  private void initializeCharacterSystem(IApplicationModel model, Environment environment) {
    CharacterGenericsExtension extension = new CharacterGenericsExtension();
    extension.initialize(model.getRepository(), environment, environment);
    model.getExtensionPointRegistry().register(CharacterGenericsExtension.ID, extension);
  }
}