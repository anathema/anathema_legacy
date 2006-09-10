package net.sf.anathema.character.impl.view.concept;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import net.disy.commons.swing.layout.grid.GridAlignment;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.view.concept.IWillpowerConditionView;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;
import net.sf.anathema.lib.gui.gridlayout.IGridDialogPanel;

public class WillpowerConditionView implements IGridDialogPanelContent, IWillpowerConditionView {
  private final JTextArea conditionLabel;
  private final JLabel headerLabel;

  public WillpowerConditionView(String labelText) {
    conditionLabel = new JTextArea(2, 45);
    conditionLabel.setEditable(false);
    conditionLabel.setWrapStyleWord(true);
    conditionLabel.setLineWrap(true);
    conditionLabel.setDisabledTextColor(Color.DARK_GRAY);
    JLabel label = new JLabel();
    conditionLabel.setFont(label.getFont());
    conditionLabel.setBackground(label.getBackground());
    this.headerLabel = new JLabel(labelText);
  }

  public void addComponents(IGridDialogPanel dialogPanel) {
    dialogPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 2;
      }

      public void fillInto(JPanel panel, int columnCount) {
        GridDialogLayoutData labelLayoutData = new GridDialogLayoutData();
        labelLayoutData.setHorizontalAlignment(GridAlignment.BEGINNING);
        labelLayoutData.setVerticalAlignment(GridAlignment.BEGINNING);
        panel.add(headerLabel, labelLayoutData);
        GridDialogLayoutData contentData = new GridDialogLayoutData();
        contentData.setHorizontalAlignment(GridAlignment.FILL);
        contentData.setVerticalAlignment(GridAlignment.FILL);
        panel.add(conditionLabel, contentData);
      }
    });
  }

  public void setEnabled(boolean enabled) {
    if (enabled) {
      headerLabel.setForeground(SystemColor.textText);
    }
    else {
      headerLabel.setForeground(Color.DARK_GRAY);
    }
    conditionLabel.setEnabled(enabled);
  }
  
  public void setText(String text) {
    conditionLabel.setText(text);    
  }
}
