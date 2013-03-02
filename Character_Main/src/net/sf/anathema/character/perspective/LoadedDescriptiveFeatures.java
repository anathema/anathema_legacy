package net.sf.anathema.character.perspective;

import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.perspective.model.model.CharacterIdentifier;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.util.Identified;

public class LoadedDescriptiveFeatures implements DescriptiveFeatures {

  private CharacterIdentifier identifier;
  private IItem characterItem;

  public LoadedDescriptiveFeatures(CharacterIdentifier identifier, IItem characterItem) {
    this.identifier = identifier;
    this.characterItem = characterItem;
  }

  @Override
  public String getPrintName() {
    return characterItem.getDisplayName();
  }

  @Override
  public CharacterIdentifier getIdentifier() {
    return identifier;
  }

  @Override
  public ITemplateType getTemplateType() {
    ICharacter character = (ICharacter) characterItem.getItemData();
    return character.getCharacterTemplate().getTemplateType();
  }

  @Override
  public Identified getCasteType() {
    ICharacter character = (ICharacter) characterItem.getItemData();
    return character.getCharacterConcept().getCaste().getType();
  }

  @Override
  public boolean isDirty() {
    return characterItem.isDirty();
  }
}
