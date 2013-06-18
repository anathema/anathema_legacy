package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IExtendedCharmData;
import net.sf.anathema.character.main.model.essencepool.OverdrivePool;
import net.sf.anathema.character.main.model.experience.ExperienceModel;

public class CharmOverdrivePool implements OverdrivePool {

  private CharmConfiguration charms;
  private ExperienceModel experience;

  public CharmOverdrivePool(CharmConfiguration charms, ExperienceModel experience) {
    this.charms = charms;
    this.experience = experience;
  }

  @Override
  public int getPool() {
    int overdrive = 0;
    for (ICharm charm : charms.getLearnedCharms(experience.isExperienced())) {
      if (charm.hasAttribute(IExtendedCharmData.OVERDRIVE_ATTRIBUTE)) {
        int pool = 10;
        String value = charm.getAttributeValue(IExtendedCharmData.OVERDRIVE_ATTRIBUTE);
        if (value != null) {
          try {
            pool = Integer.valueOf(value);
          } catch (NumberFormatException e) {
            System.err.println("WARNING: could not parse Overdrive value for charm " + charm.getId() + "; ignoring keyword");
            continue;
          }
        }
        overdrive += pool;
      }
    }
    return overdrive;
  }
}
