package net.sf.anathema.character.impl.model.creation.bonus.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.impl.model.creation.bonus.basic.ElementCreationCost;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.favorable.IFavorableTrait;

public class TraitGroupCost {

  private final int dotsToSpend;
  private final List<ITrait> unhandledTraits;
  private final TraitGroup group;
  private int bonusPointsSpent = 0;
  private int dotsSpent = 0;

  public TraitGroupCost(TraitGroup group, int dotsToSpend) {
    this.group = group;
    this.dotsToSpend = dotsToSpend;
    this.unhandledTraits = new ArrayList<ITrait>(Arrays.asList(group.getGroupTraits()));
  }

  public TraitGroup getGroup() {
    return group;
  }

  public IFavorableTrait[] getTraits() {
    return group.getGroupTraits();
  }

  public int getPointsToSpend() {
    return dotsToSpend;
  }

  public void addTraitToCost(ITrait trait, ElementCreationCost cost) {
    if (!unhandledTraits.contains(trait)) {
      throw new IllegalArgumentException("Trait " + trait.getType().getId() + " not expected."); //$NON-NLS-1$//$NON-NLS-2$
    }
    dotsSpent += cost.getDotsSpent();
    bonusPointsSpent += cost.getBonusPointsSpent();
    unhandledTraits.remove(trait);
  }

  public int getDotsSpent() {
    return dotsSpent;
  }

  public int getBonusPointsSpent() {
    return bonusPointsSpent;
  }
}