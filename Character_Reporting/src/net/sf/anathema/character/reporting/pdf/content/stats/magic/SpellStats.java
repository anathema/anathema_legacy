package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.disy.commons.core.util.ObjectUtilities;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.MagicSourceStringBuilder;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.lib.resources.IResources;

public class SpellStats extends AbstractMagicStats<ISpell> {

  public SpellStats(ISpell spell) {
    super(spell);
  }

  @Override
  public String getGroupName(final IResources resources) {
    return resources.getString("Sheet.Magic.Group.Sorcery"); //$NON-NLS-1$
  }

  @Override
  public String getType(final IResources resources) {
    return resources.getString(getMagic().getCircleType().getId());
  }

  @Override
  public String getDurationString(final IResources resources) {
    return "-"; //$NON-NLS-1$
  }

  @Override
  public String getSourceString(IResources resources) {
    IMagicSourceStringBuilder<ISpell> stringBuilder = new MagicSourceStringBuilder<ISpell>(resources);
    return stringBuilder.createShortSourceString(getMagic());
  }

  protected String[] getDetailKeys() {
    final String target = getMagic().getTarget();
    if (target != null) {
      return new String[]{"Spells.Target." + target}; //$NON-NLS-1$
    }
    return new String[0];
  }
  
  @Override
  public String[] getDetailStrings(final IResources resources) {
	return ArrayUtilities.transform(getDetailKeys(), String.class, new ITransformer<String, String>()
	{
	  @Override
      public String transform(String input)
	  {
	    return resources.getString(input);
	  }
	});
  }

  @Override
  public String getNameString(IResources resources) {
    return resources.getString(getMagic().getId());
  }

  @Override
  public int compareTo(IMagicStats stats) {
    if (stats.getClass() != getClass()) {
      throw new ClassCastException("Uncomparable elements.");
    }

    SpellStats spell = (SpellStats) stats;
    int r = getMagic().getCircleType().compareTo(spell.getMagic().getCircleType());
    if (r == 0) {
      r = getMagic().getId().compareTo(spell.getMagic().getId());
    }
    return r;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }
    return compareTo((SpellStats)obj) == 0;
  }

  @Override
  public int hashCode() {
    return ObjectUtilities.getHashCode(getMagic().getCircleType(), getMagic().getId());
  }
}
