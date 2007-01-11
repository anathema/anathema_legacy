package net.sf.anathema.character.generic.impl.backgrounds;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.type.CharacterType;

public class CharacterTypeBackgroundTemplate extends AbstractBackgroundTemplate {

  private final CharacterType type;
  private final LowerableState state;

  public CharacterTypeBackgroundTemplate(String id, CharacterType type, LowerableState state) {
    super(id);
    this.type = type;
    this.state = state;
  }

  public CharacterTypeBackgroundTemplate(String id, CharacterType type) {
    this(id, type, LowerableState.LowerableRegain);
  }

  public boolean acceptsTemplate(ITemplateType templateType, IExaltedEdition edition) {
    return type.equals(templateType.getCharacterType());
  }

  public LowerableState getExperiencedState() {
    return state;
  }
}