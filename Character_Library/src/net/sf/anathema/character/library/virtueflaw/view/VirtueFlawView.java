package net.sf.anathema.character.library.virtueflaw.view;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.generic.framework.util.ExperienceUtilities;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.gui.gridlayout.DefaultGridDialogPanel;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class VirtueFlawView implements IVirtueFlawView {
  private final IGridDialogPanel virtueFlawPanel = new DefaultGridDialogPanel(false);
  private final List<ITextView> textViews = new ArrayList<ITextView>();

  public ITextView addTextView(final String labelText, int columns) {
    final ITextView textView = new LineTextView(columns);
    fillIntoVirtueFlawPanel(labelText, textView);
    return textView;
  }

  protected void fillIntoVirtueFlawPanel(final String labelText, final ITextView textView) {
    getVirtueFlawPanel().add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        GridDialogLayoutData labelData = new GridDialogLayoutData();
        labelData.setVerticalAlignment(GridAlignment.BEGINNING);
        panel.add(new JLabel(labelText), labelData);
        panel.add(textView.getComponent(), GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
      }
    });
  }

  public void setEnabled(boolean enabled) {
    GuiUtilities.setEnabled(getComponent(), enabled);
    handleSpecialComponents(getComponent(), enabled);
    for (ITextView textView : textViews) {
      textView.setEnabled(enabled);
    }
  }

  private void handleSpecialComponents(Container container, boolean enabled) {
    for (Component component : container.getComponents()) {
      if (component instanceof Container) {
        handleSpecialComponents((Container) component, enabled);
      }
    }
    ExperienceUtilities.setLabelColor(container, enabled);
  }

  public JComponent getComponent() {
    return virtueFlawPanel.getComponent();
  }

  protected IGridDialogPanel getVirtueFlawPanel() {
    return virtueFlawPanel;
  }

  protected List<ITextView> getTextViews() {
    return textViews;
  }

  public IObjectSelectionView<ITraitType> addVirtueFlawRootSelectionView(
      final String labelText,
      ListCellRenderer renderer) {
    final ObjectSelectionView<ITraitType> rootSelectionView = new ObjectSelectionView<ITraitType>(
        labelText,
        renderer,
        new ITraitType[0]);
    rootSelectionView.addComponents(getVirtueFlawPanel(), GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
    return rootSelectionView;
  }
}