package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import com.google.common.collect.Lists;
import net.sf.anathema.character.equipment.MagicalMaterial;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import static net.disy.commons.core.util.ArrayUtilities.containsValue;
import static net.sf.anathema.character.equipment.impl.creation.model.WeaponTag.Piercing;

public class TagsModification {
  private BaseMaterial material;

  public TagsModification(MagicalMaterial material) {
    this.material = new BaseMaterial(material);
  }


  public IIdentificate[] getModifiedValue(IIdentificate[] tags) {
    if (!material.isAdamantBased()) {
      return tags;
    }
    if (!containsValue(tags, Piercing)) {
      return addPiercing(tags);
    }
    return addAdamantPiercing(tags);
  }

  private IIdentificate[] addAdamantPiercing(IIdentificate[] tags) {
    return addTag(tags, new Identificate("AdamantPiercing"));
  }

  private IIdentificate[] addPiercing(IIdentificate[] tags) {
    WeaponTag newTag = Piercing;
    return addTag(tags, newTag);
  }

  private IIdentificate[] addTag(IIdentificate[] tags, IIdentificate newTag) {
    return Lists.asList(newTag, tags).toArray(new IIdentificate[tags.length + 1]);
  }
}