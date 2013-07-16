package net.sf.anathema.character.main.magic.charm.special;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.main.magic.parser.charms.special.paintolerance.PainToleranceDto;

import java.util.ArrayList;
import java.util.List;

public class StaticPainToleranceCharm extends StaticMultiLearnableCharm implements IPainToleranceCharm {

  private final List<Integer> painToleranceLevels;

  public StaticPainToleranceCharm(String charmId, PainToleranceDto dto) {
    super(charmId, dto.learnCount);
    Preconditions.checkArgument(dto.learnCount == dto.levels.size(), "Number of pain tolerance levels must equal learn count.");
    this.painToleranceLevels = new ArrayList<Integer>(dto.levels);
  }

  @Override
  public void accept(ISpecialCharmVisitor visitor) {
    super.accept(visitor);
    visitor.visitPainToleranceCharm(this);
  }

  @Override
  public int getPainToleranceLevel(int learnCount) {
    if (learnCount <= 0) {
      return 0;
    }
    return painToleranceLevels.get(learnCount - 1);
  }
}