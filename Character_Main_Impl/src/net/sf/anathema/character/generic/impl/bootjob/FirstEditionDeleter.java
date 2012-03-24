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

public class FirstEditionDeleter {

  public void deleteAllFirstEditionCharacters(IAnathemaModel model) {
    List<File> characters = getCharacter(model);
    for (File character : characters) {
      if (isSecondEditionCharacter(character)) {
        continue;
      }
      character.delete();
    }
  }


  private boolean isSecondEditionCharacter(File file) {
    try {
      String xmlString = FileUtils.readFileToString(file);
      return xmlString.contains("<RuleSet name=\"SecondEdition\"/>");
    } catch (IOException e) {
      throw new RuntimeException(
              "Could not clean up repository before launch. Please delete all 1E characters manually.", e);
    }
  }

  private List<File> getCharacter(IAnathemaModel model) {
    IItemType character = model.getItemTypeRegistry().getById(CHARACTER_ITEM_TYPE_ID);
    File itemTypeFolder = model.getRepository().getRepositoryFileResolver().getItemTypeFolder(character);
    if (!itemTypeFolder.exists()){
      return Collections.emptyList();
    }
    return Lists.newArrayList(itemTypeFolder.listFiles());
  }
}