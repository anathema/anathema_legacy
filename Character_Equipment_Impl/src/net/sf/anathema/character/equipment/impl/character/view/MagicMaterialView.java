package net.sf.anathema.character.equipment.impl.character.view;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.character.view.IMagicalMaterialView;
import net.sf.anathema.lib.gui.GuiUtilities;
import net.sf.anathema.lib.gui.widgets.ChangeableJComboBox;

public class MagicMaterialView implements IMagicalMaterialView {

  private final ChangeableJComboBox<MagicalMaterial> materialCombo = new ChangeableJComboBox<MagicalMaterial>(
      new MagicalMaterial[0],
      false);
  private final JLabel label = new JLabel();
  private JPanel content;

  public JComponent getComponent() {
    if (content == null) {
      content = new JPanel(new GridDialogLayout(2, false));
      content.add(label);
      content.add(materialCombo.getComponent(), GridDialogLayoutData.FILL_HORIZONTAL);
    }
    return content;
  }

  public void initView(String label, ListCellRenderer renderer, MagicalMaterial[] materials, MagicalMaterial selection) {
    this.label.setText(label);
    materialCombo.setObjects(materials);
    materialCombo.setRenderer(renderer);
    setSelectedMaterial(selection);
    if (content != null) {
      GuiUtilities.revalidateTree(content);
    }
  }

  public void setSelectedMaterial(MagicalMaterial selection) {
    materialCombo.setSelectedObject(selection);
    materialCombo.getComponent().setEnabled(selection != null);
  }
  
  public MagicalMaterial getSelectedMaterial() {
    return materialCombo.getSelectedObject();
  }
}