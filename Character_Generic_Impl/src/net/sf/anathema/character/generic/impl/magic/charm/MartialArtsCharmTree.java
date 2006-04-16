package net.sf.anathema.character.generic.impl.magic.charm;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;

public class MartialArtsCharmTree extends AbstractCharmTree {

  private final MartialArtsLevel standardLevel;

  public MartialArtsCharmTree(ICharmTemplate charmTemplate, IExaltedRuleSet rules) {
    this(charmTemplate.getMartialArtsCharms(rules), charmTemplate.getMartialArtsLevel());
  }

  public MartialArtsCharmTree(ICharm[] martialArtsCharms, MartialArtsLevel standardLevel) {
    super(martialArtsCharms);
    this.standardLevel = standardLevel;
  }

  @Override
  protected boolean isLearnableCharm(ICharm charm) {
    return MartialArtsUtilities.getLevel(charm).compareTo(standardLevel) <= 1;
  }

  public ICharmGroup[] getAllNonMartialArtsCharmGroups() {
    return new ICharmGroup[0];
  }

  public ICharmGroup[] getAllMartialArtsCharmGroups() {
    return getAllCharmGroups();
  }
}