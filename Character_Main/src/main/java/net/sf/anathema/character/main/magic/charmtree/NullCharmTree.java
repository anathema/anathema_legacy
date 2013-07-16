package net.sf.anathema.character.main.magic.charmtree;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmGroup;

import java.util.ArrayList;
import java.util.List;

public class NullCharmTree implements ICharmTree {

  @Override
  public Charm getCharmById(String id) {
    return null;
  }

  @Override
  public Charm[] getAllCharms() {
    return new Charm[0];
  }

  @Override
  public List<Charm> getAllCharmsForGroup(String id) {
    return new ArrayList<>();
  }

  @Override
  public ICharmGroup[] getAllCharmGroups() {
    return new ICharmGroup[0];
  }

  @Override
  public boolean isLearnable(Charm charm) {
    return false;
  }
}