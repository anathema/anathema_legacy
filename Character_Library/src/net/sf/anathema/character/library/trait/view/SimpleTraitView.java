package net.sf.anathema.character.library.trait.view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;

public class SimpleTraitView extends AbstractTraitView implements ITraitView<SimpleTraitView> {

  private final JLabel label;
  private final Component displayComponent;
  private final GridAlignment dotAlignment;
  private final IGridDialogLayoutData labelAlignment;
  private JPanel traitViewPanel;

  public SimpleTraitView(IIntValueDisplayFactory factory, String labelText, int value, int maxValue) {
    this(factory, labelText, value, maxValue, GridAlignment.END);
  }
  
  public SimpleTraitView(
	      IIntValueDisplayFactory factory,
	      String labelText,
	      int value,
	      int maxValue,
	      GridAlignment dotAlignment)
  {
	  this(factory, labelText, value, maxValue, dotAlignment, GridDialogLayoutData.FILL_HORIZONTAL);
  }

  public SimpleTraitView(
      IIntValueDisplayFactory factory,
      String labelText,
      int value,
      int maxValue,
      GridAlignment dotAlignment,
      IGridDialogLayoutData labelAlignment) {
    super(factory, labelText, value, maxValue);
    this.label = new JLabel(getLabelText());
    this.displayComponent = maxValue > 0 ? getValueDisplay().getComponent() : null;
    this.dotAlignment = dotAlignment;
    this.labelAlignment = labelAlignment;
  }

  public void addComponents(JPanel panel) {
    this.traitViewPanel = panel;
    panel.add(label);
    if (labelAlignment != null)
    	panel.add(label, labelAlignment);
    else
    	panel.add(label);
    GridDialogLayoutData data = new GridDialogLayoutData();
    data.setHorizontalAlignment(dotAlignment);
    if (displayComponent != null)
    	panel.add(displayComponent, data);
  }

  public void delete() {
    traitViewPanel.remove(label);
    traitViewPanel.remove(displayComponent);
    traitViewPanel.revalidate();
  }

  public SimpleTraitView getInnerView() {
    return this;
  }
}