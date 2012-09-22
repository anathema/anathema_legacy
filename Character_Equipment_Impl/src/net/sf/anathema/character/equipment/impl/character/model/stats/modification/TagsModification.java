package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import com.google.common.collect.Lists;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.lib.util.Identificate;
import net.sf.anathema.lib.util.Identified;

import static net.sf.anathema.character.equipment.impl.creation.model.WeaponTag.Piercing;
import static net.sf.anathema.lib.lang.ArrayUtilities.containsValue;

public class TagsModification {
  private BaseMaterial material;

  public TagsModification(BaseMaterial material) {
    this.material = material;
  }


  public Identified[] getModifiedValue(Identified[] tags) {
    if (!material.isAdamantBased()) {
      return tags;
    }
    if (!containsValue(tags, Piercing)) {
      return addPiercing(tags);
    }
    return addAdamantPiercing(tags);
  }

  private Identified[] addAdamantPiercing(Identified[] tags) {
    return addTag(tags, new Identificate("AdamantPiercing"));
  }

  private Identified[] addPiercing(Identified[] tags) {
    WeaponTag newTag = Piercing;
    return addTag(tags, newTag);
  }

  private Identified[] addTag(Identified[] tags, Identified newTag) {
    return Lists.asList(newTag, tags).toArray(new Identified[tags.length + 1]);
  }
}