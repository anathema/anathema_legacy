package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.lunar.beastform.model.IBeastformGroupCost;
import net.sf.anathema.character.lunar.beastform.model.gift.IGiftModel;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface IBeastformModel extends IAdditionalModel {

  public int getCharmValue();

  public void setCharmLearnCount(int newValue);

  public void addCharmLearnCountChangedListener(IIntValueChangedListener listener);

  public IBeastformAttribute[] getAttributes();

  public IGiftModel getGiftModel();
  
  public IMutationsModel getMutationModel();

  public IEquipmentPrintModel getEquipmentModel();

  public IBeastformGroupCost getAttributeCostModel();
  
  public IBeastformAttribute getAttributeByType(AttributeType type);
  
  public IGenericTraitCollection getBeastTraitCollection();
}