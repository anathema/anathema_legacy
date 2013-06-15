package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.magic.persistence.ISpellCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.impl.model.advance.ExperiencePointConfiguration;
import net.sf.anathema.character.impl.model.charm.CharmConfiguration;
import net.sf.anathema.character.impl.model.charm.ComboConfiguration;
import net.sf.anathema.character.impl.model.context.CharacterModelContext;
import net.sf.anathema.character.impl.model.statistics.ExtendedConfiguration;
import net.sf.anathema.character.impl.model.traits.CoreTraitConfiguration;
import net.sf.anathema.character.impl.model.traits.RegisteredTrait;
import net.sf.anathema.character.impl.model.traits.TraitRegistrar;
import net.sf.anathema.character.impl.model.traits.essence.EssencePoolConfiguration;
import net.sf.anathema.character.impl.model.traits.listening.CharacterTraitListening;
import net.sf.anathema.character.main.concept.model.CharacterConcept;
import net.sf.anathema.character.main.concept.model.ICharacterConcept;
import net.sf.anathema.character.main.description.model.CharacterDescription;
import net.sf.anathema.character.main.description.model.CharacterDescriptionExtractor;
import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.character.model.CharacterModelAutoCollector;
import net.sf.anathema.character.model.CharacterModelFactory;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.ModelCreationContext;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.model.traits.essence.IEssencePoolConfiguration;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

import java.util.Collection;

public class ExaltedCharacter implements ICharacter {

  private final CharacterChangeManagement management = new CharacterChangeManagement();
  private final CharacterModelContext context = new CharacterModelContext(new GenericCharacter(this));
  private final ICharacterTemplate characterTemplate;
  private final ICharacterConcept concept;
  private final IEssencePoolConfiguration essencePool;
  private final CharmConfiguration charms;
  private final IComboConfiguration combos;
  private final ISpellConfiguration spells;
  private final IHealthConfiguration health;
  private final IExperiencePointConfiguration experiencePoints = new ExperiencePointConfiguration();
  private boolean experienced = false;
  private final IChangeListener casteChangeListener = new IChangeListener() {
    @Override
    public void changeOccurred() {
      context.getCharacterListening().fireCasteChanged();
    }
  };
  private final IChangeListener ageChangeListener = new IChangeListener() {
    @Override
    public void changeOccurred() {
      context.getCharacterListening().fireCharacterChanged();
    }
  };
  private final ExtendedConfiguration extendedConfiguration = new ExtendedConfiguration(context);
  private final ICoreTraitConfiguration traitConfiguration;
  private final DefaultHero hero = new DefaultHero();

