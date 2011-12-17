package net.sf.anathema.character.reporting.encoder;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.extended.common.*;

public interface IPdfVariableContentBoxEncoder extends IPdfContentBoxEncoder {

  public float getRequestedHeight(IGenericCharacter character, float width);
}