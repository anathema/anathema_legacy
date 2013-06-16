package net.sf.anathema.character.presenter;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.interaction.ToggleTool;

public interface ExtensibleTraitView {
  IIntValueView getIntValueView();

  ToggleTool addToggleButtonInFront();
}
