package net.sf.anathema.character.generic.impl.bootjob;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.lib.lang.ITransformer;

import java.io.File;

public class CharacterTransformer extends CharacterChanger {
  private ITransformer<String, String> transformer;

  public CharacterTransformer(IAnathemaModel anathemaModel, ITransformer<String, String> transformer) {
    super(anathemaModel);
    this.transformer = transformer;
  }

  @Override
  protected void actWithCharacter(File character) {
    String xmlString = getCharacterAsString(character);
    xmlString = transformer.transform(xmlString);
    writeStringAsCharacter(xmlString, character);
  }
}