package net.sf.anathema.character.persistence;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.item.DataItem;
import net.sf.anathema.character.main.model.description.HeroDescriptionFetcher;
import net.sf.anathema.character.model.CharacterStatisticsConfiguration;
import net.sf.anathema.character.model.ExaltedCharacter;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.persistence.ItemMetaDataPersister;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.persistence.HeroModelPersister;
import net.sf.anathema.hero.persistence.HeroModelPersisterAutoCollector;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.workflow.wizard.selection.IDialogModelTemplate;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static net.sf.anathema.character.persistence.ICharacterXmlConstants.TAG_EXALTED_CHARACTER_ROOT;

public class ExaltedCharacterPersister implements RepositoryItemPersister {

  private final ItemMetaDataPersister repositoryItemPerister = new ItemMetaDataPersister();
  private final CharacterDescriptionPersister descriptionPersister = new CharacterDescriptionPersister();
  private final CharacterStatisticPersister statisticsPersister;
  private final IItemType characterType;
  private final ICharacterGenerics generics;
  private final IMessaging messaging;
  private final HeroModelPersisterAutoCollector persisterAutoCollector;

  public ExaltedCharacterPersister(IItemType characterType, ICharacterGenerics generics, IMessaging messaging) {
    this.characterType = characterType;
    this.generics = generics;
    this.messaging = messaging;
    this.statisticsPersister = new CharacterStatisticPersister(generics, messaging);
    this.persisterAutoCollector = new HeroModelPersisterAutoCollector(generics);
  }

  @Override
  public void save(final IRepositoryWriteAccess writeAccess, Item item) throws IOException, RepositoryException {
    OutputStream stream = null;
    try {
      stream = writeAccess.createMainOutputStream();
      saveMainFile(stream, item);
      Hero hero = (Hero) item.getItemData();
      saveModels(writeAccess, hero);
    }
    finally {
      IOUtils.closeQuietly(stream);
    }
  }

  private void saveModels(IRepositoryWriteAccess writeAccess, Hero hero) {
    for (HeroModelPersister persister : persisterAutoCollector.collect()) {
      HeroModel heroModel = hero.getModel(persister.getModelId());
      if (heroModel != null) {
        persister.save(heroModel, new HeroModelSaverImpl(writeAccess));
      }
    }
  }

  public void saveMainFile(OutputStream stream, Item item) throws IOException {
    messaging.addMessage("CharacterPersistence.SavingCharacter", MessageType.INFORMATION, item.getDisplayName());
    Element rootElement = DocumentHelper.createElement(TAG_EXALTED_CHARACTER_ROOT);
    repositoryItemPerister.save(rootElement, item);
    save(rootElement, (Hero) item.getItemData());
    DocumentUtilities.save(DocumentHelper.createDocument(rootElement), stream);
  }

  private void save(Element rootElement, Hero hero) {
    descriptionPersister.save(rootElement, HeroDescriptionFetcher.fetch(hero));
    statisticsPersister.save(rootElement, hero);
  }

  @Override
  public Item load(IRepositoryReadAccess readAccess) throws PersistenceException, RepositoryException {
    InputStream stream = null;
    try {
      if (readAccess == null) {
        return null;
      }
      stream = readAccess.openMainInputStream();
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(stream);
      Item loadedItem = load(document);
      ExaltedCharacter character = (ExaltedCharacter) loadedItem.getItemData();
      loadModels(readAccess, character);
      return loadedItem;
    }
    catch (DocumentException e) {
      throw new PersistenceException(e);
    }
    finally {
      IOUtils.closeQuietly(stream);
    }
  }

  private void loadModels(IRepositoryReadAccess readAccess, Hero hero) {
    for (HeroModelPersister persister : persisterAutoCollector.collect()) {
      HeroModel heroModel = hero.getModel(persister.getModelId());
      if (heroModel != null) {
        persister.load(heroModel, new HeroModelLoaderImpl(readAccess));
      }
    }
  }

  public Item load(Document characterXml) throws PersistenceException {
    Element documentRoot = characterXml.getRootElement();
    ExaltedCharacter character = statisticsPersister.load(documentRoot);
    descriptionPersister.load(documentRoot, HeroDescriptionFetcher.fetch(character));
    markCharacterReadyForWork(character);
    Item item = createItem(character);
    repositoryItemPerister.load(documentRoot, item);
    return item;
  }

  @Override
  public Item createNew(IDialogModelTemplate template) throws PersistenceException {
    if (!(template instanceof CharacterStatisticsConfiguration)) {
      throw new IllegalArgumentException("Bad template type for character creation.");
    }
    CharacterStatisticsConfiguration configuration = (CharacterStatisticsConfiguration) template;
    try {
      ExaltedCharacter character = new ExaltedCharacter(configuration.getTemplate(), generics);
      markCharacterReadyForWork(character);
      return createItem(character);
    } catch (SpellException e) {
      throw new PersistenceException("A problem occured while creating a new character", e);
    }
  }

  private Item createItem(ExaltedCharacter character) {
    return new DataItem(characterType, character);
  }

  private void markCharacterReadyForWork(ExaltedCharacter character) {
    character.setFullyLoaded(true);
  }

}