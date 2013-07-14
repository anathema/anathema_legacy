package net.sf.anathema.hero.magic.sheet.content.stats;

import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.CostStringBuilder;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.HealthCostStringBuilder;
import net.sf.anathema.character.main.magic.model.charmtree.builder.stringbuilder.MagicInfoStringBuilder;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.magic.sheet.content.IMagicStats;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public abstract class AbstractMagicStats<T extends Magic> implements IMagicStats {

  public static MagicInfoStringBuilder createMagicInfoStringBuilder(Resources resources) {
    CostStringBuilder essenceBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.Mote");
    CostStringBuilder willpowerBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.Willpower");
    HealthCostStringBuilder healthBuilder = new HealthCostStringBuilder(resources, "CharacterSheet.Charm.HealthLevel");
    CostStringBuilder experienceBuilder = new CostStringBuilder(resources, "CharacterSheet.Charm.ExperiencePoints");
    return new MagicInfoStringBuilder(resources, essenceBuilder, willpowerBuilder, healthBuilder, experienceBuilder);
  }

  private final T magic;

  public AbstractMagicStats(T magic) {
    this.magic = magic;
  }

  @Override
  public Identifier getName() {
    return new SimpleIdentifier(magic.getId());
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
