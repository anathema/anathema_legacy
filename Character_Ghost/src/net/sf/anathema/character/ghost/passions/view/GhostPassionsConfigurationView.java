package net.sf.anathema.character.ghost.passions.view;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.framework.value.IIntValueDisplayFactory;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.framework.presenter.view.ButtonControlledComboEditView;
import net.sf.anathema.framework.presenter.view.IButtonControlledComboEditView;
import net.sf.anathema.lib.gui.IView;

public class GhostPassionsConfigurationView implements IGhostPassionsConfigurationView, IView {

  private final IIntValueDisplayFactory factory;
  private final JPanel mainPanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel passionListPanel = new JPanel(new GridDialogLayout(5, false));
  private final JPanel passionPanel = new JPanel(new GridDialogLayout(1, true));
  private final JPanel overviewPanel = new JPanel();
  private ButtonControlledComboEditView<ITraitReference> objectSelectionView;

  public GhostPassionsConfigurationView(IIntValueDisplayFactory factory) {
    this.factory = factory;
  }

  @Override
  public IButtonControlledComboEditView<ITraitReference> addPassionSelectionView(
      String labelText,
      ListCellRenderer renderer,
      Icon addIcon) {
	objectSelectionView = new ButtonControlledComboEditView<ITraitReference>(
        addIcon,
        renderer);
    passionPanel.add(objectSelectionView.getComponent());
    return objectSelectionView;
  }
  
  @Override
  public IPassionView addPassionView(
	      String virtueName,
	      String passionName,
	      Icon deleteIcon,
	      int value,
	      int maxValue) {
	    PassionView passionView = new PassionView(factory, virtueName, deleteIcon, passionName, value, maxValue);
	    passionView.addComponents(passionListPanel);
	    passionListPanel.revalidate();
	    return passionView;
	  }

  @Override
  public JComponent getComponent() {
    GridDialogLayoutData data = GridDialogLayoutDataFactory.createFillNoGrab();
    data.setGrabExcessVerticalSpace(true);
    passionPanel.add(new JScrollPane(passionListPanel), data);
    mainPanel.add(passionPanel, data);
    mainPanel.add(overviewPanel, data);
    return mainPanel;
  }
  
  @Override
  public IOverviewCategory createOverview(String borderLabel) {
	    return new OverviewCategory(overviewPanel, borderLabel, false);
	  }

	  @Override
      public void setOverview(IOverviewCategory overview) {
	    overviewPanel.removeAll();
	    overviewPanel.add(overview.getComponent());
	  }
	  
	  @Override
      public void removeControls()
	  {
		  if (objectSelectionView != null)
			  passionPanel.remove(objectSelectionView.getComponent());
		  overviewPanel.removeAll();
	  }
}