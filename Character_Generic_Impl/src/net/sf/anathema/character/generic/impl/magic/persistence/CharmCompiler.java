package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.collection.Table;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.IIdentificate;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class CharmCompiler {
  private final Table<IIdentificate, IExaltedRuleSet, List<URL>> charmFileTable = new Table<IIdentificate, IExaltedRuleSet, List<URL>>();
  private final ICharmSetBuilder builder = new CharmSetBuilder();

  public void registerCharmFile(String typeString, String ruleString, URL resource) {
    IIdentificate type;
    if (typeString.equals(MARTIAL_ARTS.getId())) {
      type = MARTIAL_ARTS;
    }
    else {
      type = CharacterType.getById(typeString);
    }
    ExaltedRuleSet ruleSet = ExaltedRuleSet.valueOf(ruleString);
    List<URL> list = charmFileTable.get(type, ruleSet);
    if (list == null) {
      list = new ArrayList<URL>();
      charmFileTable.add(type, ruleSet, list);
    }
    list.add(resource);
  }

  public void buildCharms() throws PersistenceException {
    for (ExaltedRuleSet rules : ExaltedRuleSet.values()) {
      for (CharacterType type : CharacterType.values()) {
        buildCharms(type, rules);
      }
      buildCharms(MARTIAL_ARTS, rules);
    }
  }

  private void buildCharms(IIdentificate type, IExaltedRuleSet rules) throws PersistenceException {
    if (charmFileTable.contains(type, rules)) {
      buildOfficialCharms(type, rules);
    }
  }

  private void buildOfficialCharms(final IIdentificate type, IExaltedRuleSet rules) throws PersistenceException {
    for (URL url : charmFileTable.get(type, rules)) {
      try {
        Document charmDocument = new SAXReader().read(url);
        buildRulesetCharms(type, rules, charmDocument);
      }
      catch (DocumentException e) {
        throw new CharmException(e);
      }
    }
  }

  private void buildRulesetCharms(final IIdentificate type, IExaltedRuleSet rules, Document charmDocument)
      throws PersistenceException {
    List<ICharm> existingCharms = new ArrayList<ICharm>();
    final IExaltedRuleSet basicRules = rules.getBasicRuleset();
    CharmCache cache = CharmCache.getInstance();
    if (basicRules != null) {
      Collections.addAll(existingCharms, cache.getCharms(type, basicRules));
    }
    ICharm[] charmArray = builder.buildCharms(charmDocument, existingCharms, rules);
    for (ICharm charm : charmArray) {
      cache.addCharm(type, rules, charm);
    }
  }
}