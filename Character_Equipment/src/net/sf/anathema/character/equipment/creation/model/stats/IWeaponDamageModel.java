package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IWeaponDamageModel extends IIntValueModel {

  public HealthType getHealthType();

  public void addHealthTypeChangeListener(IChangeListener listener);

  public void setHealthType(HealthType healthType);
}