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
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IGenericSpecialtyContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.template.magic.IGenericCharmConfiguration;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.impl.model.context.magic.CreationCharmLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.CreationSpellLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ExperiencedCharmLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ExperiencedSpellLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ProxyCharmLearnStrategy;
import net.sf.anathema.character.impl.model.context.magic.ProxySpellLearnStrategy;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.character.impl.model.context.trait.ExperiencedTraitValueStrategy;
import net.sf.anathema.character.impl.model.context.trait.ProxyTraitValueStrategy;
import net.sf.anathema.character.model.ISpellLearnStrategy;
import net.sf.anathema.lib.control.change.IChangeListener;

public class CharacterModelContext implements ICharacterModelContext, ICharmContext, ITraitContext, IGenericSpecialtyContext {

  private final ProxyTraitValueStrategy traitValueStrategy = new ProxyTraitValueStrategy(
          new CreationTraitValueStrategy());
  private final ProxySpellLearnStrategy spellLearnStrategy = new ProxySpellLearnStrategy(
          new CreationSpellLearnStrategy());
  private final ProxyCharmLearnStrategy charmLearnStrategy = new ProxyCharmLearnStrategy(
          new CreationCharmLearnStrategy());
  private final CharacterListening characterListening = new CharacterListening();
  private final IGenericCharacter character;
  private final IBasicCharacterData characterData;
  private boolean isFullyLoaded = false;

  public CharacterModelContext(IGenericCharacter character) {
    this.character = character;
    this.characterData = new BasicCharacterContext(character);
  }

  @Override
  public IAdditionalModel getAdditionalModel(String id) {
    return character.getAdditionalModel(id);
  }

  @Override
  public ITraitValueStrategy getTraitValueStrategy() {
    return traitValueStrategy;
  }
  public ISpellLearnStrategy getSpellLearnStrategy() {
    return spellLearnStrategy;
  }

  public void setExperienced(boolean experienced) {
    if (experienced) {
      traitValueStrategy.setStrategy(new ExperiencedTraitValueStrategy());
      spellLearnStrategy.setStrategy(new ExperiencedSpellLearnStrategy());
      charmLearnStrategy.setStrategy(new ExperiencedCharmLearnStrategy());
    } else {
      traitValueStrategy.setStrategy(new CreationTraitValueStrategy());
      spellLearnStrategy.setStrategy(new CreationSpellLearnStrategy());
      charmLearnStrategy.setStrategy(new CreationCharmLearnStrategy());
    }
  }

  @Override
  public ICharmLearnStrategy getCharmLearnStrategy() {
    return charmLearnStrategy;
  }

  @Override
  public ILimitationContext getLimitationContext() {
    return character;
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
  public ITraitContext getTraitContext() {
    return this;
  }

  @Override
  public ICharmContext getCharmContext() {
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
  public boolean isFullyLoaded() {
    return isFullyLoaded;
  }

  @Override
  public void setFullyLoaded(boolean loaded) {
    isFullyLoaded = loaded;
  }

  @Override
  public IGenericSpecialtyContext getSpecialtyContext() {
    return this;
  }

  @Override
  public INamedGenericTrait[] getSpecialties(ITraitType traitType) {
    return character.getSpecialties(traitType);
  }

  @Override
  public void addSpecialtyListChangeListener(IChangeListener listener) {
    character.addSpecialtyListChangeListener(listener);
  }
}