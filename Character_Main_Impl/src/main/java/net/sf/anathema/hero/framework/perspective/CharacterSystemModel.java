package net.sf.anathema.hero.framework.perspective;

import net.sf.anathema.character.main.framework.item.CharacterPrintNameFileScanner;
import net.sf.anathema.character.main.persistence.HeroItemPersister;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.persistence.RepositoryItemPersister;
import net.sf.anathema.framework.presenter.ItemReceiver;
import net.sf.anathema.framework.reporting.Report;
import net.sf.anathema.framework.repository.IRepositoryFileResolver;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.framework.HeroEnvironmentExtractor;
import net.sf.anathema.hero.framework.perspective.model.CharacterIdentifier;
import net.sf.anathema.hero.framework.perspective.model.CharacterItemModel;
import net.sf.anathema.hero.framework.perspective.model.CharacterPersistenceModel;
import net.sf.anathema.hero.framework.perspective.model.ItemSystemModel;
import net.sf.anathema.hero.framework.perspective.model.NewCharacterListener;
import net.sf.anathema.hero.framework.perspective.model.ReportRegister;
import net.sf.anathema.hero.framework.perspective.sheet.ControlledPrintWithSelectedReport;
import net.sf.anathema.hero.framework.perspective.sheet.QuickPrintCommand;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.platform.RegExCharacterPrintNameFileScanner;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.wizard.selection.ItemTemplateFactory;
import net.sf.anathema.swing.hero.creation.CharacterCreationTemplateFactory;
import org.jmock.example.announcer.Announcer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.sf.anathema.character.main.itemtype.CharacterItemTypeRetrieval.retrieveCharacterItemType;

public class CharacterSystemModel implements ItemSystemModel {

  private final Map<CharacterIdentifier, CharacterItemModel> modelsByIdentifier = new HashMap<>();
  private Announcer<ChangeListener> getsSelectionListener = Announcer.to(ChangeListener.class);
  private Announcer<ChangeListener> becomesExperiencedListener = Announcer.to(ChangeListener.class);
  private Announcer<ChangeListener> becomesInexperiencedListener = Announcer.to(ChangeListener.class);
  private Announcer<NewCharacterListener> characterAddedListener = Announcer.to(NewCharacterListener.class);
  private CharacterIdentifier currentCharacter;
  private Announcer<ChangeListener> becomesDirtyAnnouncer = Announcer.to(ChangeListener.class);
  private Announcer<ChangeListener> becomesCleanAnnouncer = Announcer.to(ChangeListener.class);
  private ChangeListener dirtyListener = new ChangeListener() {
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
    HeroEnvironment generics = getCharacterGenerics();
    IRepositoryFileResolver repositoryFileResolver = model.getRepository().getRepositoryFileResolver();
    return new RegExCharacterPrintNameFileScanner(generics.getCharacterTypes(), repositoryFileResolver);
  }

  private HeroEnvironment getCharacterGenerics() {
    return HeroEnvironmentExtractor.getGenerics(model);
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
  public void whenCurrentSelectionBecomesDirty(ChangeListener listener) {
    becomesDirtyAnnouncer.addListener(listener);
  }

  @Override
  public void whenCurrentSelectionBecomesClean(ChangeListener listener) {
    becomesCleanAnnouncer.addListener(listener);
  }

  @Override
  public void whenGetsSelection(ChangeListener listener) {
    getsSelectionListener.addListener(listener);
  }

  @Override
  public void whenCurrentSelectionBecomesExperienced(ChangeListener listener) {
    becomesExperiencedListener.addListener(listener);
  }

  @Override
  public void whenCurrentSelectionBecomesInexperienced(ChangeListener listener) {
    becomesInexperiencedListener.addListener(listener);
  }

  @Override
  public void convertCurrentToExperienced() {
    ExperienceModelFetcher.fetch(getCurrentCharacter()).setExperienced(true);
  }

  @Override
  public void printCurrentItemQuickly(Resources resources) {
    CharacterReportFinder reportFinder = createReportFinder(resources);
    new QuickPrintCommand(resources, getCurrentItem(), reportFinder).execute();
  }

  @Override
  public void printCurrentItemInto(Report report, Resources resources) {
    new ControlledPrintWithSelectedReport(getCurrentItem(), resources, report).execute();
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
    IItemType itemType = retrieveCharacterItemType();
    ItemTemplateFactory factory = new CharacterCreationTemplateFactory(getCharacterGenerics(), resources);
    RepositoryItemPersister persister = new HeroItemPersister(itemType, getCharacterGenerics(), model.getMessaging());
    new NewItemCommand(factory, resources, receiver, persister).execute();
  }

  @Override
  public void whenNewCharacterIsAdded(NewCharacterListener listener) {
    characterAddedListener.addListener(listener);
  }

  @Override
  public void registerAllReportsOn(ReportRegister register, Resources resources) {
    CharacterReportFinder reportFinder = createReportFinder(resources);
    for (Report report : reportFinder.getAllReports(getCurrentItem())) {
      register.register(report);
    }
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

  private CharacterReportFinder createReportFinder(Resources resources) {
    return new CharacterReportFinder(model, resources);
  }
}
