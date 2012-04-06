package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Phrase;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

  public final static String[] RESOURCE_ID = new String[]{"First", "Second", //$NON-NLS-1$ //$NON-NLS-2$
          "Third", "Fourth", //$NON-NLS-1$ //$NON-NLS-2$
          "Fifth", "Sixth", //$NON-NLS-1$ //$NON-NLS-2$
          "Seventh", "Eighth", //$NON-NLS-1$ //$NON-NLS-2$
          "Ninth", "Tenth"}; //$NON-NLS-1$ //$NON-NLS-2$

  public static void addBulletedListText(IResources resources, Chunk symbolChunk, String resourceBase, Phrase phrase,
                                         boolean showHeader) {
    String header = showHeader ? getRequiredString(resources, resourceBase) : null;
    String[] items = getAvailableLineItems(resources, resourceBase);
    addBulletList(phrase, symbolChunk, header, items);
  }

  public static void addBulletList(Phrase phrase, Chunk symbolChunk, String header, String[] items) {
    if (header != null) {
      phrase.add(header + "\n"); //$NON-NLS-1$
    }
    for (String lineItem : items) {
      phrase.add(symbolChunk);
      phrase.add(lineItem + "\n"); //$NON-NLS-1$
    }
  }

  public static String[] getAvailableLineItems(IResources resources, String resourceBase) {
    List<String> items = new ArrayList<String>();
    for (String itemId : ListUtils.RESOURCE_ID) {
      String lineItem = getRequiredString(resources, resourceBase, itemId);
      if (lineItem != null) {
        items.add(lineItem);
      }
    }
    return items.toArray(new String[items.size()]);
  }

  public static String getRequiredString(IResources resources, String resourceBase) {
    return getRequiredString(resources, resourceBase, null);
  }

  private static String getRequiredString(IResources resources, String resourceBase, String resourceId) {
    String baseId;
    if (resourceId == null) {
      baseId = resourceBase;
    } else {
      baseId = resourceBase + "." + resourceId; //$NON-NLS-1$
    }

    String resource = null;
    if (resources.supportsKey(baseId)) {
      resource = resources.getString(baseId);
    }
    return resource;
  }
}
