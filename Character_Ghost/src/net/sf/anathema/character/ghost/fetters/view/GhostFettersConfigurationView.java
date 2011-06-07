package net.sf.anathema.character.ghost.fetters.view;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.overview.IOverviewCategory;
import net.sf.anathema.character.library.overview.OverviewCategory;
import net.sf.anathema.lib.gui.IView;

public class GhostFettersConfigurationView implements IGhostFettersConfigurationView, IView {

  private final IIntValueDisplayFactory factory;
  private final JPanel mainPanel = new JPanel(new GridDialogLayout(2, false));
  private final JPanel fetterListPanel = new JPanel(new GridDialogLayout(3, false));
  private final JPanel fetterPanel = new JPanel(new GridDialogLayout(1, true));
  private final JPanel overviewPanel = new JPanel();

  public GhostFettersConfigurationView(IIntValueDisplayFactory factory) {
    this.factory = factory;
  }

  public ButtonControlledEditView addFetterSelectionView(
      String labelText,
      Icon addIcon) {
    ButtonControlledEditView objectSelectionView = new ButtonControlledEditView(
        addIcon);
    fetterPanel.add(objectSelectionView.getComponent());
    return objectSelectionView;
  }
  
  public IFetterView addFetterView(
	      String fetterName,
	      Icon deleteIcon,
	      int value,
	      int maxValue) {
	    FetterView fetterView = new FetterView(factory, deleteIcon, fetterName, value, maxValue);
	    fetterView.addComponents(fetterListPanel);
	    fetterListPanel.revalidate();
	    return fetterView;
	  }

  public JComponent getComponent() {
    GridDialogLayoutData data = GridDialogLayoutDataUtilities.createFillNoGrab();
    data.setGrabExcessVerticalSpace(true);
    fetterPanel.add(new JScrollPane(fetterListPanel), data);
    mainPanel.add(fetterPanel, data);
    mainPanel.add(overviewPanel, data);
    return mainPanel;
  }
  
  public IOverviewCategory createOverview(String borderLabel) {
	    return new OverviewCategory(overviewPanel, borderLabel, false);
	  }

	  @SuppressWarnings("deprecation")
	public void setOverview(IOverviewCategory overview) {
	    overviewPanel.removeAll();
	    overviewPanel.add(overview.getComponent());
	  }
}