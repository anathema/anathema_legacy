package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.character.generic.data.IExtensibleDataSetCompiler;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.type.HardcodedCharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IdentificateRegistry;
import net.sf.anathema.lib.resources.ResourceFile;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.util.Identified;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ExtensibleDataSetCompiler
public class CharmCompiler implements IExtensibleDataSetCompiler {
  private static final String Charm_File_Recognition_Pattern = "Charms.*\\.xml";
  //matches stuff like data/charms/solar/Charms_Solar_SecondEdition_Occult.xml
  //the pattern is data/charms/REST_OF_PATH/Charms_TYPE_EDITION_ANYTHING.xml
  private static final String Charm_Data_Extraction_Pattern = ".*/Charms_(.*?)_(.*?)(?:_.*)?\\.xml";
  private final Map<Identified, List<Document>> charmFileTable = new HashMap<>();
  private final IIdentificateRegistry<Identified> registry = new IdentificateRegistry<>();
  private final CharmSetBuilder setBuilder = new CharmSetBuilder();
  private final GenericCharmSetBuilder genericBuilder = new GenericCharmSetBuilder();
  private final CharmAlternativeBuilder alternativeBuilder = new CharmAlternativeBuilder();
  private final CharmMergedBuilder mergedBuilder = new CharmMergedBuilder();
  private final CharmRenameBuilder renameBuilder = new CharmRenameBuilder();
  private final SAXReader reader = new SAXReader();
  private final CharmCache charmCache = new CharmCache();

  @Override
  public String getName() {
    return "Charms";
  }

  @Override
  public String getRecognitionPattern() {
    return Charm_File_Recognition_Pattern;
  }

  @Override
  public void registerFile(ResourceFile resource) throws Exception {
    Matcher matcher = Pattern.compile(Charm_Data_Extraction_Pattern).matcher(resource.getFileName());
    matcher.matches();
    String typeString = matcher.group(1);
    Identified type = new Identificate(typeString);
    if (!registry.idRegistered(typeString)) {
      registry.add(type);
    }
    List<Document> list = charmFileTable.get(type);
    if (list == null) {
      list = new ArrayList<>();
      charmFileTable.put(type, list);
    }
    try {
      list.add(reader.read(resource.getURL()));
    } catch (DocumentException e) {
      throw new CharmException(resource.getURL().toExternalForm(), e);
    }
  }

  @Override
  public IExtensibleDataSet build() throws PersistenceException {
    for (Identified type : registry.getAll()) {
      buildStandardCharms(type);
      buildGenericCharms(type);
      buildCharmAlternatives(type);
      buildCharmMerges(type);
      buildCharmRenames(type);
    }
    extractParents(charmCache.getCharms());

    return charmCache;
  }

  private void buildStandardCharms(Identified type) throws PersistenceException {
    buildCharms(type, setBuilder);
  }

  private void buildGenericCharms(Identified type) throws PersistenceException {
    try {
      ICharacterType characterType = new HardcodedCharacterTypes().findById(type.getId());
      ITraitType[] primaryTypes = characterType.getFavoringTraitType().getTraitTypes();
      genericBuilder.setTypes(primaryTypes);
      buildCharms(type, genericBuilder);
    } catch (IllegalArgumentException exception) {
      // Not a character type; no generic charms
    }
  }

  private void buildCharmAlternatives(Identified type) {
    if (charmFileTable.containsKey(type)) {
      for (Document charmDocument : charmFileTable.get(type)) {
        alternativeBuilder.buildAlternatives(charmDocument, charmCache.getCharms(type));
      }
    }
  }

  private void buildCharmMerges(Identified type) {
    if (charmFileTable.containsKey(type)) {
      for (Document charmDocument : charmFileTable.get(type)) {
        mergedBuilder.buildMerges(charmDocument, charmCache.getCharms(type));
      }
    }
  }

  private void buildCharmRenames(Identified type) {
    if (charmFileTable.containsKey(type)) {
      for (Document charmDocument : charmFileTable.get(type)) {
        charmCache.addCharmRenames(renameBuilder.buildRenames(charmDocument));
      }
    }
  }

  private void buildCharms(Identified type, ICharmSetBuilder builder) throws PersistenceException {
    if (charmFileTable.containsKey(type)) {
      List<Document> documents = charmFileTable.get(type);
      for (Document charmDocument : documents) {
        buildTypeCharms(type, charmDocument, builder);
      }
    }
  }

  private void buildTypeCharms(Identified type, Document charmDocument,
                               ICharmSetBuilder builder) throws PersistenceException {
    List<ISpecialCharm> specialCharms = new ArrayList<>();
    ICharm[] charmArray = builder.buildCharms(charmDocument, specialCharms);
    for (ICharm charm : charmArray) {
      charmCache.addCharm(type, charm);
    }
    charmCache.addSpecialCharmData(type, specialCharms);
  }

  private void extractParents(Iterable<ICharm> charms) {
    Map<String, Charm> charmsById = new HashMap<>();
    for (ICharm charm : charms) {
      charmsById.put(charm.getId(), (Charm) charm);
    }
    for (Charm charm : charmsById.values()) {
      charm.extractParentCharms(charmsById);
    }
  }
}