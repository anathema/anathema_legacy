package net.sf.anathema.hero.charms.persistence.special;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.special.IMultiLearnableCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IMultipleEffectCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IPainToleranceCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IPrerequisiteModifyingCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmVisitor;
import net.sf.anathema.character.main.magic.model.charm.special.ISubEffectCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ITraitCapModifyingCharm;
import net.sf.anathema.character.main.magic.model.charm.special.IUpgradableCharm;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.persistence.CharmPto;
import net.sf.anathema.hero.charms.persistence.special.effect.MultipleEffectCharmPersister;
import net.sf.anathema.hero.charms.persistence.special.learn.MultiLearnCharmPersister;
import net.sf.anathema.hero.charms.persistence.special.oxbody.OxBodyTechniquePersister;
import net.sf.anathema.hero.charms.persistence.special.traitcap.TraitCapModifyingCharmPersister;

import java.util.HashMap;
import java.util.Map;

public class SpecialCharmListPersister {

  private final Map<Charm, SpecialCharmPersister> persisterByCharm = new HashMap<>();

  public SpecialCharmListPersister(CharmsModel model) {
    final CharmIdMap charmTree = model.getCharmIdMap();
    for (ISpecialCharm specialCharm : model.getSpecialCharms()) {
      specialCharm.accept(new ISpecialCharmVisitor() {
        @Override
        public void visitMultiLearnableCharm(IMultiLearnableCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmId(), charmTree), new MultiLearnCharmPersister());
        }

        @Override
        public void visitOxBodyTechnique(IOxBodyTechniqueCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmId(), charmTree), new OxBodyTechniquePersister());
        }

        @Override
        public void visitPainToleranceCharm(IPainToleranceCharm charm) {
          // Nothing to do
        }

        @Override
        public void visitSubEffectCharm(ISubEffectCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmId(), charmTree), new MultipleEffectCharmPersister());
        }

        @Override
        public void visitMultipleEffectCharm(IMultipleEffectCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmId(), charmTree), new MultipleEffectCharmPersister());
        }

        @Override
        public void visitUpgradableCharm(IUpgradableCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmId(), charmTree), new MultipleEffectCharmPersister());
        }

        @Override
        public void visitPrerequisiteModifyingCharm(IPrerequisiteModifyingCharm charm) {
          // Nothing to do
        }

        @Override
        public void visitTraitCapModifyingCharm(ITraitCapModifyingCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmId(), charmTree), new TraitCapModifyingCharmPersister());
        }
      });
    }
  }

  private Charm getCharm(String charmId, CharmIdMap charmTree) {
    return charmTree.getCharmById(charmId);
  }

  public void saveCharmSpecials(CharmsModel charmsModel, Charm charm, CharmPto charmPto) {
    CharmSpecialsModel charmSpecials = charmsModel.getCharmSpecialsModel(charm);
    SpecialCharmPersister specialCharmPersister = persisterByCharm.get(charm);
    if (charmSpecials == null || specialCharmPersister == null) {
      return;
    }
    charmPto.special = new SpecialCharmPto();
    specialCharmPersister.saveCharmSpecials(charmSpecials, charmPto.special);
  }
}
