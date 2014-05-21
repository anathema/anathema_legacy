package net.sf.anathema.hero.template.magic;

import com.google.common.base.Predicate;
import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.magic.basic.Magic;

import java.util.ArrayList;
import java.util.List;

public class CustomizableFreePicksPredicate implements Predicate<Magic> {

  private final boolean defaultResponse;
  private final List<String> exceptionMagicIds = new ArrayList<>();
  private final List<String> exceptionGroupIds = new ArrayList<>();

  public CustomizableFreePicksPredicate(boolean defaultResponse) {
    this.defaultResponse = defaultResponse;
  }

  @Override
  public boolean apply(Magic magic) {
    if (exceptionMagicIds.contains(magic.getId())) {
      return !defaultResponse;
    }
    if (magic instanceof Charm && exceptionGroupIds.contains(((Charm) magic).getGroupId())) {
      return !defaultResponse;
    }
    return defaultResponse;
  }

  public void addIdException(String id) {
    exceptionMagicIds.add(id);
  }

  public void addCharmGroupException(String groupId) {
    exceptionGroupIds.add(groupId);
  }
}