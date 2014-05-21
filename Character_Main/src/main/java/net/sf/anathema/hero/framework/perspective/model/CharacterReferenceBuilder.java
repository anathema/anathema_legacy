package net.sf.anathema.hero.framework.perspective.model;

import com.google.gson.Gson;
import net.sf.anathema.character.framework.persistence.HeroMainFileDto;
import net.sf.anathema.framework.repository.access.printname.ReferenceBuilder;
import net.sf.anathema.framework.repository.access.printname.SimpleRepositoryId;

public class CharacterReferenceBuilder implements ReferenceBuilder<CharacterReference> {
  @Override
  public CharacterReference create(String itemSaveData) {
    HeroMainFileDto dto = new Gson().fromJson(itemSaveData, HeroMainFileDto.class);
    return new CharacterReference(new SimpleRepositoryId(dto.repositoryId), dto.printName);
  }
}