  public ExaltedCharacter(ICharacterTemplate template, ICharacterGenerics generics) {
    this.characterTemplate = template;
    this.concept = initConcept();
    this.traitConfiguration = createTraitConfiguration(template, generics);
    this.health = new HealthConfiguration(getTraitArray(template.getToughnessControllingTraitTypes()), traitConfiguration,
            template.getBaseHealthProviders());
    this.charms = new CharmConfiguration(health, context, generics.getCharacterTypes(), generics.getTemplateRegistry(), generics.getCharmProvider());
    initCharmListening(charms);
    this.essencePool = new EssencePoolConfiguration(template.getEssenceTemplate(), template.getAdditionalRules(), context);
    charms.initListening();
    this.combos = new ComboConfiguration(charms);
    combos.addComboConfigurationListener(new CharacterChangeComboListener(context.getCharacterListening()));
    this.spells = new SpellConfiguration(charms, context.getSpellLearnStrategy(), template, generics.getDataSet(ISpellCache.class));
    this.spells.addChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        context.getCharacterListening().fireCharacterChanged();
      }
    });
    initExperienceListening();
    extendedConfiguration.addAdditionalModelChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        context.getCharacterListening().fireCharacterChanged();
      }
    });
    if (characterTemplate.isNpcOnly()) {
      setExperienced(true);
    }
    addModels(generics);
    for (IGlobalAdditionalTemplate globalTemplate : generics.getGlobalAdditionalTemplateRegistry().getAll()) {
      addAdditionalModels(generics, globalTemplate);
    }
    addAdditionalModels(generics, template.getAdditionalTemplates());
    addCompulsiveCharms(template);
    getCharacterContext().getCharacterListening().addChangeListener(management.getStatisticsChangeListener());
  }

  private void addModels(ICharacterGenerics generics) {
    ModelCreationContext creationContext = new DefaultModelCreationContext(generics);
    Instantiater instantiater = generics.getInstantiater();
    Collection<CharacterModelFactory> factories = instantiater.instantiateAll(CharacterModelAutoCollector.class);
    for (CharacterModelFactory factory : factories) {
      CharacterModel model = factory.create(creationContext, hero);
      hero.addModel(model);
      model.addChangeListener(management.getStatisticsChangeListener());
    }
  }

  private CoreTraitConfiguration createTraitConfiguration(ICharacterTemplate template, ICharacterGenerics generics) {
    Collection<TraitRegistrar> registrars = generics.getInstantiater().instantiateAll(RegisteredTrait.class);
    CoreTraitConfiguration configuration = new CoreTraitConfiguration(template, context, generics.getBackgroundRegistry(), registrars);
    new CharacterTraitListening(configuration, context.getCharacterListening(), registrars).initListening();
    return configuration;
  }

  private void addCompulsiveCharms(ICharacterTemplate template) {
    String[] compulsiveCharms = template.getAdditionalRules().getCompulsiveCharmIDs();
    for (String charmId : compulsiveCharms) {
      ICharmConfiguration charmConfiguration = getCharms();
      ICharm charm = charmConfiguration.getCharmById(charmId);
      charmConfiguration.getGroup(charm).learnCharm(charm, false);
    }
  }

  private void addAdditionalModels(ICharacterGenerics generics, IAdditionalTemplate... additionalTemplates) {
    IRegistry<String, IAdditionalModelFactory> additionalModelFactoryRegistry = generics.getAdditionalModelFactoryRegistry();
    for (IAdditionalTemplate additionalTemplate : additionalTemplates) {
      IAdditionalModelFactory factory = additionalModelFactoryRegistry.get(additionalTemplate.getId());
      getExtendedConfiguration().addAdditionalModel(factory, additionalTemplate);
    }
  }

  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    CharacterDescription characterDescription = CharacterDescriptionExtractor.getCharacterDescription(this);
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

  private IGenericTrait[] getTraitArray(ITraitType[] types) {
    IGenericTrait[] traits = new IGenericTrait[types.length];
    for (int i = 0; i != types.length; i++) {
      traits[i] = traitConfiguration.getTrait(types[i]);
    }
    return traits;
  }

  private void initExperienceListening() {
    experiencePoints.addExperiencePointConfigurationListener(new IExperiencePointConfigurationListener() {

      @Override
      public void entryAdded(IExperiencePointEntry entry) {
        context.getCharacterListening().fireCharacterChanged();
      }

      @Override
      public void entryChanged(IExperiencePointEntry entry) {
        context.getCharacterListening().fireCharacterChanged();
      }

      @Override
      public void entryRemoved(IExperiencePointEntry entry) {
        context.getCharacterListening().fireCharacterChanged();
      }
    });
  }

  private CharacterConcept initConcept() {
    CharacterConcept characterConcept = new CharacterConcept();
    characterConcept.getCaste().addChangeListener(casteChangeListener);
    characterConcept.getAge().addChangeListener(ageChangeListener);
    return characterConcept;
  }

  private void initCharmListening(ICharmConfiguration charmConfiguration) {
    charmConfiguration.addCharmLearnListener(new CharacterChangeCharmListener(context.getCharacterListening()));
  }

  public ICharacterConcept getCharacterConcept() {
    return concept;
  }

  public IEssencePoolConfiguration getEssencePool() {
    return essencePool;
  }

  public ICharmConfiguration getCharms() {
    return charms;
  }

  public IHealthConfiguration getHealth() {
    return health;
  }

  public IComboConfiguration getCombos() {
    return combos;
  }

  public ISpellConfiguration getSpells() {
    return spells;
  }

  public boolean isExperienced() {
    return experienced;
  }

  public void setExperienced(boolean experienced) {
    if (this.experienced) {
      return;
    }
    this.experienced = experienced;
    context.setExperienced(experienced);
    context.getCharacterListening().fireExperiencedChanged(experienced);
  }

  public IExperiencePointConfiguration getExperiencePoints() {
    return experiencePoints;
  }

  public ICharacterTemplate getCharacterTemplate() {
    return characterTemplate;
  }

  public ExtendedConfiguration getExtendedConfiguration() {
    return extendedConfiguration;
  }

  public ICoreTraitConfiguration getTraitConfiguration() {
    return traitConfiguration;
  }

  public ICharacterModelContext getCharacterContext() {
    return context;
  }

  @Override
  public <M extends CharacterModel> M getModel(Identified id) {
    return hero.getModel(id);
  }
}