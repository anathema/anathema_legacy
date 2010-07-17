package net.sf.anathema.character.generic.impl.magic.persistence;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.collection.Table;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IdentificateRegistry;
import net.sf.anathema.lib.util.IIdentificate;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class CharmCompiler {
  private final Table<IIdentificate, IExaltedRuleSet, List<Document>> charmFileTable = new Table<IIdentificate, IExaltedRuleSet, List<Document>>();
  private final CharmSetBuilder setBuilder = new CharmSetBuilder();
  private final GenericCharmSetBuilder genericBuilder = new GenericCharmSetBuilder();
  private final CharmAlternativeBuilder alternativeBuilder = new CharmAlternativeBuilder();
  private final IIdentificateRegistry<ICharacterType> registry;
  private final SAXReader reader;

  public CharmCompiler() {
    this.registry = new IdentificateRegistry<ICharacterType>();
    registry.add(CharacterType.values()); //$NON-NLS-1$
    this.reader = new SAXReader();
  }

  public void registerCharmFile(String typeString, String ruleString, URL resource) throws CharmException {
    IIdentificate type;
    if (typeString.equals(MARTIAL_ARTS.getId())) {
      type = MARTIAL_ARTS;
    }
    else {
      type = registry.getById(typeString);
    }
    ExaltedRuleSet ruleSet = ExaltedRuleSet.valueOf(ruleString);
    List<Document> list = charmFileTable.get(type, ruleSet);
    if (list == null) {
      list = new ArrayList<Document>();
      charmFileTable.add(type, ruleSet, list);
    }
    if (resource == null) {
      throw new CharmException("No resource given for " + typeString + "," + ruleString);
    }
    try {
      list.add(reader.read(resource));
    }
    catch (DocumentException e) {
      throw new CharmException(e);
    }
  }

  public void buildCharms() throws PersistenceException {
    for (ExaltedRuleSet rules : ExaltedRuleSet.values()) {
      IExaltedRuleSet basicRules = rules.getBasicRuleset();
      if (basicRules != null) {
        CharmCache.getInstance().cloneCharms(basicRules, rules);
      }
      for (ICharacterType type : registry.getAll()) {
        buildStandardCharms(type, rules);
        buildGenericCharms(type, rules);
        buildCharmAlternatives(type, rules);
      }
      buildStandardCharms(MARTIAL_ARTS, rules);
      buildCharmAlternatives(MARTIAL_ARTS, rules);
    }
    for (ExaltedRuleSet rules : ExaltedRuleSet.values()) {
      extractParents(CharmCache.getInstance().getCharms(rules));
    }
  }

  private List<ICharm> buildStandardCharms(IIdentificate type, ExaltedRuleSet rules) throws PersistenceException {
    return buildCharms(type, rules, setBuilder);
  }

  private List<ICharm> buildGenericCharms(ICharacterType type, ExaltedRuleSet rules) throws PersistenceException {
    ITraitType[] primaryTypes = type.getFavoringTraitType().getTraitTypes(rules.getEdition());
    genericBuilder.setTypes(primaryTypes);
    return buildCharms(type, rules, genericBuilder);
  }

  private void buildCharmAlternatives(IIdentificate type, ExaltedRuleSet rules) {
    if (charmFileTable.contains(type, rules)) {
      for (Document charmDocument : charmFileTable.get(type, rules)) {
        alternativeBuilder.buildAlternatives(charmDocument, CharmCache.getInstance().getCharms(type, rules));
      }
    }
  }

  private List<ICharm> buildCharms(IIdentificate type, IExaltedRuleSet rules, ICharmSetBuilder builder)
      throws PersistenceException {
    List<ICharm> allCharms = new ArrayList<ICharm>();
    if (charmFileTable.contains(type, rules)) {
      List<Document> documents = charmFileTable.get(type, rules);
      for (Document charmDocument : documents) {
        ICharm[] builtCharms = buildRulesetCharms(type, rules, charmDocument, builder);
        Collections.addAll(allCharms, builtCharms);
      }
    }
    return allCharms;
  }

  private ICharm[] buildRulesetCharms(
      final IIdentificate type,
      IExaltedRuleSet rules,
      Document charmDocument,
      ICharmSetBuilder builder) throws PersistenceException {
    CharmCache cache = CharmCache.getInstance();
    ICharm[] charmArray = builder.buildCharms(charmDocument);
    for (ICharm charm : charmArray) {
      cache.addCharm(type, rules, charm);
    }
    return charmArray;
  }

  private void extractParents(Iterable<ICharm> charms) {
    Map<String, Charm> charmsById = new HashMap<String, Charm>();
    for (ICharm charm : charms) {
      charmsById.put(charm.getId(), (Charm) charm);
    }
    for (Charm charm : charmsById.values()) {
      charm.extractParentCharms(charmsById);
    }
  }
}