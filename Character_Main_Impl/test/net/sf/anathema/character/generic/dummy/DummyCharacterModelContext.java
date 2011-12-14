package net.sf.anathema.character.generic.dummy;

import net.sf.anathema.character.generic.dummy.magic.DummyCharmContext;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.dummy.template.DummyCharacterTemplate;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.*;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.impl.model.context.BasicCharacterContext;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;

public class DummyCharacterModelContext implements ICharacterModelContext {

  private ICharacterListening characterListening = new CharacterListening();
  private final ITraitValueStrategy valueStrategy;
  private DummyGenericCharacter character;
  private ITraitContext traitContext = new ITraitContext() {

    public ITraitValueStrategy getTraitValueStrategy() {
      return valueStrategy;
    }

    public ILimitationContext getLimitationContext() {
      return getCharacter();
    }
  };
  private final ICharmContext charmContext;

  public DummyCharacterModelContext() {
    this(new CreationTraitValueStrategy());
  }

  public IAdditionalModel getAdditionalModel(String id) {
    return character.getAdditionalModel(id);
  }

  public DummyCharacterModelContext(final ITraitValueStrategy valueStrategy) {
    this.valueStrategy = valueStrategy;
    ICharacterTemplate template = new DummyCharacterTemplate();
    this.character = new DummyGenericCharacter(template);
    this.charmContext = new DummyCharmContext(character, null);
  }

  public DummyGenericCharacter getCharacter() {
    return character;
  }

  public IAdditionalRules getAdditionalRules() {
    return getCharacter().getTemplate().getAdditionalRules();
  }

  public ICharmContext getCharmContext() {
    return charmContext;
  }

  public IMagicCollection getMagicCollection() {
    return getCharacter();
  }

  public IGenericTraitCollection getTraitCollection() {
    return getCharacter().getTraitCollection();
  }

  public ITraitContext getTraitContext() {
    return traitContext;
  }

  public ICharacterListening getCharacterListening() {
    return characterListening;
  }
  
  public IPresentationProperties getPresentationProperties()
  {
	  return null;
  }

  public IBasicCharacterData getBasicCharacterContext() {
    return new BasicCharacterContext(getCharacter());
  }

	@Override
	public boolean isFullyLoaded() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setFullyLoaded(boolean loaded) {
		// TODO Auto-generated method stub
		
	}
}