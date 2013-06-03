package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.perspective.PreloadedDescriptiveFeatures;
import net.sf.anathema.character.perspective.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.CharacterModel;
import net.sf.anathema.character.perspective.model.CharacterPersistenceModel;
import net.sf.anathema.character.perspective.model.ItemSystemModel;
import net.sf.anathema.character.perspective.model.NewCharacterListener;
import net.sf.anathema.character.platform.module.RegExCharacterPrintNameFileScanner;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.presenter.action.NewItemCommand;
import net.sf.anathema.framework.reporting.ControlledPrintCommand;
import net.sf.anathema.framework.reporting.QuickPrintCommand;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.Resources;
import org.jmock.example.announcer.Announcer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterSystemModel implements ItemSystemModel {

  private final Map<CharacterIdentifier, CharacterModel> modelsByIdentifier = new HashMap<>();
  private Announcer<IChangeListener> getsSelectionListener = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> becomesExperiencedListener = Announcer.to(IChangeListener.class);
  private Announcer<IChangeListener> becomesInexperiencedListener = Announcer.to(IChangeListener.class);
  private Announcer<NewCharacterListener> characterAddedListener = Announcer.to(NewCharacterListener.class);
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
  private IApplicationModel model;
  private int newCharacterCount = 0;

  public CharacterSystemModel(IApplicationModel model) {
    this(new CharacterPersistenceModel(model), model);
  }

  public CharacterSystemModel(CharacterPersistenceModel persistenceModel, IApplicationModel model) {
    this.persistenceModel = persistenceModel;
    this.model = model;
  }

  @Override
  public Collection<CharacterModel> collectAllExistingCharacters() {
    Collection<PrintNameFile> printNameFiles = persistenceModel.collectCharacterPrintNameFiles();
    List<CharacterModel> characters = new ArrayList<>();
    for (PrintNameFile file : printNameFiles) {
      PreloadedDescriptiveFeatures features = new PreloadedDescriptiveFeatures(createFileScanner(), file);
      CharacterModel character = new CharacterModel(features);
      modelsByIdentifier.put(features.getIdentifier(), character);
      characters.add(character);
    }
    return characters;
  }

  private CharacterPrintNameFileScanner createFileScanner() {
    ICharacterGenerics generics = CharacterGenericsExtractor.getGenerics(model);
    IRepositoryFileResolver repositoryFileResolver = model.getRepository().getRepositoryFileResolver();
    return new RegExCharacterPrintNameFileScanner(generics.getCharacterTypes(), generics.getCasteCollectionRegistry(), repositoryFileResolver);
  }

  @Override
  public IItem loadItem(CharacterIdentifier identifier) {
    CharacterModel character = modelsByIdentifier.get(identifier);
    if (character.isLoaded()) {
      return character.getItem();
    }
    IItem item = persistenceModel.loadItem(identifier);
    character.setItem(item);
    initCharacterListening(item);
    return item;
  }

  private void initCharacterListening(IItem item) {
    item.addDirtyListener(dirtyListener);
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
  public void printCurrentItemQuickly(Resources resources) {
    CharacterReportFinder reportFinder = new CharacterReportFinder(model, resources);
    new QuickPrintCommand(resources, getCurrentItem(), reportFinder).execute();
  }

  @Override
  public void printCurrentItemControlled(Resources resources) {
    new ControlledPrintCommand(resources, model, getCurrentItem()).execute();
  }

  @Override
  public void createNew(final Resources resources) {
    ItemReceiver receiver = new ItemReceiver() {
      @Override
      public void addItem(IItem item) {
        CharacterIdentifier identifier = new CharacterIdentifier("InternalNewCharacter" + newCharacterCount++);
        initCharacterListening(item);
        CharacterModel character = new CharacterModel(identifier, item);
        modelsByIdentifier.put(identifier, character);
        characterAddedListener.announce().added(character);
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
    if (currentCharacter == null) {
      return;
    }
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
    return modelsByIdentifier.get(currentCharacter).getItem();
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
