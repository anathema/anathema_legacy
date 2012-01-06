package net.sf.anathema.character.generic.impl.backgrounds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;

public class TemplateTypeBackgroundTemplate extends AbstractBackgroundTemplate {

  private final List<ITemplateType> types = new ArrayList<ITemplateType>();
  private final LowerableState experiencedState;

  public TemplateTypeBackgroundTemplate(String id, ITemplateType... types) {
    this(id, types, LowerableState.LowerableRegain);
  }

  public TemplateTypeBackgroundTemplate(String id, ITemplateType[] types, LowerableState experiencedState) {
    super(id);
    Collections.addAll(this.types, types);
    this.experiencedState = experiencedState;
  }

  public void addContent(TemplateTypeBackgroundTemplate template) {
    Ensure.ensureArgumentTrue("Combine only identical backgrounds", getId().equals(template.getId())); //$NON-NLS-1$
    types.addAll(template.types);
  }

  public boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition edition) {
    return this.types.contains(templateType);
  }

  public LowerableState getExperiencedState() {
    return experiencedState;
  }
}