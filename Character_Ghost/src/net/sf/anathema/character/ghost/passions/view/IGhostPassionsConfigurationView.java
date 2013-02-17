package net.sf.anathema.character.ghost.passions.view;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;

import javax.swing.*;

public interface IGhostPassionsConfigurationView {

  IPassionView addPassionView(String virtueName, String passionName, Icon deleteIcon, int value, int maxValue);

  IButtonControlledComboEditView<ITraitReference> addPassionSelectionView(String labelText, ListCellRenderer renderer, Icon addIcon);
  
  void setOverview(IOverviewCategory overview);
  
  void removeControls();

  IOverviewCategory createOverview(String borderLabel);
}