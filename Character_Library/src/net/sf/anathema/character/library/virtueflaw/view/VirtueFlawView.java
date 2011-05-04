package net.sf.anathema.character.library.virtueflaw.view;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.character.generic.framework.util.ExperienceUtilities;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

public class VirtueFlawView implements IVirtueFlawView {
  private final JPanel virtueFlawPanel = new JPanel(new GridDialogLayout(2, false));
  private final IIntValueDisplayFactory intValueDisplayFactory;
  
  public VirtueFlawView(IIntValueDisplayFactory factory)
  {
	  this.intValueDisplayFactory = factory;
  }

  public ITextView addTextView(final String labelText, int columns) {
    final ITextView textView = new LineTextView(columns);
    fillIntoVirtueFlawPanel(labelText, textView);
    return textView;
  }
  
  public SimpleTraitView addLimitValueView(String label, int value, int maxValue) {
	   SimpleTraitView traitView = new SimpleTraitView(
	        intValueDisplayFactory,
	        label,
	        value,
	        maxValue,
	        null,
	        GridAlignment.BEGINNING,
	        null);
	    traitView.addComponents((JPanel) getComponent());
	    return traitView;
	  }

  protected void fillIntoVirtueFlawPanel(final String labelText, final ITextView textView) {
    new LabelTextView(labelText, textView).addToStandardPanel(
        virtueFlawPanel,
        GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
  }

  public void setEnabled(boolean enabled) {
    GuiUtilities.setEnabled(getComponent(), enabled);
    handleSpecialComponents(getComponent(), enabled);
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
    return virtueFlawPanel;
  }

  public IObjectSelectionView<ITraitType> addVirtueFlawRootSelectionView(
      final String labelText,
      ListCellRenderer renderer) {
    final ObjectSelectionView<ITraitType> rootSelectionView = new ObjectSelectionView<ITraitType>(
        labelText,
        renderer,
        new ITraitType[0]);
    rootSelectionView.addTo(virtueFlawPanel, GridDialogLayoutDataUtilities.createHorizontalFillNoGrab());
    return rootSelectionView;
  }
}