package net.sf.anathema.character.generic.impl.magic.charm;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;

public class CharmTree extends AbstractCharmTree<ICharm> {

  private ICharm[] allCharms;
  private final Map<String, ICharm> charmById = new HashMap<String, ICharm>();

  public CharmTree(ICharmTemplate charmTemplate, IExaltedRuleSet rules) {
    this(charmTemplate.getCharms(rules));
  }

  public CharmTree(ICharm[] charms) {
    this.allCharms = charms;
    for (ICharm charm : allCharms) {
      charmById.put(charm.getId(), charm);
    }
  }

  public ICharm getCharmByID(String charmID) {
    return charmById.get(charmID);
  }

  @Override
  protected boolean isLearnableCharm(ICharm charm) {
    return true;
  }

  public ICharm[] getAllCharms() {
    return allCharms;
  }
}