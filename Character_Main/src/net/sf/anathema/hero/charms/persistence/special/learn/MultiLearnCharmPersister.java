package net.sf.anathema.hero.charms.persistence.special.learn;

import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.special.MultiLearnCharmSpecials;
import net.sf.anathema.hero.charms.persistence.special.SpecialCharmPersister;
import net.sf.anathema.hero.charms.persistence.special.SpecialCharmPto;

public class MultiLearnCharmPersister implements SpecialCharmPersister {

  @Override
  public void saveCharmSpecials(CharmSpecialsModel charmSpecials, SpecialCharmPto charmPto) {
    MultiLearnCharmSpecials multiLearn = (MultiLearnCharmSpecials) charmSpecials;
    charmPto.multiLearn = createMultiLearnPto(multiLearn);
  }

  private MultiLearnPto createMultiLearnPto(MultiLearnCharmSpecials multiLearn) {
    MultiLearnPto multiLearnPto = new MultiLearnPto();
    multiLearnPto.learnCount.creationValue = multiLearn.getCategory().getCreationValue();
    if (multiLearn.getCategory().getExperiencedValue() > 0) {
      multiLearnPto.learnCount.experienceValue = multiLearn.getCategory().getExperiencedValue();
    }
    return multiLearnPto;
  }
}
