package net.sf.anathema.character.abyssal.equipment;

import net.sf.anathema.character.equipment.ItemCost;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.MaterialComposition;
import net.sf.anathema.character.equipment.impl.character.model.RegisteredNaturalWeapon;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

import static net.sf.anathema.character.generic.type.CharacterType.ABYSSAL;

@RegisteredNaturalWeapon(characterType = ABYSSAL)
public class FangTemplate implements IEquipmentTemplate {

  private static final FangStats FANG_STATS = new FangStats();
  private static final String FANG = "Abyssal.Fangs"; //$NON-NLS-1$

  @Override
  public MaterialComposition getComposition() {
    return MaterialComposition.None;
  }

  @Override
  public String getDescription() {
    return FANG;
  }

  @Override
  public MagicalMaterial getMaterial() {
    return null;
  }

  @Override
  public String getName() {
    return FANG;
  }

  @Override
  public IEquipmentStats[] getStats() {
    return new IEquipmentStats[]{FANG_STATS};
  }
  
  @Override
  public ItemCost getCost() {
	return null;
  }
}