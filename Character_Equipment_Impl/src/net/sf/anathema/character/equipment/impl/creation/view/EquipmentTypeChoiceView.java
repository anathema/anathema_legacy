package net.sf.anathema.character.equipment.impl.creation.view;

import java.awt.event.ItemListener;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import net.disy.commons.swing.dialog.core.IPageContent;
import net.disy.commons.swing.layout.GridDialogLayoutDataUtilities;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.disy.commons.swing.toolbar.ToolBarUtilities;
import net.disy.commons.swing.widgets.HorizontalLine;
import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;

public class EquipmentTypeChoiceView implements IPageContent, IEquipmentTypeChoiceView {

  private static final GridDialogLayoutData OVERALL_HORIZONTAL_FILL = GridDialogLayoutDataUtilities.createHorizontalSpanData(
      3,
      GridDialogLayoutData.FILL_HORIZONTAL);
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private final JPanel content = new JPanel(new GridDialogLayout(3, false));
  private final JPanel extra = new JPanel(new GridDialogLayout(1, false));
  private JComponent focusComponent;

  public JComponent getContent()
  {
	JPanel panel = new JPanel(new GridDialogLayout(1, false));
	IGridDialogLayoutData data = GridDialogLayoutData.FILL_HORIZONTAL;
	panel.add(content, data);
	panel.add(extra, data);
	return panel;
  }

  public void addStatisticsRow(String categoryLabel, Action action, String typeLabel, boolean isSelected) {
    content.add(new JLabel(categoryLabel));
    content.add(createToggleButton(action, isSelected));
    content.add(new JLabel(typeLabel));
  }
  
  public void addCheckBox(String label, ItemListener listener, boolean isSelected)
  {
	  JCheckBox box = new JCheckBox(label);
	  box.addItemListener(listener);
	  box.setSelected(isSelected);
	  extra.add(box);
  }

  public void addHorizontalLine() {
    content.add(new HorizontalLine(), OVERALL_HORIZONTAL_FILL);
  }

  public void requestFocus() {
    focusComponent.requestFocus();
  }

  private JToggleButton createToggleButton(Action action, boolean isSelected) {
    JToggleButton toggleButton = new JToggleButton(action);
    if (focusComponent == null) {
      focusComponent = toggleButton;
    }
    toggleButton.setSelected(isSelected);
    ToolBarUtilities.configureToolBarButton(toggleButton);
    buttonGroup.add(toggleButton);
    return toggleButton;
  }

  public void dispose() {
    // Nothing to do
  }
}