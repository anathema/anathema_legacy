package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import net.sf.anathema.character.generic.character.IGenericCharacter;

public interface IPdfVariableContentBoxEncoder extends IPdfContentBoxEncoder {

  public float getRequestedHeight(IGenericCharacter character, float width);
}
