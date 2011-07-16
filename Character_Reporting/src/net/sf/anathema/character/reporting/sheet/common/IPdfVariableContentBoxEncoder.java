package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.character.IGenericCharacter;

public interface IPdfVariableContentBoxEncoder extends IPdfContentBoxEncoder {

  public float getRequestedHeight(IGenericCharacter character, float width);
}