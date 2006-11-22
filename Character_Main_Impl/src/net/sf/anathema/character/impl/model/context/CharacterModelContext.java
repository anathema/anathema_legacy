package net.sf.anathema.character.impl.model.context;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmLearnStrategy;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.impl.magic.charm.GenericCharmConfiguration;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.impl.model.context.magic.CreationCharmLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.CreationComboLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.CreationSpellLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ExperiencedCharmLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ExperiencedComboLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ExperiencedSpellLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ProxyCharmLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ProxyComboLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ProxySpellLearnStrategy;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.character.impl.model.context.trait.ExperiencedTraitValueStrategy;
import net.sf.anathema.character.impl.model.context.trait.ProxyTraitValueStrategy;
import net.sf.anathema.character.model.ISpellLearnStrategy;
import net.sf.anathema.character.model.charm.learn.IComboLearnStrategy;

public class CharacterModelContext extends GenericCharmConfiguration implements
    ICharacterModelContext,
    ICharmContext,
    ITraitContext {

  private final ProxyTraitValueStrategy traitValueStrategy = new ProxyTraitValueStrategy(
      new CreationTraitValueStrategy());
  private final ProxyComboLearnStrategy comboLearnStrategy = new ProxyComboLearnStrategy(
      new CreationComboLearnStrategy());
  private final ProxySpellLearnStrategy spellLearnStrategy = new ProxySpellLearnStrategy(
      new CreationSpellLearnStrategy());
  private final ProxyCharmLearnStrategy charmLearnStrategy = new ProxyCharmLearnStrategy(
      new CreationCharmLearnStrategy());
  private final CharacterListening characterListening = new CharacterListening();
  private final IGenericCharacter character;
  private final IBasicCharacterData characterData;

  public CharacterModelContext(IGenericCharacter character) {
    super(character);
    this.character = character;
    this.characterData = new BasicCharacterContext(character);
  }

  public IAdditionalModel getAdditionalModel(String id) {
    return character.getAdditionalModel(id);
  }

  public ITraitValueStrategy getTraitValueStrategy() {
    return traitValueStrategy;
  }

  public IComboLearnStrategy getComboLearnStrategy() {
    return comboLearnStrategy;
  }

  public ISpellLearnStrategy getSpellLearnStrategy() {
    return spellLearnStrategy;
  }

  public void setExperienced(boolean experienced) {
    if (experienced) {
      traitValueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
      comboLearnStrategy.setStrategy(new ExperiencedComboLearnStrategy());
      spellLearnStrategy.setStrategy(new ExperiencedSpellLearnStrategy());
      charmLearnStrategy.setStrategy(new ExperiencedCharmLearnStrategy());
    }
    else {
      traitValueStrategy.setStrategy(new CreationTraitValueStrategy());
      comboLearnStrategy.setStrategy(new CreationComboLearnStrategy());
      spellLearnStrategy.setStrategy(new CreationSpellLearnStrategy());
      charmLearnStrategy.setStrategy(new CreationCharmLearnStrategy());
    }
  }

  public ICharmLearnStrategy getCharmLearnStrategy() {
    return charmLearnStrategy;
  }

  public ILimitationContext getLimitationContext() {
    return character;
  }

  public boolean isLearned(ICharm charm) {
    return character.isLearned(charm);
  }

  public IAdditionalRules getAdditionalRules() {
    return character.getTemplate().getAdditionalRules();
  }

  public IGenericTraitCollection getTraitCollection() {
    return character.getTraitCollection();
  }

  public IMagicCollection getMagicCollection() {
    return character;
  }

  public ITraitContext getTraitContext() {
    return this;
  }

  public ICharmContext getCharmContext() {
    return this;
  }

  public CharacterListening getCharacterListening() {
    return characterListening;
  }

  public IBasicCharacterData getBasicCharacterContext() {
    return characterData;
  }
}