package net.sf.anathema.character.ghost.fetters.view;

import net.sf.anathema.character.library.overview.IOverviewCategory;

import javax.swing.Icon;

public interface IGhostFettersConfigurationView {

  IFetterView addFetterView(String fetterName, Icon deleteIcon, int value, int maxValue);

  ButtonControlledEditView addFetterSelectionView(String labelText, Icon addIcon);
  
  void setOverview(IOverviewCategory overview);

  IOverviewCategory createOverview(String borderLabel);
}