package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.collection.Table;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IdentificateRegistry;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharmCompiler {
  private final Table<IIdentificate, IExaltedRuleSet, List<Document>> charmFileTable = new Table<IIdentificate, IExaltedRuleSet, List<Document>>();
  private final CharmSetBuilder setBuilder = new CharmSetBuilder();
  private final GenericCharmSetBuilder genericBuilder = new GenericCharmSetBuilder();
  private final CharmAlternativeBuilder alternativeBuilder = new CharmAlternativeBuilder();
  private final CharmMergedBuilder mergedBuilder = new CharmMergedBuilder();
  private final CharmRenameBuilder renameBuilder = new CharmRenameBuilder();
  private final IIdentificateRegistry<IIdentificate> registry;
  private final SAXReader reader;
  private CharmCache charmCache;

  public CharmCompiler(CharmCache charmCache) {
    this.charmCache = charmCache;
    this.registry = new IdentificateRegistry<IIdentificate>();
    for (CharacterType characterType : CharacterType.values()) {
      registry.add(new Identificate(characterType.getId()));
    }
    this.reader = new SAXReader();
  }

  public void registerCharmFile(String typeString, URL resource) throws CharmException {
    IIdentificate type = new Identificate(typeString);
    if (!registry.idRegistered(typeString)) {
      registry.add(type);
    }
    ExaltedRuleSet ruleSet = ExaltedRuleSet.SecondEdition;
    List<Document> list = charmFileTable.get(type, ruleSet);
    if (list == null) {
      list = new ArrayList<Document>();
      charmFileTable.add(type, ruleSet, list);
    }
    try {
      list.add(reader.read(resource));
    } catch (DocumentException e) {
      throw new CharmException(resource.toExternalForm(), e);
    }
  }

  public void buildCharms() throws PersistenceException {
    for (ExaltedRuleSet rules : ExaltedRuleSet.values()) {
      for (IIdentificate type : registry.getAll()) {
        buildStandardCharms(type, rules);
        buildGenericCharms(type, rules);
        buildCharmAlternatives(type, rules);
        buildCharmMerges(type, rules);
        buildCharmRenames(type, rules);
      }
    }
    for (ExaltedRuleSet rules : ExaltedRuleSet.values()) {
      extractParents(charmCache.getCharms(rules));
    }
  }

  private void buildStandardCharms(IIdentificate type, ExaltedRuleSet rules) throws PersistenceException {
    buildCharms(type, rules, setBuilder);
  }

  private void buildGenericCharms(IIdentificate type, ExaltedRuleSet rules) throws PersistenceException {
    try {
      ICharacterType characterType = CharacterType.getById(type.getId());
      ITraitType[] primaryTypes = characterType.getFavoringTraitType().getTraitTypes(rules.getEdition());
      genericBuilder.setTypes(primaryTypes);
      buildCharms(type, rules, genericBuilder);
    } catch (IllegalArgumentException exception) {
      // Not a character type; no generic charms
    }
  }

  private void buildCharmAlternatives(IIdentificate type, ExaltedRuleSet rules) {
    if (charmFileTable.contains(type, rules)) {
      for (Document charmDocument : charmFileTable.get(type, rules)) {
        alternativeBuilder.buildAlternatives(charmDocument, charmCache.getCharms(type, rules));
      }
    }
  }

  private void buildCharmMerges(IIdentificate type, ExaltedRuleSet rules) {
    if (charmFileTable.contains(type, rules)) {
      for (Document charmDocument : charmFileTable.get(type, rules)) {
        mergedBuilder.buildMerges(charmDocument, charmCache.getCharms(type, rules));
      }
    }
  }

  private void buildCharmRenames(IIdentificate type, ExaltedRuleSet rules) {
    if (charmFileTable.contains(type, rules)) {
      for (Document charmDocument : charmFileTable.get(type, rules)) {
        charmCache.addCharmRenames(rules, renameBuilder.buildRenames(charmDocument));
      }
    }
  }

  private void buildCharms(IIdentificate type, IExaltedRuleSet rules,
                           ICharmSetBuilder builder) throws PersistenceException {
    boolean hasEntryForTypeUnderRules = charmFileTable.contains(type, rules);
    if (hasEntryForTypeUnderRules) {
      List<Document> documents = charmFileTable.get(type, rules);
      for (Document charmDocument : documents) {
        buildRulesetCharms(type, rules, charmDocument, builder);
      }
    }
  }

  private void buildRulesetCharms(IIdentificate type, IExaltedRuleSet rules, Document charmDocument,
                                  ICharmSetBuilder builder) throws PersistenceException {
    List<ISpecialCharm> specialCharms = new ArrayList<ISpecialCharm>();
    ICharm[] charmArray = builder.buildCharms(charmDocument, specialCharms);
    for (ICharm charm : charmArray) {
      charmCache.addCharm(type, rules, charm);
    }
    charmCache.addSpecialCharmData(rules, type, specialCharms);
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