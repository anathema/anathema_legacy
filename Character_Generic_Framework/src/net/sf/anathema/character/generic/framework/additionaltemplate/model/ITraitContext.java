package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.character.ILimitationContext;

public interface ITraitContext {

  ITraitValueStrategy getTraitValueStrategy();

  ILimitationContext getLimitationContext();
}