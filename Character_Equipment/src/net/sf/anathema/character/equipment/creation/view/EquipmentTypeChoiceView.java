package net.sf.anathema.character.equipment.creation.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.framework.view.toolbar.ToolbarUtilities;
import net.sf.anathema.lib.gui.dialog.core.IPageContent;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.widgets.HorizontalLine;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class EquipmentTypeChoiceView implements IPageContent, IEquipmentTypeChoiceView {
  private final ButtonGroup buttonGroup = new ButtonGroup();
  private final JPanel content = new JPanel(new MigLayout(LayoutUtils.withoutInsets().wrapAfter(3)));
  private JComponent focusComponent;

  @Override
  public JComponent getContent() {
    return content;
  }

  @Override
  public void addStatisticsRow(String categoryLabel, Action action, String typeLabel, boolean isSelected) {
    content.add(new JLabel(categoryLabel), new CC());
    content.add(createToggleButton(action, isSelected), new CC().alignX("left"));
    content.add(new JLabel(typeLabel), new CC().alignX("left").pushX().growX());
  }

  @Override
  public void addHorizontalLine() {
    content.add(new HorizontalLine(), new CC().spanX().growX());
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
    ToolbarUtilities.configureToolBarButton(toggleButton);
    buttonGroup.add(toggleButton);
    return toggleButton;
  }
}