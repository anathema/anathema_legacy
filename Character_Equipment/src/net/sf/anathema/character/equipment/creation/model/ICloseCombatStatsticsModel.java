package net.sf.anathema.character.equipment.creation.model;

import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ICloseCombatStatsticsModel {

  public ITextualDescription getName();

  public IIntValueModel getSpeedModel();

  public IIntValueModel getAccuracyModel();

  public IIntValueModel getDefenseModel();

  public IIntValueModel getRateModel();
  
  public IWeaponDamageModel getWeaponDamageModel();
}