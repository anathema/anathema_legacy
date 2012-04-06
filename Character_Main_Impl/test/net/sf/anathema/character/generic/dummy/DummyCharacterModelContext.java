package net.sf.anathema.character.generic.dummy;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.dummy.magic.DummyCharmContext;
import net.sf.anathema.character.generic.dummy.template.DummyCharacterTemplate;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.IGenericSpecialtyContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.impl.model.context.BasicCharacterContext;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;

import java.util.List;

public class DummyCharacterModelContext implements ICharacterModelContext {

  private ICharacterListening characterListening = new CharacterListening();
  private final ITraitValueStrategy valueStrategy;
  private DummyGenericCharacter character;
  private ITraitContext traitContext = new ITraitContext() {

    @Override
    public ITraitValueStrategy getTraitValueStrategy() {
      return valueStrategy;
    }

    @Override
    public ILimitationContext getLimitationContext() {
      return getCharacter();
    }
  };
  private final ICharmContext charmContext;

  public DummyCharacterModelContext() {
    this(new CreationTraitValueStrategy());
  }

  @Override
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

  @Override
  public IAdditionalRules getAdditionalRules() {
    return getCharacter().getTemplate().getAdditionalRules();
  }
  
  public INamedGenericTrait[] getSpecialties(ITraitType traitType) {
	return getCharacter().getSpecialties(traitType);
  }

  @Override
  public ICharmContext getCharmContext() {
    return charmContext;
  }

  @Override
  public IMagicCollection getMagicCollection() {
    return getCharacter();
  }

  @Override
  public IGenericTraitCollection getTraitCollection() {
    return getCharacter().getTraitCollection();
  }

  @Override
  public ITraitContext getTraitContext() {
    return traitContext;
  }

  @Override
  public ICharacterListening getCharacterListening() {
    return characterListening;
  }
  
  @Override
  public IPresentationProperties getPresentationProperties()
  {
	  return null;
  }

  @Override
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

  @Override
  public <T> List<T> getAllRegistered(Class<T> interfaceClass) {
    return getCharacter().getAllRegistered(interfaceClass);
  }

  @Override
	public IGenericSpecialtyContext getSpecialtyContext() {
		return null;
	}
}
