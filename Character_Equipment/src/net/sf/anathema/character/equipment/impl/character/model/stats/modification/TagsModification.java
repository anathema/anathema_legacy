package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import com.google.common.collect.Lists;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.apache.commons.lang3.ArrayUtils;

import static net.sf.anathema.character.equipment.impl.creation.model.WeaponTag.Piercing;

public class TagsModification {
  private BaseMaterial material;

  public TagsModification(BaseMaterial material) {
    this.material = material;
  }

  public Identifier[] getModifiedValue(Identifier[] tags) {
    if (!material.isAdamantBased()) {
      return tags;
    }
    if (!ArrayUtils.contains(tags, Piercing)) {
      return addPiercing(tags);
    }
    return addAdamantPiercing(tags);
  }

  private Identifier[] addAdamantPiercing(Identifier[] tags) {
    return addTag(tags, new SimpleIdentifier("AdamantPiercing"));
  }

  private Identifier[] addPiercing(Identifier[] tags) {
    WeaponTag newTag = Piercing;
    return addTag(tags, newTag);
  }

  private Identifier[] addTag(Identifier[] tags, Identifier newTag) {
    return Lists.asList(newTag, tags).toArray(new Identifier[tags.length + 1]);
  }
}