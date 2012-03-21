package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
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
  private final Map<IIdentificate, List<Document>> charmFileTable = new HashMap<IIdentificate, List<Document>>();
  private final CharmSetBuilder setBuilder = new CharmSetBuilder();
  private final GenericCharmSetBuilder genericBuilder = new GenericCharmSetBuilder();
  private final CharmAlternativeBuilder alternativeBuilder = new CharmAlternativeBuilder();
  private final CharmMergedBuilder mergedBuilder = new CharmMergedBuilder();
  private final CharmRenameBuilder renameBuilder = new CharmRenameBuilder();
  private final IIdentificateRegistry<IIdentificate> registry = new IdentificateRegistry<IIdentificate>();
  private final SAXReader reader = new SAXReader();
  private final CharmCache charmCache;

  public CharmCompiler(CharmCache charmCache) {
    this.charmCache = charmCache;
    for (CharacterType characterType : CharacterType.values()) {
      registry.add(new Identificate(characterType.getId()));
    }
  }

  public void registerCharmFile(String typeString, URL resource) throws CharmException {
    IIdentificate type = new Identificate(typeString);
    if (!registry.idRegistered(typeString)) {
      registry.add(type);
    }
    List<Document> list = charmFileTable.get(type);
    if (list == null) {
      list = new ArrayList<Document>();
      charmFileTable.put(type, list);
    }
    try {
      list.add(reader.read(resource));
    } catch (DocumentException e) {
      throw new CharmException(resource.toExternalForm(), e);
    }
  }

  public void buildCharms() throws PersistenceException {
    for (IIdentificate type : registry.getAll()) {
      buildStandardCharms(type);
      buildGenericCharms(type);
      buildCharmAlternatives(type);
      buildCharmMerges(type);
      buildCharmRenames(type);
    }
    extractParents(charmCache.getCharms());
  }

  private void buildStandardCharms(IIdentificate type) throws PersistenceException {
    buildCharms(type, setBuilder);
  }

  private void buildGenericCharms(IIdentificate type) throws PersistenceException {
    try {
      ICharacterType characterType = CharacterType.getById(type.getId());
      ITraitType[] primaryTypes = characterType.getFavoringTraitType().getTraitTypes(ExaltedEdition.SecondEdition);
      genericBuilder.setTypes(primaryTypes);
      buildCharms(type, genericBuilder);
    } catch (IllegalArgumentException exception) {
      // Not a character type; no generic charms
    }
  }

  private void buildCharmAlternatives(IIdentificate type) {
    if (charmFileTable.containsKey(type)) {
      for (Document charmDocument : charmFileTable.get(type)) {
        alternativeBuilder.buildAlternatives(charmDocument, charmCache.getCharms(type));
      }
    }
  }

  private void buildCharmMerges(IIdentificate type) {
    if (charmFileTable.containsKey(type)) {
      for (Document charmDocument : charmFileTable.get(type)) {
        mergedBuilder.buildMerges(charmDocument, charmCache.getCharms(type));
      }
    }
  }

  private void buildCharmRenames(IIdentificate type) {
    if (charmFileTable.containsKey(type)) {
      for (Document charmDocument : charmFileTable.get(type)) {
        charmCache.addCharmRenames(renameBuilder.buildRenames(charmDocument));
      }
    }
  }

  private void buildCharms(IIdentificate type, ICharmSetBuilder builder) throws PersistenceException {
    if (charmFileTable.containsKey(type)) {
      List<Document> documents = charmFileTable.get(type);
      for (Document charmDocument : documents) {
        buildTypeCharms(type, charmDocument, builder);
      }
    }
  }

  private void buildTypeCharms(IIdentificate type, Document charmDocument,
                               ICharmSetBuilder builder) throws PersistenceException {
    List<ISpecialCharm> specialCharms = new ArrayList<ISpecialCharm>();
    ICharm[] charmArray = builder.buildCharms(charmDocument, specialCharms);
    for (ICharm charm : charmArray) {
      charmCache.addCharm(type, charm);
    }
    charmCache.addSpecialCharmData(type, specialCharms);
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