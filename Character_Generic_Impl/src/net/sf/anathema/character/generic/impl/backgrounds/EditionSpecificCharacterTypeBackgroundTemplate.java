package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.type.ICharacterType;

public class EditionSpecificCharacterTypeBackgroundTemplate extends CharacterTypeBackgroundTemplate {

  private final IExaltedEdition allowedEdition;

  public EditionSpecificCharacterTypeBackgroundTemplate(String id, ICharacterType type,
		  IExaltedEdition edition,
		  LowerableState state) {
    super(id, type, state);
    allowedEdition = edition;
  }
  
  public EditionSpecificCharacterTypeBackgroundTemplate(String id, ICharacterType type, IExaltedEdition edition) {
    this(id, type, edition, LowerableState.LowerableRegain);
  }

  public boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition edition) {
    return super.acceptsTemplate(templateType, edition) && edition == allowedEdition;
  }
}