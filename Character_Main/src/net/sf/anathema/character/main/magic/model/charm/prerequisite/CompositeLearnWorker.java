package net.sf.anathema.character.main.magic.model.charm.prerequisite;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmImpl;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnArbitrator;

import java.util.HashSet;
import java.util.Set;

public class CompositeLearnWorker implements ICharmLearnWorker {

  private final ICharmLearnArbitrator learnArbitrator;
  private final Set<CharmImpl> forgottenCharm = new HashSet<>();

  public CompositeLearnWorker(ICharmLearnArbitrator learnArbitrator) {
    this.learnArbitrator = learnArbitrator;
  }

  @Override
  public boolean isLearned(Charm charm) {
    return learnArbitrator.isLearned(charm) && !forgottenCharm.contains(charm);
  }

  @Override
  public void forget(CharmImpl charm) {
    forgottenCharm.add(charm);
  }

  public Set<Charm> getForgottenCharms() {
    return new HashSet<Charm>(forgottenCharm);
  }
}