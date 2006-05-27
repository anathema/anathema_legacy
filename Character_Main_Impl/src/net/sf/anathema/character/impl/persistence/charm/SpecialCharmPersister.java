package net.sf.anathema.character.impl.persistence.charm;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.character.generic.magic.charms.special.IMultiLearnableCharm;
import net.sf.anathema.character.generic.magic.charms.special.IOxBodyTechniqueCharm;
import net.sf.anathema.character.generic.magic.charms.special.IPainToleranceCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmVisitor;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffectCharm;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;

public class SpecialCharmPersister implements ISpecialCharmPersister {

  private final Map<ICharm, ISpecialCharmPersister> persisterByCharmId = new HashMap<ICharm, ISpecialCharmPersister>();

  public SpecialCharmPersister(ISpecialCharm[] charms, final ICharmTree charmTree) {
    for (ISpecialCharm specialCharm : charms) {
      specialCharm.accept(new ISpecialCharmVisitor() {
        public void visitMultiLearnableCharm(IMultiLearnableCharm charm) {
          persisterByCharmId.put(getCharm(charm.getCharmId(), charmTree), new MultiLearnCharmPersister());
        }

        public void visitOxBodyTechnique(IOxBodyTechniqueCharm charm) {
          persisterByCharmId.put(getCharm(charm.getCharmId(), charmTree), new OxBodyTechniquePersister());
        }

        public void visitPainToleranceCharm(IPainToleranceCharm charm) {
          // Nothing to do
        }

        public void visitSubeffectCharm(ISubeffectCharm charm) {
          persisterByCharmId.put(getCharm(charm.getCharmId(), charmTree), new SubeffectCharmPersister());
        }
      });
    }
  }

  private ICharm getCharm(String charmId, ICharmTree charmTree) {
    return charmTree.getCharmByID(charmId);
  }

  public void saveConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration) {
    ISpecialCharmPersister persister = persisterByCharmId.get(specialCharmConfiguration.getCharm());
    persister.saveConfiguration(specialElement, specialCharmConfiguration);
  }

  public void loadConfiguration(Element specialElement, ISpecialCharmConfiguration specialCharmConfiguration)
      throws PersistenceException {
    ISpecialCharmPersister persister = persisterByCharmId.get(specialCharmConfiguration.getCharm());
    persister.loadConfiguration(specialElement, specialCharmConfiguration);
  }
}