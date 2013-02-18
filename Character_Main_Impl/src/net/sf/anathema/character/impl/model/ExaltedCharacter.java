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
import net.sf.anathema.character.impl.model.concept.CharacterConcept;
import net.sf.anathema.character.impl.model.concept.Motivation;
import net.sf.anathema.character.impl.model.context.CharacterModelContext;
import net.sf.anathema.character.impl.model.statistics.ExtendedConfiguration;
import net.sf.anathema.character.impl.model.traits.CoreTraitConfiguration;
import net.sf.anathema.character.impl.model.traits.essence.EssencePoolConfiguration;
import net.sf.anathema.character.impl.model.traits.listening.CharacterTraitListening;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ICharacterDescription;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.model.traits.essence.IEssencePoolConfiguration;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.registry.IRegistry;

public class ExaltedCharacter implements ICharacter {

  private final ICharacterDescription description = new CharacterDescription();
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
  private final ObjectValueListener<String> motivationChangeListener = new ObjectValueListener<String>() {
    @Override
    public void valueChanged(String newValue) {
      context.getCharacterListening().fireCharacterChanged();
    }
  };
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

  public ExaltedCharacter(ICharacterTemplate template, ICharacterGenerics generics) {
    description.addOverallChangeListener(management.getDescriptionChangeListener());
    this.characterTemplate = template;
    this.concept = initConcept();
    this.traitConfiguration = new CoreTraitConfiguration(template, context, generics.getBackgroundRegistry());
    new CharacterTraitListening(traitConfiguration, context.getCharacterListening()).initListening();
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
    for (IGlobalAdditionalTemplate globalTemplate : generics.getGlobalAdditionalTemplateRegistry().getAll()) {
      addAdditionalModels(generics, globalTemplate);
    }
    addAdditionalModels(generics, template.getAdditionalTemplates());
    addCompulsiveCharms(template);
    getCharacterContext().getCharacterListening().addChangeListener(management.getStatisticsChangeListener());
  }

  @Override
  public ICharacterDescription getDescription() {
    return description;
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

  @Override
  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    description.getName().addTextChangedListener(adjuster);
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
    IMotivation willpowerConcept = new Motivation(experiencePoints);
    CharacterConcept characterConcept = new CharacterConcept(willpowerConcept);
    characterConcept.getCaste().addChangeListener(casteChangeListener);
    characterConcept.getAge().addChangeListener(ageChangeListener);
    willpowerConcept.getDescription().addTextChangedListener(motivationChangeListener);
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
}