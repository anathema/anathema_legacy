package net.sf.anathema.character.generic.impl.magic;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;

import java.util.Collections;
import java.util.List;

public class StaticCharmLearnArbitrator implements ICharmLearnArbitrator {
  private final List<ICharm> learnedCharms = Lists.newArrayList();

  public StaticCharmLearnArbitrator(ICharm[] learnedCharms) {
    Collections.addAll(this.learnedCharms, learnedCharms);
  }

  @Override
  public boolean isLearned(ICharm charm) {
    return learnedCharms.contains(charm);
  }
}