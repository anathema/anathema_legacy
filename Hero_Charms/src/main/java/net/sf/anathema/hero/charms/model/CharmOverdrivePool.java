package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmAttributeList;
import net.sf.anathema.hero.spiritual.model.pool.OverdrivePool;
import net.sf.anathema.hero.experience.ExperienceModel;

public class CharmOverdrivePool implements OverdrivePool {

  private CharmsModel charms;
  private ExperienceModel experience;

  public CharmOverdrivePool(CharmsModel charms, ExperienceModel experience) {
    this.charms = charms;
    this.experience = experience;
  }

  @Override
  public int getPool() {
    int overdrive = 0;
    for (Charm charm : charms.getLearnedCharms(experience.isExperienced())) {
      if (charm.hasAttribute(CharmAttributeList.OVERDRIVE_ATTRIBUTE)) {
        int pool = 10;
        String value = charm.getAttributeValue(CharmAttributeList.OVERDRIVE_ATTRIBUTE);
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
