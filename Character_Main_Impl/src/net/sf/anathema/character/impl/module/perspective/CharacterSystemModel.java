package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.CharacterPersistenceModel;
import net.sf.anathema.character.perspective.model.model.ItemSystemModel;
import net.sf.anathema.character.perspective.model.model.NewCharacterListener;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.presenter.action.NewItemCommand;
import net.sf.anathema.framework.reporting.ControlledPrintCommand;
import net.sf.anathema.framework.reporting.QuickPrintCommand;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.IResources;
import org.jmock.example.announcer.Announcer;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CharacterSystemModel implements ItemSystemModel {

  private final Map<CharacterIdentifier, IItem> itemByIdentifier = new HashMap<>();
  private Announcer<IChangeListener> becomesDirtyAnnouncer = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> becomesCleanAnnouncer = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> getsSelectionListener = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> becomesExperiencedListener = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> becomesInexperiencedListener = Announcer.to(IChangeListener.class);
  private Announcer<NewCharacterListener> characterAddedListener = Announcer.to(NewCharacterListener.class);
  private CharacterIdentifier currentCharacter;
  private IChangeListener dirtyListener = new IChangeListener() {
    @Override
    public void changeOccurred() {
      notifyDirtyListeners();
    }
  };
  private final CharacterPersistenceModel persistenceModel;
  private IAnathemaModel model;
  private int newCharacterCount = 0;

  public CharacterSystemModel(IAnathemaModel model) {
    this(new CharacterPersistenceModel(model), model);
  }

  public CharacterSystemModel(CharacterPersistenceModel persistenceModel, IAnathemaModel model) {
    this.persistenceModel = persistenceModel;
    this.model = model;
  }

  @Override
  public Collection<PrintNameFile> collectAllCharacters() {
    return persistenceModel.collectCharacterPrintNameFiles();
  }

  @Override
  public IItem loadItem(CharacterIdentifier identifier) {
    if (itemByIdentifier.containsKey(identifier)) {
      return itemByIdentifier.get(identifier);
    }
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

  @Override
  public void whenCurrentSelectionBecomesExperienced(IChangeListener listener) {
    becomesExperiencedListener.addListener(listener);
  }

  @Override
  public void whenCurrentSelectionBecomesInexperienced(IChangeListener listener) {
    becomesInexperiencedListener.addListener(listener);
  }

  @Override
  public void convertCurrentToExperienced() {
    getCurrentCharacter().setExperienced(true);
  }

  @Override
  public void printCurrentItemQuickly(IResources resources) {
    new QuickPrintCommand(resources, model, getCurrentItem()).execute();
  }

  @Override
  public void printCurrentItemControlled(IResources resources) {
    new ControlledPrintCommand(resources, model, getCurrentItem()).execute();
  }

  @Override
  public void createNew(final IResources resources) {
    ItemReceiver receiver = new ItemReceiver() {
      @Override
      public void addItem(IItem item) {
        CharacterIdentifier identifier = new CharacterIdentifier("InternalNewCharacter" + newCharacterCount++);
        itemByIdentifier.put(identifier, item);
        item.addDirtyListener(dirtyListener);
        ICharacter character = (ICharacter) item.getItemData();
        ITemplateType templateType = character.getCharacterTemplate().getTemplateType();
        characterAddedListener.announce().added(identifier, item.getDisplayName(), templateType);
      }
    };
    new NewItemCommand(CharacterItemTypeRetrieval.retrieveCharacterItemType(model), model, resources, receiver).execute();
  }

  @Override
  public void whenNewCharacterIsAdded(NewCharacterListener listener) {
    characterAddedListener.addListener(listener);
  }

  @Override
  public void setCurrentCharacter(CharacterIdentifier identifier) {
    this.currentCharacter = identifier;
    notifyDirtyListeners();
    notifyGetSelectionListeners();
    notifyExperiencedListeners();
  }

  private void notifyExperiencedListeners() {
    ICharacter character = getCurrentCharacter();
    if (character.isExperienced()) {
      becomesExperiencedListener.announce().changeOccurred();
    }
    if (!character.isExperienced()) {
      becomesInexperiencedListener.announce().changeOccurred();
    }
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

  private ICharacter getCurrentCharacter() {
    return (ICharacter) getCurrentItem().getItemData();
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
