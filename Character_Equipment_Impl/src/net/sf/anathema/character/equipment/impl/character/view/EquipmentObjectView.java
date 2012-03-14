package net.sf.anathema.character.equipment.impl.character.view;

import net.disy.commons.core.model.BooleanModel;
import net.disy.commons.swing.action.ActionWidgetFactory;
import net.disy.commons.swing.action.SmartToggleAction;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.library.taskpane.ITaskPaneGroupView;
import net.sf.anathema.lib.gui.GuiUtilities;
import org.jdesktop.swingx.JXTaskPane;

import javax.swing.Action;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.util.HashMap;
import java.util.Map;

public class EquipmentObjectView implements IEquipmentObjectView, ITaskPaneGroupView {

  private final JXTaskPane taskGroup = new JXTaskPane();
  private final JLabel descriptionLabel = new JLabel();
  private final Map<BooleanModel, JCheckBox> boxes = new HashMap<BooleanModel, JCheckBox>();

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
    JCheckBox box = ActionWidgetFactory.createCheckBox(new SmartToggleAction(isSelectedModel, description));
    boxes.put(isSelectedModel, box);
    taskGroup.add(box);
    return isSelectedModel;
  }
  
  public void updateStatText(BooleanModel model, String newText)
  {
	  boxes.get(model).setText(newText);
  }
  
  public void setEnabled(BooleanModel model, boolean enabled)
  {
	  boxes.get(model).setEnabled(enabled);
  }

  public JXTaskPane getTaskGroup() {
    return taskGroup;
  }

  public void addAction(Action action) {
    taskGroup.add(action);
  }
}
