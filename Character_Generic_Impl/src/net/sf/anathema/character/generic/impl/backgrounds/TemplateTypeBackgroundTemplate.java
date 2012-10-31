package net.sf.anathema.character.generic.impl.backgrounds;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TemplateTypeBackgroundTemplate extends AbstractBackgroundTemplate {

  private final List<ITemplateType> types = new ArrayList<>();
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
    Preconditions.checkArgument(getId().equals(template.getId()), "Combine only identical backgrounds"); //$NON-NLS-1$
    types.addAll(template.types);
  }

  @Override
  public boolean acceptsTemplate(ITemplateType templateType) {
    return this.types.contains(templateType);
  }

  @Override
  public LowerableState getExperiencedState() {
    return experiencedState;
  }
}