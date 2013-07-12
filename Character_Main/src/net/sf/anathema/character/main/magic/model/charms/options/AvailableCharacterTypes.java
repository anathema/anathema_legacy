package net.sf.anathema.character.main.magic.model.charms.options;

import net.sf.anathema.character.main.template.magic.CharmTemplate;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.type.CharacterTypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AvailableCharacterTypes implements Iterable<CharacterType> {
  private final List<CharacterType> availableTypes = new ArrayList<>();
  private final CharmTemplateRetriever retriever;

  public AvailableCharacterTypes(CharmTemplateRetriever retriever) {
    this.retriever = retriever;
  }

  public void collectAvailableTypes(CharacterType nativeCharacterType, CharacterTypes characterTypes) {
    for (CharacterType type : characterTypes.findAll()) {
      CharmTemplate charmTemplate = retriever.getCharmTemplate(type);
      if (charmTemplate != null && charmTemplate.canLearnCharms()) {
        availableTypes.add(type);
      }
    }
    availableTypes.remove(nativeCharacterType);
    availableTypes.add(0, nativeCharacterType);
  }

  @Override
  public Iterator<CharacterType> iterator() {
    return availableTypes.iterator();
  }

  public CharacterType[] asArray() {
    return availableTypes.toArray(new CharacterType[availableTypes.size()]);
  }
}