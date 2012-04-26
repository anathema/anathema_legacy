package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IWeaponDamageModel {

  public IIntValueModel getDamageModel();
  
  public IIntValueModel getMinDamageModel();
	
  public HealthType getHealthType();

  public void addHealthTypeChangeListener(IChangeListener listener);

  public void setHealthType(HealthType healthType);
}