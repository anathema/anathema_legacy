package net.sf.anathema.character.equipment.creation.view;

import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;

public interface IWeaponDamageView extends AdditiveView, IObjectSelectionView<HealthType> {

  IntegerSpinner getDamageIntegerSpinner();
  
  IntegerSpinner getMinDamageIntegerSpinner();

  void setHealthTypeRenderer(AgnosticUIConfiguration<HealthType> renderer);

  void setDamageLabelText(String label);
  
  void setMinDamageLabelText(String label);
}