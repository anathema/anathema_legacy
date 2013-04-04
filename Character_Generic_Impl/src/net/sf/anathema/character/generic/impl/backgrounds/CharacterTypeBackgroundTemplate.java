package net.sf.anathema.character.generic.impl.backgrounds;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.type.ICharacterType;

import java.util.ArrayList;
import java.util.List;

public class CharacterTypeBackgroundTemplate extends AbstractBackgroundTemplate {

  private final List<ICharacterType> types = new ArrayList<>();
  private final LowerableState state;

  public CharacterTypeBackgroundTemplate(String id, ICharacterType type, LowerableState state) {
    super(id);
    types.add(type);
    this.state = state;
  }

  public CharacterTypeBackgroundTemplate(String id, ICharacterType type) {
    this(id, type, LowerableState.LowerableRegain);
  }

  public void addContent(CharacterTypeBackgroundTemplate template) {
    Preconditions.checkArgument(getId().equals(template.getId()), "Combine only identical backgrounds");
    types.addAll(template.types);
  }

  @Override
  public boolean acceptsTemplate(ITemplateType templateType) {
    return types.contains(templateType.getCharacterType());
  }

  @Override
  public LowerableState getExperiencedState() {
    return state;
  }
}