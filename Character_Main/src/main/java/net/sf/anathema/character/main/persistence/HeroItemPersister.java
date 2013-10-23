package net.sf.anathema.character.main.persistence;

import net.sf.anathema.character.main.ExaltedCharacter;
import net.sf.anathema.character.main.framework.item.CharacterItem;
import net.sf.anathema.character.main.framework.item.HeroNameFetcher;
import net.sf.anathema.character.main.framework.item.Item;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.framework.repository.access.RepositoryWriteAccess;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.persistence.HeroModelPersister;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.message.MessageType;
import net.sf.anathema.lib.xml.DocumentUtilities;
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

  private final RepositoryIdPersister repositoryIdPersister = new RepositoryIdPersister();
  private final HeroTemplatePersister templatePersister;
  private final HeroEnvironment generics;
  private final IMessaging messaging;
  private final HeroPersisterList persisterList;

  public HeroItemPersister(HeroEnvironment generics, IMessaging messaging) {
    this.generics = generics;
    this.messaging = messaging;
    this.templatePersister = new HeroTemplatePersister(generics);
    this.persisterList = new HeroPersisterList(generics.getObjectFactory());
  }

  @Override
  public Item createNew(HeroTemplate template) throws PersistenceException {
    return createCharacterInItem(template, new NewCharacterInitializer());
  }

  @Override
  public void save(RepositoryWriteAccess writeAccess, Item item) throws PersistenceException {
    Hero hero = (Hero) item.getItemData();
    String name = new HeroNameFetcher().getName(hero);
    messaging.addMessage("CharacterPersistence.SavingCharacter", MessageType.INFORMATION, name);
    saveModels(writeAccess, hero);
    saveMainFile(writeAccess, item);
    messaging.addMessage("CharacterPersistence.SavingCharacterDone", MessageType.INFORMATION, name);
  }

  @Override
  public Item load(RepositoryReadAccess readAccess) throws PersistenceException {
    Element documentRoot = loadCharacterXml(readAccess);
    HeroTemplate template = templatePersister.loadTemplate(documentRoot);
    CharacterInitializer initializer = new LoadingCharacterInitializer(readAccess, persisterList, messaging);
    Item item = createCharacterInItem(template, initializer);
    repositoryIdPersister.load(documentRoot, item);
    return item;
  }

  private void saveMainFile(RepositoryWriteAccess writeAccess, Item item) {
    Hero hero = (Hero) item.getItemData();
    Element rootElement = DocumentHelper.createElement(TAG_EXALTED_CHARACTER_ROOT);
    repositoryIdPersister.save(rootElement, item);
    templatePersister.saveTemplate(rootElement, hero);
    saveCharacterXml(writeAccess, rootElement);
  }

  private void saveCharacterXml(RepositoryWriteAccess writeAccess, Element rootElement) {
    try (OutputStream stream = writeAccess.createMainOutputStream()) {
      DocumentUtilities.save(DocumentHelper.createDocument(rootElement), stream);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
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

  private Element loadCharacterXml(RepositoryReadAccess readAccess) {
    try (InputStream stream = readAccess.openMainInputStream()) {
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(stream);
      return document.getRootElement();
    } catch (DocumentException | IOException e) {
      throw new PersistenceException(e);
    }
  }

  private Item createCharacterInItem(HeroTemplate template, CharacterInitializer initializer) {
    ExaltedCharacter character = new ExaltedCharacter(template, generics);
    initializer.initialize(character);
    return initItem(character);
  }

  private Item initItem(ExaltedCharacter character) {
    character.markReadyForWork();
    return new CharacterItem(character);
  }
}