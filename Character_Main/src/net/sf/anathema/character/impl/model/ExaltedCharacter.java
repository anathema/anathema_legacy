package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.magic.persistence.ISpellCache;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.impl.model.charm.CharmModelImpl;
import net.sf.anathema.character.impl.model.charm.ComboConfiguration;
import net.sf.anathema.character.impl.model.context.CharacterModelContext;
import net.sf.anathema.character.impl.model.statistics.ExtendedConfiguration;
import net.sf.anathema.character.main.hero.DefaultHero;
import net.sf.anathema.character.main.hero.HeroModel;
import net.sf.anathema.character.main.hero.ModelInitializationContext;
import net.sf.anathema.character.main.hero.initialization.HeroModelInitializer;
import net.sf.anathema.character.main.model.description.HeroDescription;
import net.sf.anathema.character.main.model.description.HeroDescriptionFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.charm.CharmModel;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class ExaltedCharacter implements ICharacter {

  private final CharacterChangeManagement management = new CharacterChangeManagement();
  private final CharacterModelContext context;
  private final CharmModelImpl charms;
  private final IComboConfiguration combos;
  private final ISpellConfiguration spells;
  private final ExtendedConfiguration extendedConfiguration;
  private final DefaultHero hero;
  private final ModelInitializationContext initializationContext;

  public ExaltedCharacter(HeroTemplate template, ICharacterGenerics generics) {
    this.hero = new DefaultHero(template);
    context = new CharacterModelContext(new GenericCharacter(this), this, hero.getListening());
    this.extendedConfiguration = new ExtendedConfiguration(context, hero);
    this.initializationContext = new ModelInitializationContext(context, generics);
    addModels(generics);

    // Charm Model
    this.charms = new CharmModelImpl(hero, initializationContext, context);
    charms.addCharmLearnListener(new CharacterChangeCharmListener(context.getCharacterListening()));

    // Combo Model
    this.combos = new ComboConfiguration(charms);
    combos.addComboConfigurationListener(new CharacterChangeComboListener(context.getCharacterListening()));

    // Spell Model
    this.spells = new SpellConfiguration(charms, context.getSpellLearnStrategy(), getTemplate(),
            generics.getDataSet(ISpellCache.class));
    this.spells.addChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        context.getCharacterListening().fireCharacterChanged();
      }
    });

    // Additional Models
    for (IGlobalAdditionalTemplate globalTemplate : generics.getGlobalAdditionalTemplateRegistry().getAll()) {
      addAdditionalModels(generics, globalTemplate);
    }
    addAdditionalModels(generics, template.getAdditionalTemplates());
    extendedConfiguration.addAdditionalModelChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        context.getCharacterListening().fireCharacterChanged();
      }
    });

    getCharacterContext().getCharacterListening().addChangeListener(management.getStatisticsChangeListener());
  }

  private void addModels(ICharacterGenerics generics) {
    HeroModelInitializer initializer = new HeroModelInitializer(initializationContext, getTemplate());
    initializer.addModels(generics, hero);
  }

  private void addAdditionalModels(ICharacterGenerics generics, IAdditionalTemplate... additionalTemplates) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = generics.getAdditionalModelFactoryRegistry();
    for (IAdditionalTemplate additionalTemplate : additionalTemplates) {
      IAdditionalModelFactory factory = additionalModelFactoryRegistry.get(additionalTemplate.getId());
      getExtendedConfiguration().addAdditionalModel(factory, additionalTemplate);
    }
  }

  // todo (sandra): remove itemDate-Relicts in Character (see ExaltedCharacterPersister)
  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    HeroDescription characterDescription = HeroDescriptionFetcher.fetch(this);
    if (characterDescription == null) {
      return;
    }
    ITextualDescription characterName = characterDescription.getName();
    characterName.addTextChangedListener(adjuster);
  }

  @Override
  public void addDirtyListener(IChangeListener changeListener) {
    management.addDirtyListener(changeListener);
  }

  @Override
  public boolean isDirty() {
    return management.isDirty();
  }

  @Override
  public void removeDirtyListener(IChangeListener changeListener) {
    management.removeDirtyListener(changeListener);
  }

  @Override
  public void setClean() {
    management.setClean();
  }

  public CharmModel getCharms() {
    return charms;
  }

  public IComboConfiguration getCombos() {
    return combos;
  }

  public ISpellConfiguration getSpells() {
    return spells;
  }

  @Override
  public ICharacterType getCharacterType() {
    return getTemplate().getTemplateType().getCharacterType();
  }

  public ExtendedConfiguration getExtendedConfiguration() {
    return extendedConfiguration;
  }

  public ICharacterModelContext getCharacterContext() {
    return context;
  }

  @Override
  public HeroTemplate getTemplate() {
    return hero.getTemplate();
  }

  @Override
  public ChangeAnnouncer getChangeAnnouncer() {
    return hero.getChangeAnnouncer();
  }

  @Override
  public <M extends HeroModel> M getModel(Identifier id) {
    return hero.getModel(id);
  }

  public void setFullyLoaded(boolean loaded) {
    hero.setFullyLoaded(loaded);
  }

  @Override
  public boolean isFullyLoaded() {
    return hero.isFullyLoaded();
  }
}