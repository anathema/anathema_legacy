package net.sf.anathema.character.equipment.creation.presenter.stats;

import net.sf.anathema.character.equipment.creation.model.stats.IWeaponDamageModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponDamageProperties;
import net.sf.anathema.character.equipment.creation.view.WeaponDamageView;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.gui.widgets.IntegerSpinner;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;

public class WeaponDamagePresenter implements Presenter {

  private final IWeaponDamageModel model;
  private final WeaponDamageView view;
  private final WeaponDamageProperties properties;

  public WeaponDamagePresenter(Resources resources, IWeaponDamageModel model, WeaponDamageView view) {
    this.model = model;
    this.view = view;
    this.properties = new WeaponDamageProperties(resources);
  }

  @Override
  public void initPresentation() {
    view.setDamageLabelText(properties.getDamageLabel());
    IntegerSpinner damageSpinner = view.getDamageIntegerSpinner();
    new IntValuePresentation().initView(damageSpinner, model.getDamageModel());
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
    IntegerSpinner minDamageSpinner = view.getMinDamageIntegerSpinner();
    new IntValuePresentation().initView(minDamageSpinner, model.getMinDamageModel());
    minDamageSpinner.setMinimum(0);
  }

  private void updateHealthTypeInView() {
    view.setSelectedObject(model.getHealthType());
  }
}
