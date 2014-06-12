package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.content.ListSubBoxContent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static net.sf.anathema.equipment.core.MaterialComposition.Fixed;
import static net.sf.anathema.equipment.core.MaterialComposition.Variable;

public class PossessionsContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private Hero hero;

  public PossessionsContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Possessions";
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printPossessions = new ArrayList<>();
    for (IEquipmentItem item : getEquipmentItems()) {
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

  private Collection<IEquipmentItem> getEquipmentItems() {
    EquipmentModel model = EquipmentModelFetcher.fetch(hero);
    return model.getEquipmentItems();
  }

}
