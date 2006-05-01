package net.sf.anathema.character.equipment.impl.creation.model;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.workflow.intvalue.RangedIntValueModel;

public class WeaponDamageModel extends RangedIntValueModel {

  private final ChangeControl changeControl = new ChangeControl();
  private HealthType healthType = HealthType.Lethal;

  public WeaponDamageModel() {
    super(new Range(0, Integer.MAX_VALUE));
  }

  public HealthType getHealthType() {
    return healthType;
  }
  
  public void addHealthTypeChangeListener(IChangeListener listener) {
    changeControl.addChangeListener(listener);
  }
  
  public void setHealthType(HealthType healthType) {
    if (this.healthType == healthType) {
      return;
    }
    this.healthType = healthType;
    changeControl.fireChangedEvent();
  }
}