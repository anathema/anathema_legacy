package net.sf.anathema.cascades.presenter;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.presenter.charm.AbstractCharmTypes;
import net.sf.anathema.lib.util.IIdentificate;

import java.util.List;

public class CascadeCharmTypes extends AbstractCharmTypes {
  @Override
  protected List<IIdentificate> getCurrentCharacterTypes() {
    CharacterType[] characterTypes = CharacterType.values();
    return Lists.<IIdentificate>newArrayList(characterTypes);
  }
}