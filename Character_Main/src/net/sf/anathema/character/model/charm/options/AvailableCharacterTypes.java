package net.sf.anathema.character.model.charm.options;

import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.type.CharacterTypes;
import net.sf.anathema.character.generic.type.ICharacterType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AvailableCharacterTypes implements Iterable<ICharacterType> {
  private final List<ICharacterType> availableTypes = new ArrayList<>();
  private final CharmTemplateRetriever retriever;

  public AvailableCharacterTypes(CharmTemplateRetriever retriever) {
    this.retriever = retriever;
  }

  public void collectAvailableTypes(ICharacterType nativeCharacterType, CharacterTypes characterTypes) {
    for (ICharacterType type : characterTypes.findAll()) {
      ICharmTemplate charmTemplate = retriever.getCharmTemplate(type);
      if (charmTemplate != null && charmTemplate.canLearnCharms()) {
        availableTypes.add(type);
      }
    }
    availableTypes.remove(nativeCharacterType);
    availableTypes.add(0, nativeCharacterType);
  }

  @Override
  public Iterator<ICharacterType> iterator() {
    return availableTypes.iterator();
  }

  public ICharacterType[] asArray() {
    return availableTypes.toArray(new ICharacterType[availableTypes.size()]);
  }
}