package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.lib.lang.ArrayUtilities;

public class TemplateTypeBackgroundTemplate extends AbstractBackgroundTemplate {

  private TemplateType[] types;
  private final LowerableState experiencedState;

  public TemplateTypeBackgroundTemplate(String id, TemplateType templateType) {
    this(id, new TemplateType[] { templateType });
  }

  public TemplateTypeBackgroundTemplate(String id, TemplateType[] types) {
    this(id, types, LowerableState.LowerableRegain);
  }

  public TemplateTypeBackgroundTemplate(String id, TemplateType[] types, LowerableState experiencedState) {
    super(id);
    this.types = types;
    this.experiencedState = experiencedState;
  }

  public boolean acceptsTemplate(TemplateType templateType, IExaltedEdition edition) {
    return ArrayUtilities.contains(types, templateType);
  }

  public LowerableState getExperiencedState() {
    return experiencedState;
  }
}