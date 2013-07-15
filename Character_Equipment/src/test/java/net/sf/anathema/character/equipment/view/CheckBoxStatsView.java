package net.sf.anathema.character.equipment.view;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.hero.equipment.display.presenter.StatsView;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.gui.action.ActionWidgetFactory;
import net.sf.anathema.lib.gui.action.SmartToggleAction;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.model.BooleanModel;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class CheckBoxStatsView implements StatsView {
  private final JPanel panel = new JPanel(new MigLayout(LayoutUtils.withoutInsets().wrapAfter(1).gridGapY("0")));
  private final JCheckBox box;
  private final BooleanModel isSelectedModel;

  public CheckBoxStatsView(String description) {
    this.isSelectedModel = new BooleanModel();
    this.box = createCheckBox(isSelectedModel, description);
  }

  @Override
  public void setSelected(boolean selected) {
    isSelectedModel.setValue(selected);
  }

  @Override
  public boolean getSelected() {
    return isSelectedModel.getValue();
  }

  @Override
  public void disable() {
    GuiUtilities.setEnabled(panel, false);
  }

  @Override
  public void addChangeListener(ChangeListener changeListener) {
    isSelectedModel.addChangeListener(changeListener);
  }

  @Override
  public StatsView addOptionFlag(String label) {
    CheckBoxStatsView checkBoxStatsView = new CheckBoxStatsView("   ..." + label);
    panel.add(checkBoxStatsView.getComponent());
    return checkBoxStatsView;
  }

  private JCheckBox createCheckBox(BooleanModel selectedModel, String description) {
    return ActionWidgetFactory.createCheckBox(new SmartToggleAction(selectedModel, description.replaceAll("&", "&&")));
  }

  public JComponent getComponent() {
    panel.add(box);
    return panel;
  }
}