package net.sf.anathema.character.generic.impl.template.magic;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;

import java.util.ArrayList;
import java.util.List;

public class CustomizableFreePicksPredicate implements Predicate<IMagic> {

  private final boolean defaultResponse;
  private final List<String> exceptionMagicIds = new ArrayList<>();
  private final List<String> exceptionGroupIds = new ArrayList<>();

  public CustomizableFreePicksPredicate(boolean defaultResponse) {
    this.defaultResponse = defaultResponse;
  }

  @Override
  public boolean apply(IMagic magic) {
    if (exceptionMagicIds.contains(magic.getId())) {
      return !defaultResponse;
    }
    if (magic instanceof ICharm && exceptionGroupIds.contains(((ICharm) magic).getGroupId())) {
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