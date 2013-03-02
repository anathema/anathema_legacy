package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.perspective.CharacterNameChangeListener;
import net.sf.anathema.character.perspective.DistinctiveFeatures;
import net.sf.anathema.character.perspective.LoadedDistinctiveFeatures;
import net.sf.anathema.character.perspective.UnloadedDistinctiveFeatures;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.model.CharacterPersistenceModel;
import net.sf.anathema.character.perspective.model.model.ItemSystemModel;
import net.sf.anathema.character.perspective.model.model.NewCharacterListener;
import net.sf.anathema.character.platform.module.RegExCharacterPrintNameFileScanner;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.presenter.action.NewItemCommand;
import net.sf.anathema.framework.reporting.ControlledPrintCommand;
import net.sf.anathema.framework.reporting.QuickPrintCommand;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IItemListener;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.IResources;
import org.jmock.example.announcer.Announcer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterSystemModel implements ItemSystemModel {

  private final Map<CharacterIdentifier, IItem> itemByIdentifier = new HashMap<>();
  private Announcer<IChangeListener> getsSelectionListener = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> becomesExperiencedListener = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> becomesInexperiencedListener = Announcer.to(IChangeListener.class);
  private Announcer<NewCharacterListener> characterAddedListener = Announcer.to(NewCharacterListener.class);
  private Announcer<CharacterNameChangeListener> nameChangedListener = Announcer.to(CharacterNameChangeListener.class);
  private CharacterIdentifier currentCharacter;
  private Announcer<IChangeListener> becomesDirtyAnnouncer = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> becomesCleanAnnouncer = Announcer.to(IChangeListener.class);
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
  public Collection<DistinctiveFeatures> collectAllExistingCharacters() {
    Collection<PrintNameFile> printNameFiles = persistenceModel.collectCharacterPrintNameFiles();
    List<DistinctiveFeatures> distinctiveFeatures = new ArrayList<>();
    for (PrintNameFile file : printNameFiles) {
      distinctiveFeatures.add(new UnloadedDistinctiveFeatures(createFileScanner(), file));
    }
    return distinctiveFeatures;
  }

  private CharacterPrintNameFileScanner createFileScanner() {
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(model);
    IRepositoryFileResolver repositoryFileResolver = model.getRepository().getRepositoryFileResolver();
    return new RegExCharacterPrintNameFileScanner(generics.getCharacterTypes(), generics.getCasteCollectionRegistry(), repositoryFileResolver);
  }

  @Override
  public IItem loadItem(CharacterIdentifier identifier) {
    if (itemByIdentifier.containsKey(identifier)) {
      return itemByIdentifier.get(identifier);
    }
    IItem item = persistenceModel.loadItem(identifier);
    addCharacter(identifier, item);
    return item;
  }

  private void addCharacter(CharacterIdentifier identifier, IItem item) {
    item.addDirtyListener(dirtyListener);
    item.addItemListener(new IItemListener() {
      @Override
      public void printNameChanged(String newName) {
        IItem currentItem = getCurrentItem();
        nameChangedListener.announce().nameChanged(currentCharacter, currentItem.getDisplayName());
      }
    });
    itemByIdentifier.put(identifier, item);
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
        addCharacter(identifier, item);
        ICharacter character = (ICharacter) item.getItemData();
        ITemplateType templateType = character.getCharacterTemplate().getTemplateType();
        DistinctiveFeatures distinctiveFeatures = new LoadedDistinctiveFeatures(identifier, item);
        characterAddedListener.announce().added(distinctiveFeatures);
      }
    };
    new NewItemCommand(retrieveCharacterItemType(model), model, resources, receiver).execute();
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

  @Override
  public void whenCurrentSelectionChangesName(CharacterNameChangeListener listener) {
    nameChangedListener.addListener(listener);
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
