package net.sf.anathema.hero.sheet.pdf.content.stats;

import net.sf.anathema.character.main.equipment.ICharacterStatsModifiers;

import java.util.ArrayList;
import java.util.List;

public class AggregatedStatsModifiers implements ICharacterStatsModifiers {
  private final List<ICharacterStatsModifiers> modifiers = new ArrayList<>();

  @Override
  public int getMobilityPenalty() {
    int mod = 0;
    for (ICharacterStatsModifiers modifier : modifiers) {
      mod += modifier.getMobilityPenalty();
    }
    return mod;
  }

  @Override
  public int getDDVPoolMod() {
    int mod = 0;
    for (ICharacterStatsModifiers modifier : modifiers) {
      mod += modifier.getDDVPoolMod();
    }
    return mod;
  }

  @Override
  public int getMDDVPoolMod() {
    int mod = 0;
    for (ICharacterStatsModifiers modifier : modifiers) {
      mod += modifier.getMDDVPoolMod();
    }
    return mod;
  }

  @Override
  public int getMPDVPoolMod() {
    int mod = 0;
    for (ICharacterStatsModifiers modifier : modifiers) {
      mod += modifier.getMPDVPoolMod();
    }
    return mod;
  }

  @Override
  public int getJoinBattleMod() {
    int mod = 0;
    for (ICharacterStatsModifiers modifier : modifiers) {
      mod += modifier.getJoinBattleMod();
    }
    return mod;
  }

  @Override
  public int getJoinDebateMod() {
    int mod = 0;
    for (ICharacterStatsModifiers modifier : modifiers) {
      mod += modifier.getJoinDebateMod();
    }
    return mod;
  }

  @Override
  public int getJoinWarMod() {
    int mod = 0;
    for (ICharacterStatsModifiers modifier : modifiers) {
      mod += modifier.getJoinWarMod();
    }
    return mod;
  }

  public void add(ICharacterStatsModifiers statsModifiers) {
    modifiers.add(statsModifiers);
  }
}
