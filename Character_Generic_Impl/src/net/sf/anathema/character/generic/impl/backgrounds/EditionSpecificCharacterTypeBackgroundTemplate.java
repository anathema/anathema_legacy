package net.sf.anathema.character.generic.impl.backgrounds;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.type.ICharacterType;

public class EditionSpecificCharacterTypeBackgroundTemplate extends CharacterTypeBackgroundTemplate {

  private final List<ITemplateType> excludedTemplates;
  private final IExaltedEdition allowedEdition;

  public EditionSpecificCharacterTypeBackgroundTemplate(String id, ICharacterType type,
		  List<ITemplateType> excluded,
		  IExaltedEdition edition,
		  LowerableState state) {
    super(id, type, state);
    this.excludedTemplates = excluded;
    allowedEdition = edition;
  }
  
  public EditionSpecificCharacterTypeBackgroundTemplate(String id, ICharacterType type, List<ITemplateType> excluded,
		  IExaltedEdition edition) {
	    this(id, type, excluded, edition, LowerableState.LowerableRegain);
	  }

  public EditionSpecificCharacterTypeBackgroundTemplate(String id, ICharacterType type, IExaltedEdition edition) {
    this(id, type, null, edition, LowerableState.LowerableRegain);
  }
  
  private boolean templateExcluded(ITemplateType template)
  {
	  for (ITemplateType excludedTemplate : excludedTemplates)
		  if (template.equals(excludedTemplate))
			  return true;
	  return false;
  }

  public boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition edition) {
    return super.acceptsTemplate(templateType, edition) && edition == allowedEdition &&
    	(excludedTemplates == null || !templateExcluded(templateType));
  }
}