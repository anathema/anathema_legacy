package net.sf.anathema.character.generic.impl.backgrounds;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;

public class TemplateTypeBackgroundTemplate extends AbstractBackgroundTemplate {

  private ITemplateType[] types;
  private final LowerableState experiencedState;

  public TemplateTypeBackgroundTemplate(String id, ITemplateType templateType) {
    this(id, new ITemplateType[] { templateType });
  }

  public TemplateTypeBackgroundTemplate(String id, ITemplateType[] types) {
    this(id, types, LowerableState.LowerableRegain);
  }

  public TemplateTypeBackgroundTemplate(String id, ITemplateType[] types, LowerableState experiencedState) {
    super(id);
    this.types = types;
    this.experiencedState = experiencedState;
  }

  public boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition edition) {
    return ArrayUtilities.contains(types, templateType);
  }

  public LowerableState getExperiencedState() {
    return experiencedState;
  }
}