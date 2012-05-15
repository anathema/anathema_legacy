package net.sf.anathema.campaign.music.impl.view.library;

import net.sf.anathema.campaign.music.view.search.ISearchComponent;
import net.sf.anathema.lib.gui.swing.ToggleComponentEnabler;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

  @Override
  public boolean isSelected() {
    return checkBox.isSelected();
  }

  @Override
  public String getSearchString() {
    return textField.getText();
  }

  public void addTo(JPanel gridPanel) {
    gridPanel.add(checkBox);
    gridPanel.add(label);
    gridPanel.add(textField);
  }
}