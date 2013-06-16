package net.sf.anathema.character.impl.model;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IAdditionalModelFactory;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.magic.persistence.ISpellCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.character.generic.traits.GenericTrait;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.impl.model.charm.CharmConfiguration;
import net.sf.anathema.character.impl.model.charm.ComboConfiguration;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.impl.model.context.CharacterModelContext;
import net.sf.anathema.character.impl.model.statistics.ExtendedConfiguration;
import net.sf.anathema.character.impl.model.traits.listening.CharacterTraitListening;
import net.sf.anathema.character.main.abilities.AbilityModel;
import net.sf.anathema.character.main.abilities.DefaultAbilityModel;
import net.sf.anathema.character.main.attributes.model.temporary.AttributeModel;
import net.sf.anathema.character.main.attributes.model.temporary.DefaultAttributeModel;
import net.sf.anathema.character.main.description.model.CharacterDescription;
import net.sf.anathema.character.main.description.model.CharacterDescriptionFetcher;
import net.sf.anathema.character.main.essencepool.model.EssencePoolModel;
import net.sf.anathema.character.main.essencepool.model.EssencePoolModelImpl;
import net.sf.anathema.character.main.model.DefaultHero;
import net.sf.anathema.character.main.model.change.ChangeAnnouncerAdapter;
import net.sf.anathema.character.main.model.initialization.CharacterModelInitializer;
import net.sf.anathema.character.main.othertraits.DefaultOtherTraitModel;
import net.sf.anathema.character.main.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.traits.model.TraitModel;
import net.sf.anathema.character.model.CharacterModel;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.framework.presenter.itemmanagement.PrintNameAdjuster;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public class ExaltedCharacter implements ICharacter {

  private final CharacterChangeManagement management = new CharacterChangeManagement();
  private final CharacterModelContext context = new CharacterModelContext(new GenericCharacter(this), this);
  private final ICharacterTemplate characterTemplate;
  private final EssencePoolModel essencePool;
  private final CharmConfiguration charms;
  private final IComboConfiguration combos;
  private final ISpellConfiguration spells;
  private final IHealthConfiguration health;
  private final ExtendedConfiguration extendedConfiguration = new ExtendedConfiguration(context);
  private final DefaultHero hero = new DefaultHero();
  private final DefaultAttributeModel attributes;
  private final DefaultAbilityModel abilities;

  public ExaltedCharacter(ICharacterTemplate template, ICharacterGenerics generics) {
    this.characterTemplate = template;
    addModels(generics);
    // todo: Beware the side effects
    OtherTraitModel otherTraitModel = new DefaultOtherTraitModel(template, context, getTraitModel());
    this.abilities = new DefaultAbilityModel(template, context, getTraitModel());
    this.attributes = new DefaultAttributeModel(template, context, getTraitModel());
    new CharacterTraitListening(this, context.getCharacterListening()).initListening();
    this.health = new HealthConfiguration(getTraitArray(template.getToughnessControllingTraitTypes()), getTraitModel(),
            template.getBaseHealthProviders());
    this.charms = new CharmConfiguration(health, context, generics.getCharacterTypes(), generics.getTemplateRegistry(), generics.getCharmProvider());
    initCharmListening(charms);
    this.essencePool = new EssencePoolModelImpl(template.getEssenceTemplate(), template.getAdditionalRules(), context);
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
    extendedConfiguration.addAdditionalModelChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        context.getCharacterListening().fireCharacterChanged();
      }
    });
    for (IGlobalAdditionalTemplate globalTemplate : generics.getGlobalAdditionalTemplateRegistry().getAll()) {
      addAdditionalModels(generics, globalTemplate);
    }
    addAdditionalModels(generics, template.getAdditionalTemplates());
    addCompulsiveCharms(template);
    getCharacterContext().getCharacterListening().addChangeListener(management.getStatisticsChangeListener());
  }

  private void addModels(ICharacterGenerics generics) {
    CharacterModelInitializer initializer = new CharacterModelInitializer(createChangeAnnouncer(), characterTemplate);
    initializer.addModels(generics, hero);
  }

  private ChangeAnnouncerAdapter createChangeAnnouncer() {
    CharacterListening listening = (CharacterListening) getCharacterContext().getCharacterListening();
    return new ChangeAnnouncerAdapter(listening, this);
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

  // todo: remove itemDate-Relicts in Character (see ExaltedCharacterPersister)
  public void setPrintNameAdjuster(PrintNameAdjuster adjuster) {
    CharacterDescription characterDescription = CharacterDescriptionFetcher.fetch(this);
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

  private GenericTrait[] getTraitArray(TraitType[] types) {
    GenericTrait[] traits = new GenericTrait[types.length];
    for (int i = 0; i != types.length; i++) {
      traits[i] = getTraitModel().getTrait(types[i]);
    }
    return traits;
  }

  private void initCharmListening(ICharmConfiguration charmConfiguration) {
    charmConfiguration.addCharmLearnListener(new CharacterChangeCharmListener(context.getCharacterListening()));
  }

  @Override
  public AttributeModel getAttributes() {
    return attributes;
  }

  @Override
  public AbilityModel getAbilities()  {
    return abilities;
  }

  public EssencePoolModel getEssencePool() {
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

  public ICharacterTemplate getCharacterTemplate() {
    return characterTemplate;
  }

  @Override
  public ICharacterType getCharacterType() {
    return getCharacterTemplate().getTemplateType().getCharacterType();
  }

  public ExtendedConfiguration getExtendedConfiguration() {
    return extendedConfiguration;
  }

  public TraitModel getTraitModel() {
    return getModel(TraitModel.ID);
  }

  public ICharacterModelContext getCharacterContext() {
    return context;
  }

  @Override
  public <M extends CharacterModel> M getModel(Identifier id) {
    return hero.getModel(id);
  }
}