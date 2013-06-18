package net.sf.anathema.character.impl.model.context;

import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IGenericSpecialtyContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitValueStrategy;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitContext;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.impl.model.context.magic.CreationSpellLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ExperiencedSpellLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ProxySpellLearnStrategy;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.character.impl.model.context.trait.ExperiencedTraitValueStrategy;
import net.sf.anathema.character.impl.model.context.trait.ProxyTraitValueStrategy;
import net.sf.anathema.character.main.hero.change.FlavoredChangeListener;
import net.sf.anathema.character.main.model.experience.ExperienceChange;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.ISpellLearnStrategy;

import java.util.List;

public class CharacterModelContext implements ICharacterModelContext, ICharmContext, TraitContext {

  private final ProxyTraitValueStrategy traitValueStrategy = new ProxyTraitValueStrategy(new CreationTraitValueStrategy());
  private final ProxySpellLearnStrategy spellLearnStrategy = new ProxySpellLearnStrategy(new CreationSpellLearnStrategy());
  private final IGenericCharacter character;
  private ICharacter hero;
  private CharacterListening characterListening;
  private final IBasicCharacterData characterData;

  public CharacterModelContext(IGenericCharacter character, final ICharacter hero, CharacterListening characterListening) {
    this.character = character;
    this.hero = hero;
    this.characterListening = characterListening;
    this.characterData = new BasicCharacterContext(character);
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
          boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
          updateLearnStrategies(experienced);
        }
      }
    });
  }

  @Override
  public IAdditionalModel getAdditionalModel(String id) {
    return character.getAdditionalModel(id);
  }

  @Override
  public TraitValueStrategy getTraitValueStrategy() {
    return traitValueStrategy;
  }

  public ISpellLearnStrategy getSpellLearnStrategy() {
    return spellLearnStrategy;
  }

  private void updateLearnStrategies(boolean experienced) {
    if (experienced) {
      traitValueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
      spellLearnStrategy.setStrategy(new ExperiencedSpellLearnStrategy());
    } else {
      traitValueStrategy.setStrategy(new CreationTraitValueStrategy());
      spellLearnStrategy.setStrategy(new CreationSpellLearnStrategy());
    }
  }

  @Override
  public ILimitationContext getLimitationContext() {
    return character;
  }

  @Override
  public ICharacter getHero() {
    return hero;
  }

  @Override
  public IAdditionalRules getAdditionalRules() {
    return character.getTemplate().getAdditionalRules();
  }

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return character.getTraitCollection();
  }

  @Override
  public IMagicCollection getMagicCollection() {
    return character;
  }

  @Override
  public TraitContext getTraitContext() {
    return this;
  }

  @Override
  public CharacterListening getCharacterListening() {
    return characterListening;
  }

  @Override
  public IBasicCharacterData getBasicCharacterContext() {
    return characterData;
  }

  @Override
  public IPresentationProperties getPresentationProperties() {
    return character.getTemplate().getPresentationProperties();
  }

  @Override
  public IGenericCharmConfiguration getCharmConfiguration() {
    return character;
  }

  @Override
  public <T> List<T> getAllRegistered(Class<T> interfaceClass) {
    return character.getAllRegistered(interfaceClass);
  }

  @Override
  public IGenericSpecialtyContext getSpecialtyContext() {
    return new GenericSpecialtyContext(character);
  }
}