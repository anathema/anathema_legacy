package net.sf.anathema.character.model;

import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;

public interface ICharacterDescription {

  public ISimpleTextualDescription getName();

  public ISimpleTextualDescription getPeriphrase();
  
  public ISimpleTextualDescription getCharacterization();
  
  public ISimpleTextualDescription getPhysicalDescription();

  public ISimpleTextualDescription getNotes();

}
