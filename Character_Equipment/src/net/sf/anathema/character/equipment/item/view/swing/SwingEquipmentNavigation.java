package net.sf.anathema.character.equipment.item.view.swing;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.equipment.item.view.EquipmentNavigation;
import net.sf.anathema.framework.perspective.PerspectiveToolBar;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.SwingActionTool;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.selection.IVetoableObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SwingEquipmentNavigation implements EquipmentNavigation {
  private final ListObjectSelectionView<String> templateListView = new ListObjectSelectionView<>(String.class);
  private final PerspectiveToolBar editTemplateButtonPanel = new PerspectiveToolBar();
  private final JPanel navigationPanel = new JPanel(new MigLayout(LayoutUtils.fillWithoutInsets().wrapAfter(1)));

  public SwingEquipmentNavigation() {
    JComponent templateListPanel = new JScrollPane(templateListView.getComponent());
    navigationPanel.add(editTemplateButtonPanel.getComponent());
    navigationPanel.add(templateListPanel, new CC().grow().push());
  }

  public JComponent getComponent() {
    return navigationPanel;
  }

  public IVetoableObjectSelectionView<String> getTemplateListView() {
    return templateListView;
  }

  @Override
  public Tool addEditTemplateTool() {
    SwingActionTool tool = new SwingActionTool();
    editTemplateButtonPanel.addTools(tool.getAction());
    return tool;
  }
}