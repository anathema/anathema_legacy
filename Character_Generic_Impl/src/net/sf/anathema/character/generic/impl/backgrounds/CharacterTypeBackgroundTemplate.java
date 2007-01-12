package net.sf.anathema.character.generic.impl.backgrounds;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.type.CharacterType;

public class CharacterTypeBackgroundTemplate extends AbstractBackgroundTemplate {

  private final List<CharacterType> types = new ArrayList<CharacterType>();
  private final LowerableState state;

  public CharacterTypeBackgroundTemplate(String id, CharacterType type, LowerableState state) {
    super(id);
    types.add(type);
    this.state = state;
  }

  public CharacterTypeBackgroundTemplate(String id, CharacterType type) {
    this(id, type, LowerableState.LowerableRegain);
  }

  public void addContent(CharacterTypeBackgroundTemplate template) {
    Ensure.ensureArgumentTrue("Combine only identical backgrounds", getId().equals(template.getId())); //$NON-NLS-1$
    types.addAll(template.types);
  }

  public boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition edition) {
    return types.contains(templateType.getCharacterType());
  }

  public LowerableState getExperiencedState() {
    return state;
  }
}