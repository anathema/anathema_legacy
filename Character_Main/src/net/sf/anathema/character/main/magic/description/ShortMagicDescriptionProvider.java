package net.sf.anathema.character.main.magic.description;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.IMagic;
import net.sf.anathema.lib.resources.Resources;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;

public class ShortMagicDescriptionProvider implements MagicDescriptionProvider {

  private Resources resources;

  public ShortMagicDescriptionProvider(Resources resources) {
    this.resources = resources;
  }

  @Override
  public MagicDescription getCharmDescription(final IMagic magic) {
    return new MagicDescription() {
      @Override
      public boolean isEmpty() {
        return getParagraphs().length == 0;
      }

      @Override
      public String[] getParagraphs() {
        String descriptionString = getDescriptionString(magic);
        return StringUtils.isEmpty(descriptionString) ? new String[0] : new String[]{descriptionString};
      }

      private String getDescriptionString(IMagic magic) {
        String id = magic.getId();
        String genericId = id.substring(0, id.lastIndexOf('.'));
        String description = getDescriptionPattern(id, genericId);
        if (magic instanceof ICharm) {
          String traitId = ((ICharm) magic).getPrimaryTraitType().getId();
          description = MessageFormat.format(description, resources.getString(traitId));
        }
        return description;
      }

      private String getDescriptionPattern(String id, String genericId) {
        if (resources.supportsKey(id + ".Description")) {
          return resources.getString(id + ".Description");
        }
        if (resources.supportsKey(genericId + ".Description.Long")) {
          return resources.getString(genericId + ".Description.Long");
        }
        return "";
      }
    };
  }
}