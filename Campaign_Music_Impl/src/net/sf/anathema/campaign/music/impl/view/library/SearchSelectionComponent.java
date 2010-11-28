package net.sf.anathema.campaign.music.impl.view.library;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.disy.commons.swing.util.ToggleComponentEnabler;
import net.sf.anathema.campaign.music.view.search.ISearchComponent;

public class SearchSelectionComponent implements ISearchComponent {

  public static int getColumnCount() {
    return 3;
  }

  private final JCheckBox checkBox = new JCheckBox();
  private final JLabel label;
  private final JTextField textField = new JTextField(30);

  public SearchSelectionComponent(String label) {
    this.label = new JLabel(label);
    ToggleComponentEnabler.connect(checkBox, textField);
    ToggleComponentEnabler.connect(checkBox, this.label);
  }

  public boolean isSelected() {
    return checkBox.isSelected();
  }

  public String getSearchString() {
    return textField.getText();
  }

  public void addTo(JPanel gridPanel) {
    gridPanel.add(checkBox);
    gridPanel.add(label);
    gridPanel.add(textField);
  }
}