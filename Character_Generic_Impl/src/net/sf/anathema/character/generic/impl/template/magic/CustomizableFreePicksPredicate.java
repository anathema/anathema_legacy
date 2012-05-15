package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.util.IPredicate;

import java.util.ArrayList;
import java.util.List;

public class CustomizableFreePicksPredicate implements IPredicate<IMagic> {

  private final boolean defaultResponse;
  private final List<String> exceptionMagicIds = new ArrayList<String>();
  private final List<String> exceptionGroupIds = new ArrayList<String>();

  public CustomizableFreePicksPredicate(boolean defaultResponse) {
    this.defaultResponse = defaultResponse;
  }

  @Override
  public boolean evaluate(IMagic magic) {
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