package net.sf.anathema.character.equipment.character.model;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterDataProvider;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;

public interface IEquipmentAdditionalModel extends IAdditionalModel, IEquipmentItemCollection {

  public MagicalMaterial getDefaultMaterial();
  
  public MaterialComposition getMaterialComposition(String templateId);

  public MagicalMaterial getMagicalMaterial(String templateId);
  
  public int getTotalAttunementCost();

  public IEquipmentPrintModel getPrintModel();
  
  public IEquipmentCharacterDataProvider getCharacterDataProvider();
  
  public void addCharacterChangedListener(ICharacterChangeListener listener);

  public void refreshItems();
}