package net.sf.anathema.character.platform.bootjob;

import com.google.common.base.Function;
import net.sf.anathema.framework.IAnathemaModel;

import java.io.File;

public class CharacterTransformer extends CharacterChanger {
  private Function<String, String> transformer;

  public CharacterTransformer(IAnathemaModel anathemaModel, Function<String, String> transformer) {
    super(anathemaModel);
    this.transformer = transformer;
  }

  @Override
  protected void actWithCharacter(File character) {
    String xmlString = getCharacterAsString(character);
    xmlString = transformer.apply(xmlString);
    writeStringAsCharacter(xmlString, character);
  }
}