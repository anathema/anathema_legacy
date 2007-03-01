package net.sf.anathema.character.generic.impl.template.magic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnableArbitrator;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.collection.Table;

public class CharmProvider implements ICharmProvider {

  private final Table<IExaltedEdition, ICharacterType, ISpecialCharm[]> charmsByTypeByRuleSet = new Table<IExaltedEdition, ICharacterType, ISpecialCharm[]>();
  private final MultiEntryMap<IExaltedEdition, ISpecialCharm> martialArtsSpecialCharms = new MultiEntryMap<IExaltedEdition, ISpecialCharm>();

  @Override
  public ISpecialCharm[] getSpecialCharms(IExaltedEdition edition, ICharmLearnableArbitrator arbitrator, ICharmIdMap map) {
    List<ISpecialCharm> relevantCharms = new ArrayList<ISpecialCharm>();
    ISpecialCharm[] allSpecialCharms = getAllSpecialCharms(edition);
    for (ISpecialCharm specialCharm : allSpecialCharms) {
      ICharm charm = map.getCharmById(specialCharm.getCharmId());
      if (charm != null && arbitrator.isLearnable(charm)) {
        relevantCharms.add(specialCharm);
      }
    }
    return relevantCharms.toArray(new ISpecialCharm[relevantCharms.size()]);
  }

  public ISpecialCharm[] getSpecialCharms(ICharacterType characterType, IExaltedEdition edition) {
    ISpecialCharm[] specialCharms = charmsByTypeByRuleSet.get(edition, characterType);
    if (specialCharms == null) {
      return new ISpecialCharm[0];
    }
    return specialCharms;
  }

  private ISpecialCharm[] getAllSpecialCharms(IExaltedEdition edition) {
    List<ISpecialCharm> list = new ArrayList<ISpecialCharm>();
    for (ICharacterType type : CharacterType.values()) {
      Collections.addAll(list, getSpecialCharms(type, edition));
    }
    list.addAll(martialArtsSpecialCharms.get(edition));
    return list.toArray(new ISpecialCharm[list.size()]);
  }

  public void setSpecialCharms(ICharacterType type, IExaltedEdition edition, ISpecialCharm[] charms) {
    charmsByTypeByRuleSet.add(edition, type, charms);
  }

  public void addMartialArtsSpecialCharm(IExaltedEdition edition, ISpecialCharm charm) {
    martialArtsSpecialCharms.add(edition, charm);
  }
}