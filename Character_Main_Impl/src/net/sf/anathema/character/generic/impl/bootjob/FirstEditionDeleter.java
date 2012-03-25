package net.sf.anathema.character.generic.impl.bootjob;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static net.sf.anathema.character.impl.module.ExaltedCharacterItemTypeConfiguration.CHARACTER_ITEM_TYPE_ID;

public class FirstEditionDeleter extends CharacterChanger {

  public FirstEditionDeleter(IAnathemaModel model) {
    super(model);
  }

  @Override
  protected void actWithCharacter(File character) {
    if (isSecondEditionCharacter(character)) {
      return;
    }
    character.delete();
  }

  private boolean isSecondEditionCharacter(File file) {
    String xmlString = getCharacterAsString(file);
    return xmlString.contains("<RuleSet name=\"SecondEdition\"/>");
  }
}