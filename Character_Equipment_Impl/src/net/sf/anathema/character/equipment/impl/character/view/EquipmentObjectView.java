package net.sf.anathema.character.equipment.impl.character.view;

import javax.swing.JLabel;

import net.disy.commons.core.model.BooleanModel;
import net.disy.commons.swing.action.ActionWidgetFactory;
import net.disy.commons.swing.action.SmartToggleAction;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.lib.gui.GuiUtilities;

import com.l2fprod.common.swing.JTaskPaneGroup;

public class EquipmentObjectView implements IEquipmentObjectView {

  private final JTaskPaneGroup taskGroup = new JTaskPaneGroup();
  private final JLabel descriptionLabel = new JLabel();

  public EquipmentObjectView() {
    taskGroup.add(descriptionLabel);
  }

  public void setItemTitle(String title) {
    taskGroup.setTitle(title);
  }

  public void setItemDescription(String text) {
    descriptionLabel.setText(text);
    GuiUtilities.revalidate(taskGroup);
  }

  public BooleanModel addStats(String description) {
    BooleanModel isSelectedModel = new BooleanModel();
    taskGroup.add(ActionWidgetFactory.createCheckBox(new SmartToggleAction(isSelectedModel, description)));
    return isSelectedModel;
  }

  public JTaskPaneGroup getTaskGroup() {
    return taskGroup;
  }
}