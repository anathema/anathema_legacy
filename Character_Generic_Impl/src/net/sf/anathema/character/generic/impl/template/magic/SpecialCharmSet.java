package net.sf.anathema.character.generic.impl.template.magic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;

public class SpecialCharmSet {

  private final List<ISpecialCharm> list = new ArrayList<ISpecialCharm>();

  public void add(ISpecialCharm... identificates) {
    for (ISpecialCharm identificate : identificates) {
      addCharm(identificate);
    }
  }

  private void addCharm(ISpecialCharm identificate) {
    for (ISpecialCharm existing : new ArrayList<ISpecialCharm>(list)) {
      if (existing.getCharmId().equals(identificate.getCharmId())) {
        list.remove(existing);
      }
    }
    list.add(identificate);
  }

  public ISpecialCharm[] toArray(ISpecialCharm[] array) {
    return list.toArray(array);
  }

  public int size() {
    return list.size();
  }

  public void addAll(Collection<ISpecialCharm> identificates) {
    for (ISpecialCharm identificate : identificates) {
      addCharm(identificate);
    }
  }
}
