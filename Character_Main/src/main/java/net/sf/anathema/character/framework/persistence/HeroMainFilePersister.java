package net.sf.anathema.character.framework.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.character.framework.item.HeroNameFetcher;
import net.sf.anathema.character.framework.item.Item;
import net.sf.anathema.hero.template.TemplateType;
import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.framework.repository.access.RepositoryWriteAccess;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.exception.PersistenceException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class HeroMainFilePersister {
  private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  public HeroMainFileDto load(RepositoryReadAccess readAccess) {
    InputStream inputStream = readAccess.openMainInputStream();
    return load(inputStream);
  }

  public HeroMainFileDto load(InputStream inputStream) {
    try {
      String jsonString = IOUtils.toString(inputStream);
      return gson.fromJson(jsonString, HeroMainFileDto.class);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }

  public void save(RepositoryWriteAccess writeAccess, Item item) {
    HeroMainFileDto mainFileDto = convertHeroToDto(item);
    saveDtoAsJson(writeAccess, mainFileDto);
  }

  private void saveDtoAsJson(RepositoryWriteAccess writeAccess, HeroMainFileDto mainFileDto) {
    String mainFileJson = gson.toJson(mainFileDto);
    try {
      IOUtils.write(mainFileJson, writeAccess.createMainOutputStream());
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }

  private HeroMainFileDto convertHeroToDto(Item item) {
    Hero hero = (Hero) item.getItemData();
    TemplateType templateType = hero.getTemplate().getTemplateType();
    return createDtoToSave(item, hero, templateType);
  }

  private HeroMainFileDto createDtoToSave(Item item, Hero hero, TemplateType templateType) {
    HeroMainFileDto mainFileDto = new HeroMainFileDto();
    mainFileDto.printName = new HeroNameFetcher().getName(hero);
    mainFileDto.repositoryId = item.getRepositoryLocation().getId();
    mainFileDto.characterType.characterType = templateType.getCharacterType().getId();
    mainFileDto.characterType.subType = templateType.getSubType().getId();
    return mainFileDto;
  }
}