package net.sf.anathema.character.generic.impl.magic.charm;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;

public class MartialArtsCharmTree extends AbstractCharmTree<ICharm> {

  private final ICharm[] martialArtsCharms;
  private final Map<String, ICharm> martialArtsById = new HashMap<String, ICharm>();
  private final MartialArtsLevel standardLevel;

  public MartialArtsCharmTree(ICharmTemplate charmTemplate, IExaltedRuleSet rules) {
    this(charmTemplate.getMartialArtsCharms(rules), charmTemplate.getMartialArtsLevel());
  }

  public MartialArtsCharmTree(ICharm[] martialArtsCharms, MartialArtsLevel standardLevel) {
    this.martialArtsCharms = martialArtsCharms;
    this.standardLevel = standardLevel;
    for (ICharm charm : martialArtsCharms) {
      martialArtsById.put(charm.getId(), charm);
    }
  }

  public ICharm[] getAllCharms() {
    return martialArtsCharms;
  }

  public ICharm getCharmByID(String id) {
    return martialArtsById.get(id);
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