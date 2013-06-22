package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.character.equipment.NaturalWeaponMap;
import net.sf.anathema.hero.equipment.model.EquipmentModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ListSubBoxContent;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.equipment.MaterialComposition.Fixed;
import static net.sf.anathema.character.equipment.MaterialComposition.Variable;

public class PossessionsContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private IGenericCharacter character;

  public PossessionsContent(Resources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Possessions";
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printPossessions = new ArrayList<>();
    IEquipmentItem[] equipmentItems = getEquipmentItems();
    for (IEquipmentItem item : equipmentItems) {
      if (isInArsenalOrPanopoly(item)) {
        continue;
      }
      String possession = item.getTitle();
      if (item.getMaterialComposition() == Fixed || item.getMaterialComposition() == Variable) {
        possession += " (" + item.getMaterial().getId() + ")";
      }
      printPossessions.add(possession);
    }
    return printPossessions;
  }

  private boolean isInArsenalOrPanopoly(IEquipmentItem item) {
    for (IEquipmentStats stats : item.getStats()) {
      if (stats.representsItemForUseInCombat() && item.isPrintEnabled(stats)) {
        return true;
      }
    }
    return false;
  }

  private IEquipmentItem[] getEquipmentItems() {
    EquipmentModel model = (EquipmentModel) character.getAdditionalModel(NaturalWeaponMap.ID);
    return model.getEquipmentItems();
  }

}
