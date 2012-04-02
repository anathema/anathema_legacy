package net.sf.anathema.character.equipment.impl.creation.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.creation.model.stats.IApplicableMaterialsModel;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public class ApplicableMaterialsModel implements IApplicableMaterialsModel {

  private final Map<MagicalMaterial, BooleanValueModel> selectedMap = new EnumMap<MagicalMaterial, BooleanValueModel>(
      MagicalMaterial.class);

  public ApplicableMaterialsModel() {
    for (MagicalMaterial material : MagicalMaterial.values())
      selectedMap.put(material, new BooleanValueModel(true));
  }
  
  @Override
  public MagicalMaterial[] getValidMaterials()
  {
	  List<MagicalMaterial> materials = new ArrayList<MagicalMaterial>();
	  for (MagicalMaterial material : selectedMap.keySet())
		  if (selectedMap.get(material).getValue())
			  materials.add(material);
	  MagicalMaterial[] array = new MagicalMaterial[materials.size()]; 
	  return materials.toArray(array);
  }

  @Override
  public BooleanValueModel getSelectedModel(MagicalMaterial material) {
    return selectedMap.get(material);
  }
}