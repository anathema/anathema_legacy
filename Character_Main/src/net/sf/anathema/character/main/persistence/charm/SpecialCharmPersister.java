package net.sf.anathema.character.main.persistence.charm;

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
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

public class SpecialCharmPersister implements ISpecialCharmPersister {

  private final Map<Charm, ISpecialCharmPersister> persisterByCharm = new HashMap<>();

  public SpecialCharmPersister(ISpecialCharm[] charms, final CharmIdMap charmTree) {
    for (ISpecialCharm specialCharm : charms) {
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

  @Override
  public void saveConfiguration(Element specialElement, CharmSpecialsModel specialCharmConfiguration) {
    ISpecialCharmPersister persister = persisterByCharm.get(specialCharmConfiguration.getCharm());
    if (persister != null) {
      persister.saveConfiguration(specialElement, specialCharmConfiguration);
    }
  }

  @Override
  public void loadConfiguration(Element specialElement, CharmSpecialsModel specialCharmConfiguration) throws PersistenceException {
    ISpecialCharmPersister persister = persisterByCharm.get(specialCharmConfiguration.getCharm());
    if (persister != null) {
      persister.loadConfiguration(specialElement, specialCharmConfiguration);
    }
  }
}