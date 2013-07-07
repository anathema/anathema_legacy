package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.character.main.health.HealthType;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public interface IWeaponDamageModel {

  IIntValueModel getDamageModel();
  
  IIntValueModel getMinDamageModel();
	
  HealthType getHealthType();

  void addHealthTypeChangeListener(ChangeListener listener);

  void setHealthType(HealthType healthType);
}