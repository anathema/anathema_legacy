package net.sf.anathema.character.generic.magic.description;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.lib.resources.IResources;

import java.text.MessageFormat;

public class ShortMagicDescriptionProvider implements MagicDescriptionProvider {

  private IResources resources;

  public ShortMagicDescriptionProvider(IResources resources) {
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
        return descriptionString == null ? new String[0] : new String[]{descriptionString};
      }

      private String getDescriptionString(IMagic magic) {
        String id = magic.getId();
        String genericId = id.substring(0, id.lastIndexOf('.'));

        String description = null;
        if (resources.supportsKey(genericId + ".Description.Long")) //$NON-NLS-1$
          description = resources.getString(genericId + ".Description.Long"); //$NON-NLS-1$
        if (resources.supportsKey(id + ".Description")) //$NON-NLS-1$
          description = resources.getString(id + ".Description"); //$NON-NLS-1$

        if (description != null && magic instanceof ICharm)
          description = MessageFormat.format(description,
                  new Object[]{resources.getString(((ICharm) magic).getPrimaryTraitType().getId())});

        return description;
      }
    };
  }

}
