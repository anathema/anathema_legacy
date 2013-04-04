package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.CostStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.HealthCostStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.MagicInfoStringBuilder;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

public abstract class AbstractMagicStats<T extends IMagic> implements IMagicStats {

  public static MagicInfoStringBuilder createMagicInfoStringBuilder(Resources resources) {
    CostStringBuilder essenceBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.Mote"); //$NON-NLS-1$
    CostStringBuilder willpowerBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.Willpower"); //$NON-NLS-1$
    HealthCostStringBuilder healthBuilder = new HealthCostStringBuilder(resources, "CharacterSheet.Charm.HealthLevel"); //$NON-NLS-1$
    CostStringBuilder experienceBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.ExperiencePoints"); //$NON-NLS-1$
    return new MagicInfoStringBuilder(resources, essenceBuilder, willpowerBuilder, healthBuilder, experienceBuilder);
  }

  private final T magic;

  public AbstractMagicStats(T magic) {
    this.magic = magic;
  }

  @Override
  public Identified getName() {
    return new Identifier(magic.getId());
  }

  @Override
  public String getCostString(Resources resources) {
    MagicInfoStringBuilder infoBuilder = createMagicInfoStringBuilder(resources);
    return infoBuilder.createCostString(magic);
  }

  protected final T getMagic() {
    return magic;
  }
}
