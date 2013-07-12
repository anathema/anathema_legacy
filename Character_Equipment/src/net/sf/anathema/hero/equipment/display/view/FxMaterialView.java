package net.sf.anathema.hero.equipment.display.view;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.equipment.display.presenter.MagicalMaterialView;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionView;

public class FxMaterialView implements MagicalMaterialView {

  private ComboBoxSelectionView<MagicalMaterial> materialCombo;

  @Override
  public void initView(String label, AgnosticUIConfiguration<MagicalMaterial> renderer, MagicalMaterial[] materials) {
    materialCombo = new ComboBoxSelectionView<>(label, renderer);
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
}
