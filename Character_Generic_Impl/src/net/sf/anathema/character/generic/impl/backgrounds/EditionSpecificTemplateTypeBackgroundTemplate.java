package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;

public class EditionSpecificTemplateTypeBackgroundTemplate extends TemplateTypeBackgroundTemplate {

  private final IExaltedEdition edition;

  public EditionSpecificTemplateTypeBackgroundTemplate(
      String id,
      ITemplateType[] types,
      IExaltedEdition edition,
      LowerableState state) {
    super(id, types, state);
    this.edition = edition;
  }
  
  public EditionSpecificTemplateTypeBackgroundTemplate(
	      String id,
	      ITemplateType[] types,
	      IExaltedEdition edition) {
	    this(id, types, edition, LowerableState.LowerableRegain);
	  }

  @Override
  public boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition requestEdition) {
    return super.acceptsTemplate(templateType, edition) && this.edition == requestEdition;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}