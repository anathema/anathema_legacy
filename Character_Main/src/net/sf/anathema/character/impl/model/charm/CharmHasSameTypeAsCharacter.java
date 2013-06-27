package net.sf.anathema.character.impl.model.charm;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.magic.ICharm;

public class CharmHasSameTypeAsCharacter implements Predicate<ICharm> {
  private ICharacterModelContext context;

  public CharmHasSameTypeAsCharacter(ICharacterModelContext context) {
    this.context = context;
  }

  @Override
  public boolean apply(ICharm charm) {
    return isCharmForCharactersOwnType(charm);
  }


  private boolean isCharmForCharactersOwnType(ICharm charm) {
    IBasicCharacterData basicCharacterContext = context.getBasicCharacterContext();
    return basicCharacterContext.getCharacterType().equals(charm.getCharacterType());
  }
}