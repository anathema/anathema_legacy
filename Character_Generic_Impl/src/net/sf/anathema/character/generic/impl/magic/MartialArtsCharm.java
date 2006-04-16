package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.IMartialArtsCharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.general.IMagicSource;

public class MartialArtsCharm extends Charm implements IMartialArtsCharm {

  public MartialArtsCharm(Charm charm) {
    super(
        charm.getCharacterType(),
        charm.getId(),
        charm.getGroupId(),
        charm.prerequisisteList,
        charm.getTemporaryCost(),
        charm.getPermanentCost(),
        charm.getComboRules(),
        charm.getDuration(),
        charm.getCharmType(),
        new IMagicSource[] { charm.getSource() });
    for (ICharmAttribute attribute : charm.getAttributes()) {
      addCharmAttribute(attribute);
    }
  }

  @Override
  public void accept(IMagicVisitor visitor) {
    visitor.visitMartialArtsCharm(this);
  }
}