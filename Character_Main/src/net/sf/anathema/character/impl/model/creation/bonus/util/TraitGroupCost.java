package net.sf.anathema.character.impl.model.creation.bonus.util;

import net.sf.anathema.character.impl.model.creation.bonus.basic.ElementCreationCost;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

  public IDefaultTrait[] getTraits() {
    return group.getGroupTraits();
  }

  public int getPointsToSpend() {
    return dotsToSpend;
  }

  public void addTraitToCost(ITrait trait, ElementCreationCost cost) {
    if (!unhandledTraits.contains(trait)) {
      throw new IllegalArgumentException("Trait " + trait.getType().getId() + " not expected.");
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