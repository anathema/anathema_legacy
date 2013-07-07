package net.sf.anathema.character.equipment.impl.character.view;

import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.main.library.taskpane.ITaskPaneGroupView;
import net.sf.anathema.character.main.magic.view.AddToTaskPane;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.action.ActionWidgetFactory;
import net.sf.anathema.lib.gui.action.SmartToggleAction;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.model.BooleanModel;
import net.sf.anathema.swing.interaction.ActionInteraction;
import org.jdesktop.swingx.JXTaskPane;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.HashMap;
import java.util.Map;

public class EquipmentObjectView implements IEquipmentObjectView, ITaskPaneGroupView {
  private final JXTaskPane taskGroup = new JXTaskPane();
  private final JLabel descriptionLabel = new JLabel();
  private final Map<BooleanModel, JCheckBox> boxes = new HashMap<>();
  private final Map<BooleanModel, JPanel> boxPanels = new HashMap<>();

  public EquipmentObjectView() {
    taskGroup.add(descriptionLabel);
  }

  @Override
  public void setItemTitle(String title) {
    taskGroup.setTitle(title);
  }

  @Override
  public void setItemDescription(String text) {
    descriptionLabel.setText(text);
    net.sf.anathema.lib.gui.swing.GuiUtilities.revalidate(taskGroup);
  }

  @Override
  public void clearContents() {
    taskGroup.removeAll();
    boxes.clear();
    boxPanels.clear();
    taskGroup.add(descriptionLabel);
  }

  @Override
  public BooleanModel addStats(String description) {
    BooleanModel isSelectedModel = new BooleanModel();
    JCheckBox box = createCheckBox(isSelectedModel, description);
    boxes.put(isSelectedModel, box);
    JPanel panel = new JPanel(new MigLayout(LayoutUtils.withoutInsets().wrapAfter(1).gridGapY("0")));
    panel.add(box);
    taskGroup.add(panel);
    boxPanels.put(isSelectedModel, panel);
    return isSelectedModel;
  }

  @Override
  public BooleanModel addOptionFlag(BooleanModel base, String description) {
    BooleanModel isSelectedModel = new BooleanModel();
    JPanel basePanel = boxPanels.get(base);
    if (basePanel != null) {
      JPanel optionPanel = new JPanel(new MigLayout(LayoutUtils.withoutInsets().wrapAfter(2)));
      optionPanel.add(new JLabel("   ..."));
      JCheckBox box = createCheckBox(isSelectedModel, description);
      boxes.put(isSelectedModel, box);
      optionPanel.add(box);
      basePanel.add(optionPanel);
    }
    return isSelectedModel;
  }

  @Override
  public void setEnabled(BooleanModel model, boolean enabled) {
    boxes.get(model).setEnabled(enabled);
  }

  @Override
  public JXTaskPane getTaskGroup() {
    return taskGroup;
  }

  @Override
  public Tool addAction() {
    ActionInteraction tool = new ActionInteraction();
    tool.addTo(new AddToTaskPane(taskGroup));
    return tool;
  }

  private JCheckBox createCheckBox(BooleanModel selectedModel, String description) {
    return ActionWidgetFactory.createCheckBox(new SmartToggleAction(selectedModel, description.replaceAll("&", "&&")));
  }
}