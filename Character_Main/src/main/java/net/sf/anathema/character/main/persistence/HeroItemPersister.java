package net.sf.anathema.character.main.persistence;

import net.sf.anathema.character.main.CharacterStatisticsConfiguration;
import net.sf.anathema.character.main.ExaltedCharacter;
import net.sf.anathema.character.main.framework.item.DataItem;
import net.sf.anathema.character.main.magic.model.spells.SpellException;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.persistence.ItemMetaDataPersister;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.persistence.HeroModelPersister;
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

public class HeroItemPersister implements RepositoryItemPersister {

  private static final String TAG_EXALTED_CHARACTER_ROOT = "ExaltedCharacter";

  private final ItemMetaDataPersister repositoryItemPersister = new ItemMetaDataPersister();
  private final HeroTemplatePersister templatePersister;
  private final IItemType characterType;
  private final HeroEnvironment generics;
  private final IMessaging messaging;
  private final HeroPersisterList persisterList;

  public HeroItemPersister(IItemType characterType, HeroEnvironment generics, IMessaging messaging) {
    this.characterType = characterType;
    this.generics = generics;
    this.messaging = messaging;
    this.templatePersister = new HeroTemplatePersister(generics);
    this.persisterList = new HeroPersisterList(generics);
  }

  @Override
  public void save(final IRepositoryWriteAccess writeAccess, Item item) throws IOException, RepositoryException {
    OutputStream stream = null;
    try {
      stream = writeAccess.createMainOutputStream();
      messaging.addMessage("CharacterPersistence.SavingCharacter", MessageType.INFORMATION, item.getDisplayName());
      Element rootElement = DocumentHelper.createElement(TAG_EXALTED_CHARACTER_ROOT);
      Hero hero = (Hero) item.getItemData();
      repositoryItemPersister.save(rootElement, item);
      saveModels(writeAccess, hero);
      templatePersister.saveTemplate(rootElement, hero);
      DocumentUtilities.save(DocumentHelper.createDocument(rootElement), stream);
    }
    finally {
      IOUtils.closeQuietly(stream);
    }
  }

  private void saveModels(IRepositoryWriteAccess writeAccess, Hero hero) {
    for (HeroModelPersister persister : persisterList.iterator(hero)) {
      HeroModel heroModel = hero.getModel(persister.getModelId());
      if (heroModel != null) {
        persister.setMessaging(messaging);
        persister.save(heroModel, new HeroModelSaverImpl(writeAccess));
      }
    }
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
      Element documentRoot = document.getRootElement();
      ExaltedCharacter character = templatePersister.loadTemplate(documentRoot);
      loadModels(readAccess, character);
      markCharacterReadyForWork(character);
      Item item = createItem(character);
      repositoryItemPersister.load(documentRoot, item);
      return item;
    }
    catch (DocumentException e) {
      throw new PersistenceException(e);
    }
    finally {
      IOUtils.closeQuietly(stream);
    }
  }

  private void loadModels(IRepositoryReadAccess readAccess, Hero hero) {
    for (HeroModelPersister persister : persisterList.iterator(hero)) {
      HeroModel heroModel = hero.getModel(persister.getModelId());
      if (heroModel != null) {
        persister.setMessaging(messaging);
        persister.load(hero, heroModel, new HeroModelLoaderImpl(readAccess));
      }
    }
  }

  @Override
  public Item createNew(IDialogModelTemplate template) throws PersistenceException {
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