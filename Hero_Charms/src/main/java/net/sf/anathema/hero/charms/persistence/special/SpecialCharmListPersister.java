package net.sf.anathema.hero.charms.persistence.special;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.hero.charms.model.special.multilearn.IMultiLearnableCharm;
import net.sf.anathema.hero.charms.model.special.subeffects.IMultipleEffectCharm;
import net.sf.anathema.hero.charms.model.special.oxbody.IOxBodyTechniqueCharm;
import net.sf.anathema.hero.charms.model.special.paintolerance.IPainToleranceCharm;
import net.sf.anathema.hero.charms.model.special.prerequisite.IPrerequisiteModifyingCharm;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.ISpecialCharmVisitor;
import net.sf.anathema.hero.charms.model.special.subeffects.ISubEffectCharm;
import net.sf.anathema.hero.charms.model.special.traitcap.ITraitCapModifyingCharm;
import net.sf.anathema.hero.charms.model.special.upgradable.IUpgradableCharm;
import net.sf.anathema.hero.charms.model.special.multilearn.MultiLearnCharmSpecials;
import net.sf.anathema.hero.charms.model.CharmsModel;
import net.sf.anathema.hero.charms.persistence.CharmListPto;
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

  public void saveCharmSpecials(CharmsModel charmsModel, Charm charm, CharmListPto charmPto) {
    CharmSpecialsModel charmSpecials = charmsModel.getCharmSpecialsModel(charm);
    SpecialCharmPersister specialCharmPersister = persisterByCharm.get(charm);
    if (charmSpecials == null || specialCharmPersister == null) {
      return;
    }
    CharmSpecialsPto specialPto = new CharmSpecialsPto();
    specialPto.charmId = charm.getId();
    specialCharmPersister.saveCharmSpecials(charmSpecials, specialPto);
    charmPto.charmSpecials.add(specialPto);
  }

  public void loadSpecials(CharmsModel model, Charm charm, CharmListPto pto, boolean isExperienceLearned) {
    SpecialCharmPersister specialPersister = persisterByCharm.get(charm);
    CharmSpecialsModel charmSpecials = model.getSpecialCharmConfiguration(charm.getId());
    CharmSpecialsPto charmSpecialsPto = getSpecialCharmPto(charm.getId(), pto);
    if (charmSpecialsPto != null && charmSpecials != null) {
      specialPersister.loadCharmSpecials(charmSpecials, charmSpecialsPto);
    } else if (charmSpecials instanceof MultiLearnCharmSpecials) {
      charmSpecials.learn(isExperienceLearned);
    }
  }

  private CharmSpecialsPto getSpecialCharmPto(String charmId, CharmListPto pto) {
    for (CharmSpecialsPto specialsPto : pto.charmSpecials) {
      if (specialsPto.charmId.equals(charmId)) {
        return specialsPto;
      }
    }
    return null;
  }
}
