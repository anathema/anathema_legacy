package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ListSubBoxContent;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class PossessionsContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private IGenericCharacter character;

  public PossessionsContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Possessions"; //$NON-NLS-1$
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printPossessions = new ArrayList<String>();
    IEquipmentItem[] equipmentItems = getEquipmentItems();
    for (int index = 0; index < equipmentItems.length; index++) {
      IEquipmentItem item = equipmentItems[index];
      if (isInArsenalOrPanopoly(item)) {
        continue;
      }
      String possession = item.getTitle();
      if (item.getMaterialComposition() == MaterialComposition.Fixed ||
    	  item.getMaterialComposition() == MaterialComposition.Variable) {
    	  possession += " (" + item.getMaterial().getId() + ")";
      }
      printPossessions.add(possession);
    }
    return printPossessions;
  }
  
  private boolean isInArsenalOrPanopoly(IEquipmentItem item) {
	  for (IEquipmentStats stats : item.getStats()) {
		  if (stats.representsItemForUseInCombat() && item.isPrintEnabled(stats))
			  return true;
	  }
	  return false;
  }

  private IEquipmentItem[] getEquipmentItems() {
    IEquipmentAdditionalModel model = (IEquipmentAdditionalModel) character.getAdditionalModel(IEquipmentAdditionalModelTemplate.ID);
    return model.getEquipmentItems();
  }

}
