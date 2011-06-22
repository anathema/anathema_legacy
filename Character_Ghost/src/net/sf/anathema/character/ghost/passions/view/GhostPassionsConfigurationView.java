package net.sf.anathema.character.ghost.passions.view;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.generic.framework.ITraitReference;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
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

  public JComponent getComponent() {
    GridDialogLayoutData data = GridDialogLayoutDataUtilities.createFillNoGrab();
    data.setGrabExcessVerticalSpace(true);
    passionPanel.add(new JScrollPane(passionListPanel), data);
    mainPanel.add(passionPanel, data);
    mainPanel.add(overviewPanel, data);
    return mainPanel;
  }
  
  public IOverviewCategory createOverview(String borderLabel) {
	    return new OverviewCategory(overviewPanel, borderLabel, false);
	  }

	  public void setOverview(IOverviewCategory overview) {
	    overviewPanel.removeAll();
	    overviewPanel.add(overview.getComponent());
	  }
	  
	  public void removeControls()
	  {
		  if (objectSelectionView != null)
			  passionPanel.remove(objectSelectionView.getComponent());
		  overviewPanel.removeAll();
	  }
}