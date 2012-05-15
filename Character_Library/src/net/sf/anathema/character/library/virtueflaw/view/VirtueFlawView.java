package net.sf.anathema.character.library.virtueflaw.view;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.sf.anathema.character.generic.framework.util.ExperienceUtilities;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.trait.view.SimpleTraitView;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawView;
import net.sf.anathema.framework.value.IntegerViewFactory;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LabelTextView;
import net.sf.anathema.lib.workflow.textualdescription.view.LineTextView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.awt.Container;

public class VirtueFlawView implements IVirtueFlawView {
  private final JPanel virtueFlawPanel = new JPanel(new GridDialogLayout(2, false));
  private final IntegerViewFactory intValueDisplayFactory;
  
  public VirtueFlawView(IntegerViewFactory factory)
  {
	  this.intValueDisplayFactory = factory;
  }

  @Override
  public ITextView addTextView(String labelText, int columns) {
    ITextView textView = new LineTextView(columns);
    fillIntoVirtueFlawPanel(labelText, textView);
    return textView;
  }
  
  @Override
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

  protected void fillIntoVirtueFlawPanel(String labelText, ITextView textView) {
    new LabelTextView(labelText, textView).addToStandardPanel(
        virtueFlawPanel,
        GridDialogLayoutDataFactory.createHorizontalFillNoGrab());
  }

  @Override
  public void setEnabled(boolean enabled) {
    net.sf.anathema.lib.gui.swing.GuiUtilities.setEnabled(getComponent(), enabled);
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

  @Override
  public JComponent getComponent() {
    return virtueFlawPanel;
  }

  @Override
  public IObjectSelectionView<ITraitType> addVirtueFlawRootSelectionView(
      String labelText,
      ListCellRenderer renderer) {
    ObjectSelectionView<ITraitType> rootSelectionView = new ObjectSelectionView<ITraitType>(
        labelText,
        renderer,
        new ITraitType[0]);
    rootSelectionView.addTo(virtueFlawPanel, GridDialogLayoutDataFactory.createHorizontalFillNoGrab());
    return rootSelectionView;
  }
}