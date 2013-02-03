package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.swing.character.perspective.CharacterStackBridge;

import javax.swing.JComponent;

@PerspectiveAutoCollector
@Weight(weight = 1)
public class CharacterSystemPerspective implements Perspective {


  public String getTitle() {
    return "Character";
  }

  @Override
  public JComponent createContent(IAnathemaModel model, IResources resources, ReflectionObjectFactory objectFactory) {
    CharacterSystemView view = new CharacterSystemView();
    CharacterSystemModel systemModel = new CharacterSystemModel(model);
    CharacterStackBridge bridge = new CharacterStackBridge(model, view.getStackView());
    CharacterStackPresenter presenter = new CharacterStackPresenter(bridge, systemModel);
    view.fillButtonGrid(model, presenter);
    return view.getComponent();
  }
}
