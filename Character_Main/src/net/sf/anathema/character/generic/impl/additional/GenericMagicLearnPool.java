package net.sf.anathema.character.generic.impl.additional;

import net.sf.anathema.character.generic.additionalrules.IAdditionalMagicLearnPool;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericMagicLearnPool implements IAdditionalMagicLearnPool {

  private final boolean defaultResponse;
  private final List<String> exceptionIds = new ArrayList<>();
  private final Map<CircleType, Integer> typesByMinimumValue = new HashMap<>();
  private CircleType maximumCircle = CircleType.Terrestrial;

  public GenericMagicLearnPool(boolean defaultResponse) {
    this.defaultResponse = defaultResponse;
  }

  @Override
  public int getAdditionalMagicCount(IGenericTraitCollection traitCollection) {
    return 0;
  }

  @Override
  public boolean isAllowedFor(final IGenericTraitCollection traitCollection, IMagic magic) {
    final boolean[] isAllowed = new boolean[1];
    magic.accept(new IMagicVisitor() {
      @Override
      public void visitSpell(ISpell spell) {
        CircleType type = spell.getCircleType();
        if (isSpellCircleGreaterThanMaximumCircle(type)) {
          isAllowed[0] = false;
          return;
        }
        if (exceptionIds.contains(spell.getId())) {
          isAllowed[0] = !defaultResponse;
        } else {
          isAllowed[0] = defaultResponse;
        }
      }

      @Override
      public void visitCharm(ICharm charm) {
        isAllowed[0] = false;
      }
    });
    return isAllowed[0];
  }

  public void addIdException(String attributeValue) {
    exceptionIds.add(attributeValue);
  }

  public void setMaximumCircle(CircleType type) {
    this.maximumCircle = type;
  }

  public void attachCondition(CircleType minimumType, int minimumBackgroundValue) {
    for (CircleType type : CircleType.values()) {
      if (type.compareTo(minimumType) >= 0) {
        typesByMinimumValue.put(type, minimumBackgroundValue);
      }
    }
  }

  private boolean isSpellCircleGreaterThanMaximumCircle(CircleType type) {
    return !ArrayUtils.contains(type.getComparableCircles(), maximumCircle) || (maximumCircle.compareTo(type) < 0);
  }
}