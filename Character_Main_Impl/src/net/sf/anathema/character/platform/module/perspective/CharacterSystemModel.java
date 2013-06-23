package net.sf.anathema.character.platform.module.perspective;

import net.sf.anathema.character.CharacterPrintNameFileScanner;
import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.impl.persistence.ExaltedCharacterPersister;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.perspective.PreloadedDescriptiveFeatures;
import net.sf.anathema.character.perspective.model.CharacterIdentifier;
import net.sf.anathema.character.perspective.model.CharacterItemModel;
import net.sf.anathema.character.perspective.model.CharacterPersistenceModel;
import net.sf.anathema.character.perspective.model.ItemSystemModel;
import net.sf.anathema.character.perspective.model.NewCharacterListener;
import net.sf.anathema.character.platform.module.RegExCharacterPrintNameFileScanner;
import net.sf.anathema.character.platform.module.repository.CharacterCreationTemplateFactory;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.presenter.action.NewItemCommand;
import net.sf.anathema.framework.reporting.ControlledPrintCommand;
import net.sf.anathema.framework.reporting.QuickPrintCommand;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.wizard.selection.ItemTemplateFactory;
import org.jmock.example.announcer.Announcer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterSystemModel implements ItemSystemModel {

  private final Map<CharacterIdentifier, CharacterItemModel> modelsByIdentifier = new HashMap<>();
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
  public Collection<CharacterItemModel> collectAllExistingCharacters() {
    Collection<PrintNameFile> printNameFiles = persistenceModel.collectCharacterPrintNameFiles();
    List<CharacterItemModel> characters = new ArrayList<>();
    for (PrintNameFile file : printNameFiles) {
      PreloadedDescriptiveFeatures features = new PreloadedDescriptiveFeatures(createFileScanner(), file);
      CharacterItemModel character = new CharacterItemModel(features);
      modelsByIdentifier.put(features.getIdentifier(), character);
      characters.add(character);
    }
    return characters;
  }

  private CharacterPrintNameFileScanner createFileScanner() {
    ICharacterGenerics generics = getCharacterGenerics();
    IRepositoryFileResolver repositoryFileResolver = model.getRepository().getRepositoryFileResolver();
    return new RegExCharacterPrintNameFileScanner(generics.getCharacterTypes(), generics.getCasteCollectionRegistry(), repositoryFileResolver);
  }

  private ICharacterGenerics getCharacterGenerics() {
    return CharacterGenericsExtractor.getGenerics(model);
  }

  @Override
  public Item loadItem(CharacterIdentifier identifier) {
    CharacterItemModel character = modelsByIdentifier.get(identifier);
    if (character.isLoaded()) {
      return character.getItem();
    }
    Item item = persistenceModel.loadItem(identifier);
    character.setItem(item);
    initCharacterListening(item);
    return item;
  }

  private void initCharacterListening(Item item) {
    item.getChangeManagement().addDirtyListener(dirtyListener);
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
    ExperienceModelFetcher.fetch(getCurrentCharacter()).setExperienced(true);
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
      public void addItem(Item item) {
        CharacterIdentifier identifier = new CharacterIdentifier("InternalNewCharacter" + newCharacterCount++);
        initCharacterListening(item);
        CharacterItemModel character = new CharacterItemModel(identifier, item);
        modelsByIdentifier.put(identifier, character);
        characterAddedListener.announce().added(character);
      }
    };
    IItemType itemType = retrieveCharacterItemType(model);
    ItemTemplateFactory factory = new CharacterCreationTemplateFactory(getCharacterGenerics(), resources);
    RepositoryItemPersister persister = new ExaltedCharacterPersister(itemType, getCharacterGenerics(), model.getMessaging());
    new NewItemCommand(factory, resources, receiver, persister).execute();
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
    Hero hero = getCurrentCharacter();
    if (ExperienceModelFetcher.fetch(hero).isExperienced()) {
      becomesExperiencedListener.announce().changeOccurred();
    }
    if (!ExperienceModelFetcher.fetch(hero).isExperienced()) {
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

  private void notifyDirtyListeners(Item item) {
    if (item == null) {
      return;
    }
    boolean dirty = item.getChangeManagement().isDirty();
    if (dirty) {
      becomesDirtyAnnouncer.announce().changeOccurred();
    } else {
      becomesCleanAnnouncer.announce().changeOccurred();
    }
  }

  private Item getCurrentItem() {
    return modelsByIdentifier.get(currentCharacter).getItem();
  }

  private Hero getCurrentCharacter() {
    return (Hero) getCurrentItem().getItemData();
  }

  @Override
  public void saveCurrent() throws IOException {
    save(getCurrentItem());
  }

  private void save(Item item) throws IOException {
    persistenceModel.save(item);
    item.getChangeManagement().setClean();
  }
}
