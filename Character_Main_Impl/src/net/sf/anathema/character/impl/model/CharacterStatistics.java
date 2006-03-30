package net.sf.anathema.character.impl.model;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.caste.ITypedDescriptionType;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.magic.SpellException;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.impl.model.advance.ExperiencePointConfiguration;
import net.sf.anathema.character.impl.model.charm.CharmConfiguration;
import net.sf.anathema.character.impl.model.charm.ComboConfiguration;
import net.sf.anathema.character.impl.model.concept.CharacterConcept;
import net.sf.anathema.character.impl.model.context.CharacterModelContext;
import net.sf.anathema.character.impl.model.statistics.ExtendedConfiguration;
import net.sf.anathema.character.impl.model.traits.CoreTraitConfiguration;
import net.sf.anathema.character.impl.model.traits.essence.EssencePoolConfiguration;
import net.sf.anathema.character.impl.model.traits.listening.CharacterTraitListening;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.character.model.ICharacterConcept;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.ISpellConfiguration;
import net.sf.anathema.character.model.ISpellModelListener;
import net.sf.anathema.character.model.advance.IExperiencePointConfiguration;
import net.sf.anathema.character.model.charm.CharmLearnAdapter;
import net.sf.anathema.character.model.charm.ICharmConfiguration;
import net.sf.anathema.character.model.charm.ICombo;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.model.charm.IComboConfigurationListener;
import net.sf.anathema.character.model.generic.GenericCharacter;
import net.sf.anathema.character.model.health.IHealthConfiguration;
import net.sf.anathema.character.model.traits.ICoreTraitConfiguration;
import net.sf.anathema.character.model.traits.essence.IEssencePoolConfiguration;
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
  private final IObjectValueChangedListener<ITypedDescriptionType> natureChangeListener = new IObjectValueChangedListener<ITypedDescriptionType>() {
    public void valueChanged(ITypedDescriptionType oldValue, ITypedDescriptionType newValue) {
      context.getCharacterListening().fireCharacterChanged();
    }
  };
  private final IObjectValueChangedListener<ITypedDescriptionType> casteChangeListener = new IObjectValueChangedListener<ITypedDescriptionType>() {
    public void valueChanged(ITypedDescriptionType oldValue, ITypedDescriptionType newValue) {
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
    this.concept = new CharacterConcept();
    concept.getCaste().addTypeListener(casteChangeListener);
    concept.getNature().addTypeListener(natureChangeListener);
    traitConfiguration = new CoreTraitConfiguration(template, context, generics.getBackgroundRegistry());
    new CharacterTraitListening(traitConfiguration, context.getCharacterListening()).initListening();
    ITrait toughnessTrait = getTraitConfiguration().getTrait(template.getToughnessControllingTraitType());
    health = new HealthConfiguration(toughnessTrait);
    this.charms = new CharmConfiguration(health, context, generics.getTemplateRegistry(), generics.getCharmProvider());
    initCharmListening(charms);
    this.essencePool = new EssencePoolConfiguration(
        template.getEssenceTemplate(),
        template.getAdditionalRules(),
        context);
    charms.initListening();
    this.combos = new ComboConfiguration(charms, context.getComboLearnStrategy());
    combos.addComboConfigurationListener(new IComboConfigurationListener() {

      public void editEnded() {
        // Nothing to do
      }

      public void editBegun(ICombo combo) {
        // Nothing to do
      }

      public void comboDeleted(ICombo combo) {
        context.getCharacterListening().fireCharacterChanged();
      }

      public void comboChanged(ICombo combo) {
        context.getCharacterListening().fireCharacterChanged();
      }

      public void comboAdded(ICombo combo) {
        context.getCharacterListening().fireCharacterChanged();
      }
    });
    CharacterType characterType = template.getTemplateType().getCharacterType();
    this.spells = new SpellConfiguration(charms, context.getSpellLearnStrategy(), characterType);
    this.spells.addSpellListener(new ISpellModelListener() {
      public void spellsChanged() {
        context.getCharacterListening().fireCharacterChanged();
      }
    });
    extendedConfiguration.addBonusPointsChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        context.getCharacterListening().fireCharacterChanged();
      }
    });
  }

  private void initCharmListening(ICharmConfiguration charmConfiguration) {
    charmConfiguration.addCharmLearnListener(new CharmLearnAdapter() {
      @Override
      public void charmForgotten(ICharm charm) {
        context.getCharacterListening().fireCharacterChanged();
      }

      @Override
      public void charmLearned(ICharm charm) {
        context.getCharacterListening().fireCharacterChanged();
      }

      @Override
      public void recalculateRequested() {
        context.getCharacterListening().fireCharacterChanged();
      }
    });
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