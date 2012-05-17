package net.sf.anathema.character.reporting.pdf.content.stats.magic;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.CostStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.HealthCostStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.MagicInfoStringBuilder;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.util.Identified;

public abstract class AbstractMagicStats<T extends IMagic> implements IMagicStats {

  public static MagicInfoStringBuilder createMagicInfoStringBuilder(IResources resources) {
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
    return new Identificate(magic.getId());
  }

  @Override
  public String getCostString(IResources resources) {
    MagicInfoStringBuilder infoBuilder = createMagicInfoStringBuilder(resources);
    return infoBuilder.createCostString(magic);
  }

  protected final T getMagic() {
    return magic;
  }
}
