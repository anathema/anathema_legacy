package net.sf.anathema.character.main.magic.model.charmtree;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.template.magic.CharmProvider;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities;

import java.text.MessageFormat;

public class MartialArtsCharmTree extends CharmTree {

  private final MartialArtsLevel standardLevel;

  public MartialArtsCharmTree(CharmProvider charmProvider, MartialArtsLevel standardLevel) {
    super(charmProvider.getMartialArtsCharms());
    this.standardLevel = standardLevel;
  }

  @Override
  public boolean isLearnable(Charm charm) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(charm);
    if (level == null) {
      String format = MessageFormat
              .format("The charm {0} is not a Martial Arts charm.\nTry naming Martial Arts as the first prerequisite trait.", charm.getId());
      throw new IllegalStateException(format);
    }
    return level.compareTo(standardLevel) <= 1;
  }
}