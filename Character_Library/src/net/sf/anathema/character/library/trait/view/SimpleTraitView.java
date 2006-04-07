package net.sf.anathema.character.library.trait.view;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;

public class SimpleTraitView extends AbstractTraitView implements IGridDialogPanelContent, ITraitView {

  private GridDialogLayoutData dotLayoutData = new GridDialogLayoutData();
  private final JLabel label;
  private final Component displayComponent;
  private JPanel traitViewPanel;

  public SimpleTraitView(IIntValueDisplayFactory configuration, String labelText, int value, int maxValue) {
    super(configuration, labelText, value, maxValue);
    this.label = new JLabel(getLabelText());
    this.displayComponent = getValueDisplay().getComponent();
  }

  public void addComponents(GridDialogPanel dialogPanel) {
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
    panel.add(label, new GridDialogLayoutData());
    panel.add(displayComponent, dotLayoutData);
  }

  public void setDotLayoutData(GridDialogLayoutData dotLayoutData) {
    this.dotLayoutData = dotLayoutData;
  }

  public void delete() {
    traitViewPanel.remove(label);
    traitViewPanel.remove(displayComponent);
    traitViewPanel.revalidate();
  }
}