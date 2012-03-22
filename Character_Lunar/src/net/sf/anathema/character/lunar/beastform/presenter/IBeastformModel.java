package net.sf.anathema.character.lunar.beastform.presenter;

import net.sf.anathema.character.equipment.character.model.IEquipmentPrintModel;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.mutations.model.IMutationsModel;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface IBeastformModel extends IAdditionalModel {

  int getCharmValue();

  void addCharmLearnCountChangedListener(IIntValueChangedListener listener);

  IBeastformAttribute[] getAttributes();

  IMutationsModel getMutationModel();

  IEquipmentPrintModel getEquipmentModel();

  IBeastformAttribute getAttributeByType(AttributeType type);

  IGenericTraitCollection getBeastTraitCollection();
}