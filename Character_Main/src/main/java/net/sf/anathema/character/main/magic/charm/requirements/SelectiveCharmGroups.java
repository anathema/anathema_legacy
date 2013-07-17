package net.sf.anathema.character.main.magic.charm.requirements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectiveCharmGroups implements Iterable<SelectiveCharmGroup> {
  private final List<SelectiveCharmGroup> selectiveCharmGroups = new ArrayList<>();

  public void add(SelectiveCharmGroup selectiveCharmGroup) {
    selectiveCharmGroups.add(selectiveCharmGroup);
  }

  @Override
  public Iterator<SelectiveCharmGroup> iterator() {
    return selectiveCharmGroups.iterator();
  }

  public boolean isEmpty() {
    return selectiveCharmGroups.isEmpty();
  }

  public List<SelectiveCharmGroup> getCombinedGroups() {
    List<SelectiveCharmGroup> combinedGroups = new ArrayList<>();
    for (SelectiveCharmGroup selectiveCharmGroup : selectiveCharmGroups) {
      if (selectiveCharmGroup.getLabel() != null) {
        combinedGroups.add(selectiveCharmGroup);
      }
    }
    return combinedGroups;
  }

  public List<SelectiveCharmGroup> getOpenGroups() {
    List<SelectiveCharmGroup> openGroups = new ArrayList<>();
    for (SelectiveCharmGroup selectiveCharmGroup : selectiveCharmGroups) {
      if (selectiveCharmGroup.getLabel() == null) {
        openGroups.add(selectiveCharmGroup);
      }
    }
    return openGroups;
  }
}
