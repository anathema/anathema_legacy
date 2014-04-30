package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.control.ChangeListener;

public interface IWeaponDamageModel {

  IIntValueModel getDamageModel();
  
  IIntValueModel getMinDamageModel();
	
  HealthType getHealthType();

  void addHealthTypeChangeListener(ChangeListener listener);

  void setHealthType(HealthType healthType);
}