package net.sf.anathema.character.magic.charm;

import java.util.HashSet;
import java.util.Set;

import net.sf.anathema.character.magic.basic.attribute.MagicAttribute;

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
  public boolean hasLearnedThresholdCharmsWithKeyword(MagicAttribute attribute,
  		int threshold) {
  	return learnArbitrator.hasLearnedThresholdCharmsWithKeyword(attribute, threshold);
  }

  @Override
  public void forget(CharmImpl charm) {
    forgottenCharm.add(charm);
  }

  public Set<Charm> getForgottenCharms() {
    return new HashSet<Charm>(forgottenCharm);
  }
}