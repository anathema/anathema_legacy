package net.sf.anathema.character.main.magic.charms;

import net.sf.anathema.character.main.magic.MartialArtsUtilities;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;

import java.text.MessageFormat;

public class MartialArtsCharmTree extends CharmTree {

  private final MartialArtsLevel standardLevel;

  public MartialArtsCharmTree(ICharmTemplate charmTemplate) {
    super(charmTemplate.getMartialArtsCharms());
    this.standardLevel = charmTemplate.getMartialArtsRules().getStandardLevel();
  }

  @Override
  public boolean isLearnable(ICharm charm) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(charm);
    if (level == null) {
      String format = MessageFormat
              .format("The charm {0} is not a Martial Arts charm.\nTry naming Martial Arts as the first prerequisite trait.", charm.getId());
      throw new IllegalStateException(format);
    }
    return level.compareTo(standardLevel) <= 1;
  }
}