package net.sf.anathema.character.presenter;

import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.interaction.Tool;

public interface ExtensibleTraitView {
  IIntValueView getIntValueView();

  ToggleTool addToggleInFront();

  ToggleTool addToggleBehind(IIconToggleButtonProperties properties);

  Tool addToolBehind();

  void remove();
}