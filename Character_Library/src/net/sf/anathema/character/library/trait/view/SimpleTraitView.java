package net.sf.anathema.character.library.trait.view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class SimpleTraitView extends AbstractTraitView implements IGridDialogPanelContent, ITraitView<SimpleTraitView> {

  private final JLabel label;
  private final Component displayComponent;
  private final GridAlignment dotAlignment;
  private JPanel traitViewPanel;

  public SimpleTraitView(IIntValueDisplayFactory configuration, String labelText, int value, int maxValue) {
    this(configuration, labelText, value, maxValue, GridAlignment.END);
  }

  public SimpleTraitView(
      IIntValueDisplayFactory configuration,
      String labelText,
      int value,
      int maxValue,
      GridAlignment dotAlignment) {
    super(configuration, labelText, value, maxValue);
    this.label = new JLabel(getLabelText());
    this.displayComponent = getValueDisplay().getComponent();
    this.dotAlignment = dotAlignment;
  }

  public void addComponents(IGridDialogPanel dialogPanel) {
    dialogPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        addComponents(panel);
      }
    });
  }

  public void addComponents(JPanel panel) {
    this.traitViewPanel = panel;
    panel.add(label, GridDialogLayoutData.FILL_HORIZONTAL);
    GridDialogLayoutData data = new GridDialogLayoutData();
    data.setHorizontalAlignment(dotAlignment);
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