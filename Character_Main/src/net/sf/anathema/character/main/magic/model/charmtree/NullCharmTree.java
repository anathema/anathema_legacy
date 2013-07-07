package net.sf.anathema.character.main.magic.model.charmtree;

import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;
import net.sf.anathema.character.main.magic.model.charm.ICharm;

import java.util.ArrayList;
import java.util.List;

public class NullCharmTree implements ICharmTree {

  @Override
  public ICharm getCharmById(String id) {
    return null;
  }

  @Override
  public ICharm[] getAllCharms() {
    return new ICharm[0];
  }

  @Override
  public List<ICharm> getAllCharmsForGroup(String id) {
    return new ArrayList<>();
  }

  @Override
  public ICharmGroup[] getAllCharmGroups() {
    return new ICharmGroup[0];
  }

  @Override
  public boolean isLearnable(ICharm charm) {
    return false;
  }
}