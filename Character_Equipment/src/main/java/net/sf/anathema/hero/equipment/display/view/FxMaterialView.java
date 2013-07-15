package net.sf.anathema.hero.equipment.display.view;

import javafx.scene.Node;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.equipment.display.presenter.MagicalMaterialView;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;

public class FxMaterialView implements MagicalMaterialView {

  private ComboBoxSelectionView<MagicalMaterial> materialCombo;

  public FxMaterialView(String label, AgnosticUIConfiguration<MagicalMaterial> renderer) {
    materialCombo = new ComboBoxSelectionView<>(label, renderer);
  }

  @Override
  public void setMaterials(MagicalMaterial[] materials) {
    materialCombo.setObjects(materials);
  }

  @Override
  public void setSelectedMaterial(MagicalMaterial selection, boolean viewEnabled) {
    materialCombo.setSelectedObject(selection);
    materialCombo.setEnabled(viewEnabled);
  }

  @Override
  public MagicalMaterial getSelectedMaterial() {
    return materialCombo.getSelectedObject();
  }

  public Node getNode() {
    return materialCombo.getNode();
  }
}
