package net.sf.anathema.character.perspective.model;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.IChangeListener;
import org.jmock.example.announcer.Announcer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterSystemModel implements ItemSelectionModel {

  private final Map<CharacterIdentifier, IItem> itemByIdentifier = new HashMap<>();
  private Announcer<IChangeListener> becomesDirtyAnnouncer = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> becomesCleanAnnouncer = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> getsSelectionListener = Announcer.to(IChangeListener.class);
  private CharacterIdentifier currentCharacter;
  private IChangeListener dirtyListener = new IChangeListener() {
    @Override
    public void changeOccurred() {
      notifyDirtyListeners();
    }
  };
  private final CharacterPersistenceModel persistenceModel;

  public CharacterSystemModel(IAnathemaModel model) {
    this(new CharacterPersistenceModel(model));
  }

  public CharacterSystemModel(CharacterPersistenceModel model) {
    this.persistenceModel = model;
  }

  public List<PrintNameFile> collectCharacterPrintNameFiles() {
    return persistenceModel.collectCharacterPrintNameFiles();
  }

  public IItem loadItem(CharacterIdentifier identifier) {
    IItem item = persistenceModel.loadItem(identifier);
    item.addDirtyListener(dirtyListener);
    itemByIdentifier.put(identifier, item);
    return item;
  }

  @Override
  public void whenCurrentSelectionBecomesDirty(IChangeListener listener) {
    becomesDirtyAnnouncer.addListener(listener);
  }

  @Override
  public void whenCurrentSelectionBecomesClean(IChangeListener listener) {
    becomesCleanAnnouncer.addListener(listener);
  }

  @Override
  public void whenGetsSelection(IChangeListener listener) {
    getsSelectionListener.addListener(listener);
  }

  public void setCurrentCharacter(CharacterIdentifier identifier) {
    this.currentCharacter = identifier;
    notifyDirtyListeners();
    notifyGetSelectionListeners();
  }

  private void notifyGetSelectionListeners() {
    getsSelectionListener.announce().changeOccurred();
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

  @Override
  public void saveCurrent() throws IOException {
    save(getCurrentItem());
  }

  private void save(IItem item) throws IOException {
    persistenceModel.save(item);
    item.setClean();
  }
}
