package net.sf.anathema.character.main.magic.model.charm.prerequisite;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnArbitrator;

import java.util.HashSet;
import java.util.Set;

public class CompositeLearnWorker implements ICharmLearnWorker {

  private final ICharmLearnArbitrator learnArbitrator;
  private final Set<Charm> forgottenCharm = new HashSet<>();

  public CompositeLearnWorker(ICharmLearnArbitrator learnArbitrator) {
    this.learnArbitrator = learnArbitrator;
  }

  @Override
  public boolean isLearned(ICharm charm) {
    return learnArbitrator.isLearned(charm) && !forgottenCharm.contains(charm);
  }

  @Override
  public void forget(Charm charm) {
    forgottenCharm.add(charm);
  }

  public Set<ICharm> getForgottenCharms() {
    return new HashSet<ICharm>(forgottenCharm);
  }
}