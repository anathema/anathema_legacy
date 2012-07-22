package net.sf.anathema.character.equipment.impl.creation.view;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.GridDialogLayoutDataFactory;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.character.equipment.creation.view.IEquipmentTypeChoiceView;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.toolbar.ToolBarUtilities;
import net.sf.anathema.lib.gui.widgets.HorizontalLine;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class EquipmentTypeChoiceView implements IPageContent, IEquipmentTypeChoiceView {

  private static final GridDialogLayoutData OVERALL_HORIZONTAL_FILL = GridDialogLayoutDataFactory.createHorizontalSpanData(
          3, GridDialogLayoutData.FILL_HORIZONTAL);
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private final JPanel content = new JPanel(new GridDialogLayout(3, false));
  private JComponent focusComponent;

  @Override
  public JComponent getContent() {
    JPanel panel = new JPanel(new GridDialogLayout(1, false));
    IGridDialogLayoutData data = GridDialogLayoutData.FILL_HORIZONTAL;
    panel.add(content, data);
    return panel;
  }

  @Override
  public void addStatisticsRow(String categoryLabel, Action action, String typeLabel, boolean isSelected) {
    content.add(new JLabel(categoryLabel));
    content.add(createToggleButton(action, isSelected));
    content.add(new JLabel(typeLabel));
  }

  @Override
  public void addHorizontalLine() {
    content.add(new HorizontalLine(), OVERALL_HORIZONTAL_FILL);
  }

  @Override
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

  @Override
  public void dispose() {
    // Nothing to do
  }
}