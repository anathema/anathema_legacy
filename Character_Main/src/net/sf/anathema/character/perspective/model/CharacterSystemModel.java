package net.sf.anathema.character.perspective.model;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.IRepositoryItemPersister;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.registry.IRegistry;
import org.jmock.example.announcer.Announcer;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterSystemModel {

  private final IAnathemaModel model;
  private final Map<CharacterIdentifier, IItem> itemByIdentifier = new HashMap<>();
  private Announcer<IChangeListener> becomesDirtyAnnouncer = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> becomesCleanAnnouncer = Announcer.to(IChangeListener.class);
  private CharacterIdentifier currentCharacter;
  private IChangeListener dirtyListener = new IChangeListener() {
    @Override
    public void changeOccurred() {
      notifyDirtyListeners();
    }
  };

  public CharacterSystemModel(IAnathemaModel model) {
    this.model = model;
  }

  public List<PrintNameFile> collectCharacterPrintNameFiles() {
    IItemType characterItemType = getCharacterItemType();
    IPrintNameFileAccess access = model.getRepository().getPrintNameFileAccess();
    PrintNameFile[] printNameFiles = access.collectAllPrintNameFiles(characterItemType);
    return Arrays.asList(printNameFiles);
  }

  public IItem loadItem(CharacterIdentifier identifier) {
    IRepositoryReadAccess readAccess = createReadAccess(identifier.getId());
    IRepositoryItemPersister persister = findPersister();
    IItem item = persister.load(readAccess);
    item.addDirtyListener(dirtyListener);
    itemByIdentifier.put(identifier, item);
    return item;
  }

  private IRepositoryItemPersister findPersister() {
    IRegistry<IItemType,IRepositoryItemPersister> registry = model.getPersisterRegistry();
    return registry.get(getCharacterItemType());
  }

  private IRepositoryReadAccess createReadAccess(String repositoryId) {
    IRepository repository = model.getRepository();
    return repository.openReadAccess(getCharacterItemType(), repositoryId);
  }

  private IItemType getCharacterItemType() {
    return retrieveCharacterItemType(model);
  }

  public void whenCurrentSelectionBecomesDirty(IChangeListener listener) {
    becomesDirtyAnnouncer.addListener(listener);
  }

  public void whenCurrentSelectionBecomesClean(IChangeListener listener) {
    becomesCleanAnnouncer.addListener(listener);
  }

  public void setCurrentCharacter(CharacterIdentifier currentCharacter) {
    this.currentCharacter = currentCharacter;
    notifyDirtyListeners();
  }

  private void notifyDirtyListeners() {
    notifyDirtyListeners(getCurrentItem());
  }

  private void notifyDirtyListeners(IItem item) {
    if (item == null) {
      return;
    }
    if (item.isDirty()) {
      becomesDirtyAnnouncer.announce().changeOccurred();
    }
    if (!item.isDirty()) {
      becomesCleanAnnouncer.announce().changeOccurred();
    }
  }

  private IItem getCurrentItem() {
    return itemByIdentifier.get(currentCharacter);
  }

  public void saveCurrent() throws IOException {
    save(getCurrentItem());
  }

  private void save(IItem item) throws IOException {
    IRepositoryItemPersister persister = findPersister();
    IRepository repository = model.getRepository();
    IRepositoryWriteAccess writeAccess = repository.createWriteAccess(item);
    persister.save(writeAccess, item);
    item.setClean();
  }
}
