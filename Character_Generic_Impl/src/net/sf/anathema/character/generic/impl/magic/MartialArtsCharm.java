package net.sf.anathema.character.generic.impl.magic;

import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.IMartialArtsCharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.general.IMagicSource;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class MartialArtsCharm extends Charm implements IMartialArtsCharm {

  public static final String ALLOWS_CELESTIAL_ATTRIBUTE = "AllowsCelestial"; //$NON-NLS-1$
  public static final IIdentificate UNRESTRICTED_ATTRIBUTE = new Identificate("Unrestricted"); //$NON-NLS-1$
  public static final IIdentificate NO_STYLE_ATTRIBUTE = new Identificate("NoStyle"); //$NON-NLS-1$
  private final MartialArtsLevel level;

  public MartialArtsCharm(Charm charm, MartialArtsLevel level) {
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
    this.level = level;
    for (ICharmAttribute attribute : charm.getAttributes()) {
      addCharmAttribute(attribute);
    }
  }

  public MartialArtsLevel getLevel() {
    return level;
  }

  @Override
  public void accept(IMagicVisitor visitor) {
    visitor.visitMartialArtsCharm(this);
  }
}