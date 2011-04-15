package net.sf.anathema.character.ghost.passions.view;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.view.basic.IButtonControlledComboEditView;

public interface IGhostPassionsConfigurationView {

  public IPassionView addPassionView(
      String virtueName,
      String passionName,
      Icon deleteIcon,
      int value,
      int maxValue);

  public IButtonControlledComboEditView<ITraitReference> addPassionSelectionView(
      String labelText,
      ListCellRenderer renderer,
      Icon addIcon);
  
  public void setOverview(IOverviewCategory overview);

  public IOverviewCategory createOverview(String borderLabel);
}