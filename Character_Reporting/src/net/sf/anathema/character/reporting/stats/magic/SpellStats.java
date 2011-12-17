package net.sf.anathema.character.reporting.stats.magic;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.SpellSourceStringBuilder;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.lib.resources.IResources;

public class SpellStats extends AbstractMagicStats<ISpell> {

  private final IExaltedEdition edition;

  public SpellStats(ISpell spell, IExaltedEdition edition) {
    super(spell);
    this.edition = edition;
  }

  public String getGroupName(final IResources resources) {
    return resources.getString("Sheet.Magic.Group.Sorcery"); //$NON-NLS-1$
  }

  public String getType(final IResources resources) {
    return resources.getString(getMagic().getCircleType().getId());
  }

  public String getDurationString(final IResources resources) {
    return "-"; //$NON-NLS-1$
  }

  public String getSourceString(IResources resources) {
    final IMagicSourceStringBuilder<ISpell> stringBuilder = new SpellSourceStringBuilder(resources, edition);
    return stringBuilder.createShortSourceString(getMagic());
  }

  public String[] getDetailKeys() {
    final String target = getMagic().getTarget();
    if (target != null) {
      return new String[] { "Spells.Target." + target }; //$NON-NLS-1$
    }
    return new String[0];
  }

  public String getNameString(IResources resources) {
    return resources.getString(getMagic().getId());
  }

  public int compareTo(IMagicStats stats) {
    if (stats instanceof SpellStats) {
      SpellStats spell = (SpellStats)stats;
      int r = getMagic().getCircleType().compareTo(spell.getMagic().getCircleType());
      if (r == 0) {
        r = getMagic().getId().compareTo(spell.getMagic().getId());
      }
      return r;
    }
    else {
      return 1;
    }
  }
}