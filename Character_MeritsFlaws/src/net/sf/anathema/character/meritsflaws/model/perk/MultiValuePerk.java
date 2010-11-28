package net.sf.anathema.character.meritsflaws.model.perk;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.library.quality.model.Quality;
import net.sf.anathema.character.meritsflaws.model.perk.cost.IFixedPerkCost;
import net.sf.anathema.character.meritsflaws.model.perk.cost.IPerkCostModifier;
import net.sf.anathema.character.meritsflaws.presenter.IPerkVisitor;

public class MultiValuePerk extends Quality implements IPerk {

  private final int[] pointValues;
  private final PerkCategory category;
  private final ICharacterType[] legalTypes;
  private final List<IPerkCostModifier> modifiers = new ArrayList<IPerkCostModifier>();
  private final List<IFixedPerkCost> fixedCosts = new ArrayList<IFixedPerkCost>();

  public MultiValuePerk(PerkType type, PerkCategory category, String id, int[] pointValues, ICharacterType[] legalTypes) {
    super(id, type);
    this.category = category;
    this.pointValues = pointValues;
    this.legalTypes = legalTypes;    
  }

  public MultiValuePerk(PerkType type, PerkCategory category, String id, int[] pointValues) {
    this(type, category, id, pointValues, CharacterType.values());
  }

  public MultiValuePerk(PerkType type, PerkCategory category, String id, int value) {
    this(type, category, id, new int[] { value });
  }

  public Integer[] getPointValues(IBasicCharacterData characterData) {
    List<Integer> costList = new ArrayList<Integer>();
    for (int value : getBaseCosts(characterData)) {
      int currentCost = value;
      for (IPerkCostModifier modifier : modifiers) {
        currentCost = modifier.getModifiedCost(characterData, currentCost);
      }
      costList.add(currentCost);
    }
    return costList.toArray(new Integer[costList.size()]);
  }

  private int[] getBaseCosts(IBasicCharacterData characterData) {
    for (IFixedPerkCost cost : fixedCosts) {
      int[] modifiedCost = cost.getModifiedCost(characterData);
      if (modifiedCost.length > 0) {
        return modifiedCost;
      }
    }
    return pointValues;
  }

  public void accept(IPerkVisitor visitor) {
    visitor.visitMultiValuePerk(this);
  }

  public boolean isLegalFor(ICharacterType characterType) {
    return ArrayUtilities.contains(legalTypes, characterType);
  }

  public void setSpecialFixedCost(IFixedPerkCost cost) {
    fixedCosts.add(cost);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof MultiValuePerk)) {
      return false;
    }
    MultiValuePerk multiValuePerk = (MultiValuePerk) obj;
    return super.equals(obj) && multiValuePerk.category == this.category;
  }

  @Override
  public int hashCode() {
    return super.hashCode() + category.hashCode();
  }

  public PerkCategory getCategory() {
    return category;
  }
}