package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.data.IExtensibleDataSet;
import net.sf.anathema.character.generic.data.IExtensibleDataSetCompiler;
import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.special.ReflectionSpecialCharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.special.SpecialCharmBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.CharmException;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.generic.type.ReflectionCharacterTypes;
import net.sf.anathema.initialization.ExtensibleDataSetCompiler;
import net.sf.anathema.initialization.ObjectFactory;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IIdentificateRegistry;
import net.sf.anathema.lib.registry.IdentificateRegistry;
import net.sf.anathema.lib.resources.ResourceFile;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
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
  private final Map<Identifier, List<Document>> charmFileTable = new HashMap<>();
  private final IIdentificateRegistry<Identifier> registry = new IdentificateRegistry<>();
  private final CharmAlternativeBuilder alternativeBuilder = new CharmAlternativeBuilder();
  private final CharmMergedBuilder mergedBuilder = new CharmMergedBuilder();
  private final CharmRenameBuilder renameBuilder = new CharmRenameBuilder();
  private final SAXReader reader = new SAXReader();
  private final CharmCache charmCache = new CharmCache();
  private final CharacterTypes characterTypes;
  private final CharmSetBuilder setBuilder;
  private final GenericCharmSetBuilder genericBuilder;

  public CharmCompiler(ObjectFactory objectFactory) {
    this.characterTypes = new ReflectionCharacterTypes(objectFactory);
    SpecialCharmBuilder specialCharmBuilder = new ReflectionSpecialCharmBuilder(objectFactory);
    this.setBuilder = new CharmSetBuilder(characterTypes, specialCharmBuilder);
    this.genericBuilder = new GenericCharmSetBuilder(characterTypes, specialCharmBuilder);
  }

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
    Identifier type = new SimpleIdentifier(typeString);
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
    for (Identifier type : registry.getAll()) {
      buildStandardCharms(type);
      buildGenericCharms(type);
      buildCharmAlternatives(type);
      buildCharmMerges(type);
      buildCharmRenames(type);
    }
    extractParents(charmCache.getCharms());

    return charmCache;
  }

  private void buildStandardCharms(Identifier type) throws PersistenceException {
    buildCharms(type, setBuilder);
  }

  private void buildGenericCharms(Identifier type) throws PersistenceException {
    try {
      ICharacterType characterType = characterTypes.findById(type.getId());
      TraitType[] primaryTypes = characterType.getFavoringTraitType().getTraitTypesForGenericCharms();
      genericBuilder.setTypes(primaryTypes);
      buildCharms(type, genericBuilder);
    } catch (IllegalArgumentException exception) {
      // Not a character type; no generic charms
    }
  }

  private void buildCharmAlternatives(Identifier type) {
    if (charmFileTable.containsKey(type)) {
      for (Document charmDocument : charmFileTable.get(type)) {
        alternativeBuilder.buildAlternatives(charmDocument, charmCache.getCharms(type));
      }
    }
  }

  private void buildCharmMerges(Identifier type) {
    if (charmFileTable.containsKey(type)) {
      for (Document charmDocument : charmFileTable.get(type)) {
        mergedBuilder.buildMerges(charmDocument, charmCache.getCharms(type));
      }
    }
  }

  private void buildCharmRenames(Identifier type) {
    if (charmFileTable.containsKey(type)) {
      for (Document charmDocument : charmFileTable.get(type)) {
        charmCache.addCharmRenames(renameBuilder.buildRenames(charmDocument));
      }
    }
  }

  private void buildCharms(Identifier type, ICharmSetBuilder builder) throws PersistenceException {
    if (charmFileTable.containsKey(type)) {
      List<Document> documents = charmFileTable.get(type);
      for (Document charmDocument : documents) {
        buildTypeCharms(type, charmDocument, builder);
      }
    }
  }

  private void buildTypeCharms(Identifier type, Document charmDocument, ICharmSetBuilder builder) throws PersistenceException {
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