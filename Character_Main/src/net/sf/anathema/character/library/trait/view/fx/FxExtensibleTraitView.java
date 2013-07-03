package net.sf.anathema.character.library.trait.view.fx;

import javafx.scene.Node;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.presenter.ExtensibleTraitView;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;

public class FxExtensibleTraitView implements ExtensibleTraitView {
  private FxTraitView view;

  public FxExtensibleTraitView(FxTraitView view) {
    this.view = view;
  }

  public Node getNode() {
    return null;
  }

  @Override
  public IIntValueView getIntValueView() {
    return view;
  }

  @Override
  public ToggleTool addToggleInFront(IIconToggleButtonProperties properties) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public ToggleTool addToggleBehind(IIconToggleButtonProperties properties) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Tool addToolBehind() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void remove() {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
