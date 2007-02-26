package net.sf.anathema.character.impl.model;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.impl.model.advance.ExperiencePointConfiguration;
import net.sf.anathema.character.impl.model.charm.CharmConfiguration;
import net.sf.anathema.character.impl.model.charm.ComboConfiguration;
import net.sf.anathema.character.impl.model.concept.CharacterConcept;
import net.sf.anathema.character.impl.model.concept.Motivation;
import net.sf.anathema.character.impl.model.concept.Nature;
import net.sf.anathema.character.impl.model.context.CharacterModelContext;
import net.sf.anathema.character.impl.model.statistics.ExtendedConfiguration;
import net.sf.anathema.character.impl.model.traits.CoreTraitConfiguration;
import net.sf.anathema.character.impl.model.traits.essence.EssencePoolConfiguration;
import net.sf.anathema.character.impl.model.traits.listening.CharacterTraitListening;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.concept.INature;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConcept;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.model.traits.essence.IEssencePoolConfiguration;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class CharacterStatistics implements ICharacterStatistics {

  private final CharacterModelContext context = new CharacterModelContext(new GenericCharacter(this, null));
  private final ICharacterTemplate characterTemplate;
  private final ICharacterConcept concept;
  private final IEssencePoolConfiguration essencePool;
  private final CharmConfiguration charms;
  private final IComboConfiguration combos;
  private final ISpellConfiguration spells;
  private final IHealthConfiguration health;
  private final IExperiencePointConfiguration experiencePoints = new ExperiencePointConfiguration();
  private final IExaltedRuleSet rules;
  private boolean experienced = false;
  private final IObjectValueChangedListener<String> motivationChangeListener = new IObjectValueChangedListener<String>() {
    public void valueChanged(String newValue) {
      context.getCharacterListening().fireCharacterChanged();
    }
  };
  private final IChangeListener natureChangeListener = new IChangeListener() {
    public void changeOccured() {
      context.getCharacterListening().fireCharacterChanged();
    }
  };
  private final IChangeListener casteChangeListener = new IChangeListener() {
    public void changeOccured() {
      context.getCharacterListening().fireCasteChanged();
    }
  };
  private final ExtendedConfiguration extendedConfiguration = new ExtendedConfiguration(context);
  private final ICoreTraitConfiguration traitConfiguration;

  public CharacterStatistics(final ICharacterTemplate template, ICharacterGenerics generics, IExaltedRuleSet rules)
      throws SpellException {
    Ensure.ensureArgumentNotNull(template);
    Ensure.ensureArgumentNotNull(generics);
    Ensure.ensureArgumentNotNull(rules);
    this.rules = rules;
    this.characterTemplate = template;
    this.concept = initConcept();
    this.traitConfiguration = new CoreTraitConfiguration(template, context, generics.getBackgroundRegistry());
    new CharacterTraitListening(traitConfiguration, context.getCharacterListening()).initListening();
    ITrait toughnessTrait = getTraitConfiguration().getTrait(template.getToughnessControllingTraitType());
    this.health = new HealthConfiguration(toughnessTrait);
    this.charms = new CharmConfiguration(health, context, generics.getTemplateRegistry(), generics.getCharmProvider());
    initCharmListening(charms);
    this.essencePool = new EssencePoolConfiguration(
        template.getEssenceTemplate(),
        template.getAdditionalRules(),
        context);
    charms.initListening();
    this.combos = new ComboConfiguration(charms, context.getComboLearnStrategy(), rules.getEdition());
    combos.addComboConfigurationListener(new CharacterChangeComboListener(context.getCharacterListening()));
    CharacterType characterType = template.getTemplateType().getCharacterType();
    this.spells = new SpellConfiguration(charms, context.getSpellLearnStrategy(), characterType);
    this.spells.addChangeListener(new IChangeListener() {
      public void changeOccured() {
        context.getCharacterListening().fireCharacterChanged();
      }
    });
    initExperienceListening();
    extendedConfiguration.addAdditionalModelChangeListener(new IChangeListener() {
      public void changeOccured() {
        context.getCharacterListening().fireCharacterChanged();
      }
    });
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
    final IWillpowerRegainingConcept[] willpowerConcept = new IWillpowerRegainingConcept[1];
    rules.getEdition().accept(new IEditionVisitor() {
      public void visitFirstEdition(IExaltedEdition visitedEdition) {
        willpowerConcept[0] = new Nature();
      }

      public void visitSecondEdition(IExaltedEdition visitedEdition) {
        willpowerConcept[0] = new Motivation(experiencePoints);
      }
    });
    CharacterConcept characterConcept = new CharacterConcept(willpowerConcept[0]);
    characterConcept.getCaste().addChangeListener(casteChangeListener);
    characterConcept.getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      public void accept(INature nature) {
        nature.getDescription().addChangeListener(natureChangeListener);
      }

      public void accept(IMotivation motivation) {
        motivation.getDescription().addTextChangedListener(motivationChangeListener);
      }
    });
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
    Ensure.ensureFalse("Can't convert already experienced character.", this.experienced); //$NON-NLS-1$
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

  public IExaltedRuleSet getRules() {
    return rules;
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