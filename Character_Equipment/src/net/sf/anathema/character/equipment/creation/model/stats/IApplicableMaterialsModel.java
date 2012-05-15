package net.sf.anathema.character.equipment.creation.model.stats;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public interface IApplicableMaterialsModel {

  MagicalMaterial[] getValidMaterials();
	
  BooleanValueModel getSelectedModel(MagicalMaterial material);
}