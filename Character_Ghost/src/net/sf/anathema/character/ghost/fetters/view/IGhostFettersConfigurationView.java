package net.sf.anathema.character.ghost.fetters.view;

import javax.swing.Icon;

import net.sf.anathema.character.library.overview.IOverviewCategory;

public interface IGhostFettersConfigurationView {

  public IFetterView addFetterView(
      String fetterName,
      Icon deleteIcon,
      int value,
      int maxValue);

  public ButtonControlledEditView addFetterSelectionView(
      String labelText,
      Icon addIcon);
  
  public void setOverview(IOverviewCategory overview);

  public IOverviewCategory createOverview(String borderLabel);
}