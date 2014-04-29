package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponDamageProperties;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.widgets.IIntegerSpinner;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;

public class WeaponDamagePresenter {

  private final IWeaponDamageModel model;
  private final WeaponDamageView view;
  private final WeaponDamageProperties properties;

  public WeaponDamagePresenter(WeaponDamageProperties properties, IWeaponDamageModel model, WeaponDamageView view) {
    this.model = model;
    this.view = view;
    this.properties = properties;
  }

  public void initPresentation() {
    view.setDamageLabelText(properties.getDamageLabel());
    IIntegerSpinner damageSpinner = view.getDamageIntegerSpinner();
    new IntValuePresentation().initPresentation(damageSpinner, model.getDamageModel());
    damageSpinner.setMinimum(0);
    view.setObjects(HealthType.values());
    model.addHealthTypeChangeListener(new ChangeListener() {
      @Override
      public void changeOccurred() {
        updateHealthTypeInView();
      }
    });
    view.addObjectSelectionChangedListener(new ObjectValueListener<HealthType>() {
      @Override
      public void valueChanged(HealthType newValue) {
        model.setHealthType(newValue);
      }
    });
    view.setHealthTypeRenderer(properties.getHealthTypeUi());
    updateHealthTypeInView();
    view.setMinDamageLabelText(properties.getMinDamageLabel());
    IIntegerSpinner minDamageSpinner = view.getMinDamageIntegerSpinner();
    new IntValuePresentation().initPresentation(minDamageSpinner, model.getMinDamageModel());
    minDamageSpinner.setMinimum(0);
  }

  private void updateHealthTypeInView() {
    view.setSelectedObject(model.getHealthType());
  }
}
