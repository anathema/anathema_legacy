package net.sf.anathema.character.main.persistence;

import net.sf.anathema.character.main.ExaltedCharacter;
import net.sf.anathema.character.main.framework.item.CharacterItem;
import net.sf.anathema.character.main.framework.item.Item;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.framework.repository.access.RepositoryWriteAccess;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.persistence.HeroModelPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.message.MessageType;
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
  private final HeroEnvironment generics;
  private final IMessaging messaging;
  private final HeroPersisterList persisterList;

  public HeroItemPersister(HeroEnvironment generics, IMessaging messaging) {
    this.generics = generics;
    this.messaging = messaging;
    this.templatePersister = new HeroTemplatePersister(generics);
    this.persisterList = new HeroPersisterList(generics);
  }

  @Override
  public void save(final RepositoryWriteAccess writeAccess, Item item) throws IOException, RepositoryException {
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
      messaging.addMessage("CharacterPersistence.SavingCharacterDone", MessageType.INFORMATION, item.getDisplayName());
    } finally {
      IOUtils.closeQuietly(stream);
    }
  }

  @Override
  public Item createNew(HeroTemplate template) throws PersistenceException {
    return createCharacterInItem(template, new NewCharacterInitializer());
  }

  @Override
  public Item load(RepositoryReadAccess readAccess) throws PersistenceException, RepositoryException {
    InputStream stream = null;
    try {
      if (readAccess == null) {
        return null;
      }
      stream = readAccess.openMainInputStream();
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(stream);
      Element documentRoot = document.getRootElement();
      HeroTemplate template = templatePersister.loadTemplate(documentRoot);
      CharacterInitializer initializer = new LoadingCharacterInitializer(readAccess, persisterList, messaging);
      Item item = createCharacterInItem(template, initializer);
      repositoryItemPersister.load(documentRoot, item);
      return item;
    } catch (DocumentException e) {
      throw new PersistenceException(e);
    } finally {
      IOUtils.closeQuietly(stream);
    }
  }

  private Item createCharacterInItem(HeroTemplate template, CharacterInitializer initializer) {
    ExaltedCharacter character = new ExaltedCharacter(template, generics);
    initializer.initialize(character);
    return initItem(character);
  }

  private void saveModels(RepositoryWriteAccess writeAccess, Hero hero) {
    for (HeroModelPersister persister : persisterList.iterator(hero)) {
      HeroModel heroModel = hero.getModel(persister.getModelId());
      if (heroModel != null) {
        persister.setMessaging(messaging);
        persister.save(heroModel, new HeroModelSaverImpl(writeAccess));
      }
    }
  }

  private Item initItem(ExaltedCharacter character) {
    character.markReadyForWork();
    return new CharacterItem(character);
  }
}