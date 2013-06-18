package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.magic.persistence.ISpellCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.impl.model.charm.CharmConfiguration;
import net.sf.anathema.character.impl.model.charm.ComboConfiguration;
import net.sf.anathema.character.impl.model.context.CharacterModelContext;
import net.sf.anathema.character.impl.model.statistics.ExtendedConfiguration;
import net.sf.anathema.character.main.hero.DefaultHero;
import net.sf.anathema.character.main.hero.HeroModel;
import net.sf.anathema.character.main.hero.ModelInitializationContext;
import net.sf.anathema.character.main.hero.initialization.HeroModelInitializer;
import net.sf.anathema.character.main.model.description.HeroDescription;
import net.sf.anathema.character.main.model.description.HeroDescriptionFetcher;
import net.sf.anathema.character.main.model.health.HealthModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class ExaltedCharacter implements ICharacter {

  private final CharacterChangeManagement management = new CharacterChangeManagement();
  private final CharacterModelContext context = new CharacterModelContext(new GenericCharacter(this), this);
  private final HeroTemplate heroTemplate;
  private final CharmConfiguration charms;
  private final IComboConfiguration combos;
  private final ISpellConfiguration spells;
  private final ExtendedConfiguration extendedConfiguration = new ExtendedConfiguration(context);
  private final DefaultHero hero = new DefaultHero();
  private final ModelInitializationContext initializationContext;

  public ExaltedCharacter(HeroTemplate template, ICharacterGenerics generics) {
    this.heroTemplate = template;
    this.initializationContext = new ModelInitializationContext(context, this, heroTemplate, generics);
    addModels(generics);

    // Charm Model
    this.charms = new CharmConfiguration(hero, HealthModelFetcher.fetch(hero), context, initializationContext.getCharacterTypes(),
            initializationContext.getTemplateRegistry(), initializationContext.getCharmProvider());
    addCompulsiveCharms(template);
    charms.addCharmLearnListener(new CharacterChangeCharmListener(context.getCharacterListening()));
    charms.initListening();

    // Combo Model
    this.combos = new ComboConfiguration(charms);
    combos.addComboConfigurationListener(new CharacterChangeComboListener(context.getCharacterListening()));

    // Spell Model
    this.spells = new SpellConfiguration(charms, context.getSpellLearnStrategy(), initializationContext.getTemplate(),
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

  private void addCompulsiveCharms(HeroTemplate template) {
    String[] compulsiveCharms = template.getAdditionalRules().getCompulsiveCharmIDs();
    for (String charmId : compulsiveCharms) {
      ICharmConfiguration charmConfiguration = getCharms();
      ICharm charm = charmConfiguration.getCharmById(charmId);
      charmConfiguration.getGroup(charm).learnCharm(charm, false);
    }
  }

  private void addModels(ICharacterGenerics generics) {
    HeroModelInitializer initializer = new HeroModelInitializer(initializationContext, heroTemplate);
    initializer.addModels(generics, hero);
  }

  private void addAdditionalModels(ICharacterGenerics generics, IAdditionalTemplate... additionalTemplates) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = generics.getAdditionalModelFactoryRegistry();
    for (IAdditionalTemplate additionalTemplate : additionalTemplates) {
      IAdditionalModelFactory factory = additionalModelFactoryRegistry.get(additionalTemplate.getId());
      getExtendedConfiguration().addAdditionalModel(factory, additionalTemplate);
    }
  }

  // todo: remove itemDate-Relicts in Character (see ExaltedCharacterPersister)
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

  public ICharmConfiguration getCharms() {
    return charms;
  }

  public IComboConfiguration getCombos() {
    return combos;
  }

  public ISpellConfiguration getSpells() {
    return spells;
  }

  public HeroTemplate getHeroTemplate() {
    return heroTemplate;
  }

  @Override
  public ICharacterType getCharacterType() {
    return getHeroTemplate().getTemplateType().getCharacterType();
  }

  public ExtendedConfiguration getExtendedConfiguration() {
    return extendedConfiguration;
  }

  public ICharacterModelContext getCharacterContext() {
    return context;
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