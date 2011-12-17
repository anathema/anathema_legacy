package net.sf.anathema.character.reporting.common;

import com.lowagie.text.Chunk;
import com.lowagie.text.Phrase;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.lib.resources.IResources;

public class ListUtils {

  public final static int MAX_ITEMS_COUNT = 10;
  public final static String[] RESOURCE_ID = new String[]{"First", "Second", //$NON-NLS-1$ //$NON-NLS-2$
                                                          "Third", "Fourth", //$NON-NLS-1$ //$NON-NLS-2$
                                                          "Fifth", "Sixth", //$NON-NLS-1$ //$NON-NLS-2$
                                                          "Seventh", "Eighth", //$NON-NLS-1$ //$NON-NLS-2$
                                                          "Ninth", "Tenth"}; //$NON-NLS-1$ //$NON-NLS-2$

  public static void addBulletedListText(IResources resources, Chunk symbolChunk, IExaltedEdition edition, String resourceBase, Phrase phrase,
                                         boolean showHeader) {
    if (showHeader) {
      String header = getRequiredString(resources, resourceBase, edition);
      phrase.add(header + "\n"); //$NON-NLS-1$
    }
    for (int power = 0; power < ListUtils.MAX_ITEMS_COUNT; power++) {
      String lineItem = getRequiredString(resources, resourceBase, edition, ListUtils.RESOURCE_ID[power]);
      if (lineItem != null) {
        phrase.add(symbolChunk);
        phrase.add(lineItem + "\n"); //$NON-NLS-1$
      }
      else {
        break;
      }
    }
  }

  public static String getRequiredString(IResources resources, String resourceBase, IExaltedEdition edition) {
    return getRequiredString(resources, resourceBase, edition, null);
  }

  public static String getRequiredString(IResources resources, String resourceBase, IExaltedEdition edition, String resourceId) {
    String baseId, editionId;
    if (resourceId == null) {
      baseId = resourceBase;
      editionId = resourceBase + "." + edition.getId(); //$NON-NLS-1$
    }
    else {
      baseId = resourceBase + "." + resourceId; //$NON-NLS-1$
      editionId = resourceBase + "." + edition.getId() + "." + resourceId; //$NON-NLS-1$ //$NON-NLS-2$
    }

    String resource = null;
    if (resources.supportsKey(editionId)) {
      resource = resources.getString(editionId);
    }
    else if (resources.supportsKey(baseId)) {
      resource = resources.getString(baseId);
    }
    return resource;
  }
}
