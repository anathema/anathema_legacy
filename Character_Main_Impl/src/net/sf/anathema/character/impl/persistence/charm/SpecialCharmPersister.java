package net.sf.anathema.character.impl.persistence.charm;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IMultipleEffectCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class SpecialCharmPersister implements ISpecialCharmPersister {

  private final Map<ICharm, ISpecialCharmPersister> persisterByCharm = new HashMap<ICharm, ISpecialCharmPersister>();

  public SpecialCharmPersister(ISpecialCharm[] charms, final ICharmIdMap charmTree) {
    for (ISpecialCharm specialCharm : charms) {
      specialCharm.accept(new ISpecialCharmVisitor() {
        public void visitMultiLearnableCharm(IMultiLearnableCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmId(), charmTree), new MultiLearnCharmPersister());
        }

        public void visitOxBodyTechnique(IOxBodyTechniqueCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmId(), charmTree), new OxBodyTechniquePersister());
        }

        public void visitPainToleranceCharm(IPainToleranceCharm charm) {
          // Nothing to do
        }

        public void visitSubeffectCharm(ISubeffectCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmId(), charmTree), new MultipleEffectCharmPersister());
        }

        public void visitMultipleEffectCharm(IMultipleEffectCharm charm) {
          persisterByCharm.put(getCharm(charm.getCharmId(), charmTree), new MultipleEffectCharmPersister());
        }
      });
    }
  }

  private ICharm getCharm(String charmId, ICharmIdMap charmTree) {
    return charmTree.getCharmById(charmId);
  }

  public void saveConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration) {
    ISpecialCharmPersister persister = persisterByCharm.get(specialCharmConfiguration.getCharm());
    persister.saveConfiguration(specialElement, specialCharmConfiguration);
  }

  public void loadConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration)
      throws PersistenceException {
    ISpecialCharmPersister persister = persisterByCharm.get(specialCharmConfiguration.getCharm());
    persister.loadConfiguration(specialElement, specialCharmConfiguration);
  }
}