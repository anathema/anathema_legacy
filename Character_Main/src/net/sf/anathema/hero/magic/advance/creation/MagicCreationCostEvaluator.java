package net.sf.anathema.hero.magic.advance.creation;

import net.sf.anathema.character.main.magic.model.magic.Magic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MagicCreationCostEvaluator {

  private List<MagicLearner> magicLearners;

  public MagicCreationCostEvaluator(List<MagicLearner> magicLearners) {
    this.magicLearners = magicLearners;
  }

  public List<Magic> compileCompleteMagicList() {
    List<Magic> completeList = new ArrayList<>();
    for(MagicLearner learner : magicLearners) {
      completeList.addAll(learner.getLearnedMagic(false));
    }

    return completeList;
  }

  public int getLearnCount(Magic magic, Set<Magic> alreadyHandledMagic) {
    MagicLearner learner = getLearnerFor(magic);
    return learner.getCreationLearnCount(magic, alreadyHandledMagic);
  }

  public int getAdditionalBonusPoints(Magic magic) {
    MagicLearner learner = getLearnerFor(magic);
    return learner.getAdditionalBonusPoints(magic);
  }

  private MagicLearner getLearnerFor(Magic magic) {
    for (MagicLearner learner : magicLearners) {
      if (learner.handlesMagic(magic)) {
        return learner;
      }
    }
    throw new IllegalStateException("Unknown magic " + magic);
  }
}
