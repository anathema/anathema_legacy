package net.sf.anathema.character.library.trait.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogPanel;
import net.disy.commons.swing.layout.grid.IDialogComponent;
import net.sf.anathema.character.library.intvalue.IFavorableIntValueView;
import net.sf.anathema.character.library.intvalue.IFavorableIntViewProperties;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.framework.value.IconToggleButton;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.gui.dialogcomponent.grouped.IGridDialogPanelContent;

public class FavorableTraitView extends AbstractTraitView implements IFavorableIntValueView, IGridDialogPanelContent {

  private final IconToggleButton button;
  private final IFavorableIntViewProperties properties;

  public FavorableTraitView(
      IIntValueDisplayFactory configuration,
      IFavorableIntViewProperties properties,
      String labelText,
      int value,
      int maxValue,
      boolean selected) {
    super(configuration, labelText, value, maxValue);
    this.properties = properties;
    this.button = new IconToggleButton(properties.createStandardIcon(), properties.createUnselectedIcon());
    setButtonState(selected, true);
  }

  public void addComponents(GridDialogPanel abilityPanel) {
    abilityPanel.add(new IDialogComponent() {
      public int getColumnCount() {
        return 3;
      }

      public void fillInto(JPanel panel, int columnCount) {
        GridDialogLayoutData gridLayoutData = new GridDialogLayoutData();
        gridLayoutData.setHorizontalIndent(5);
        panel.add(button.getComponent(), gridLayoutData);
        panel.add(new JLabel(getLabelText()));
        panel.add(getValueDisplay().getComponent());
      }
    });
  }

  public void setButtonState(boolean selected, boolean enabled) {
    button.setIconSet(properties.createStandardIcon(), properties.createUnselectedIcon());
    button.setEnabled(enabled);
    button.setSelected(selected);
  }

  public void addButtonSelectedListener(final IBooleanValueChangedListener listener) {
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.valueChanged(!button.isSelected());
      }
    });
  }

  public void setFavoredButtonEnabled(boolean enabled) {
    button.setIconSet(properties.createStandardIcon(), properties.createUnselectedIcon());
    button.setEnabled(enabled);
  }
}