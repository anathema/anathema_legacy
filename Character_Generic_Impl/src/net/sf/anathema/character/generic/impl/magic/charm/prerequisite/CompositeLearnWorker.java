package net.sf.anathema.character.generic.impl.magic.charm.prerequisite;

import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;

public class CompositeLearnWorker implements ICharmLearnWorker {

  private final ICharmLearnArbitrator learnArbitrator;
  private final Set<Charm> forgottenCharm = new HashSet<Charm>();

  public CompositeLearnWorker(ICharmLearnArbitrator learnArbitrator) {
    this.learnArbitrator = learnArbitrator;
  }

  public boolean isLearned(ICharm charm) {
    return learnArbitrator.isLearned(charm) && !forgottenCharm.contains(charm);
  }

  public void forget(Charm charm) {
    forgottenCharm.add(charm);
  }

  public Set<ICharm> getForgottenCharms() {
    return new HashSet<ICharm>(forgottenCharm);
  }
}