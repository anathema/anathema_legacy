package net.sf.anathema.character.library.trait.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;

public class SimpleTraitView extends AbstractTraitView implements IGridDialogPanelContent {

  private final int indent;
  private GridDialogLayoutData dotLayoutData = new GridDialogLayoutData();

  public SimpleTraitView(IIntValueDisplayFactory configuration, String labelText, int value, int maxValue) {
    this(configuration, labelText, value, maxValue, 0);
  }

  public SimpleTraitView(IIntValueDisplayFactory configuration, String labelText, int value, int maxValue, int indent) {
    super(configuration, labelText, value, maxValue);
    this.indent = indent;
  }

  public void addComponents(GridDialogPanel dialogPanel) {
    dialogPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        addToPanel(panel);
      }
    });
  }

  private void addToPanel(JPanel panel) {
    GridDialogLayoutData labelData = new GridDialogLayoutData();
    labelData.setHorizontalIndent(indent);
    panel.add(new JLabel(getLabelText()), labelData);
    panel.add(getValueDisplay().getComponent(), dotLayoutData);
  }

  public void setDotLayoutData(GridDialogLayoutData dotLayoutData) {
    this.dotLayoutData = dotLayoutData;
  }
}