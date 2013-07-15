package net.sf.anathema.hero.sheet.pdf.encoder.general;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Phrase;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

  public final static String[] RESOURCE_ID = new String[]{"First", "Second",
          "Third", "Fourth",
          "Fifth", "Sixth",
          "Seventh", "Eighth",
          "Ninth", "Tenth"};

  public static void addBulletedListText(Resources resources, Chunk symbolChunk, String resourceBase, Phrase phrase,
                                         boolean showHeader) {
    String header = showHeader ? getRequiredString(resources, resourceBase) : null;
    String[] items = getAvailableLineItems(resources, resourceBase);
    addBulletList(phrase, symbolChunk, header, items);
  }

  public static void addBulletList(Phrase phrase, Chunk symbolChunk, String header, String[] items) {
    if (header != null) {
      phrase.add(header + "\n");
    }
    for (String lineItem : items) {
      phrase.add(symbolChunk);
      phrase.add(lineItem + "\n");
    }
  }

  public static String[] getAvailableLineItems(Resources resources, String resourceBase) {
    List<String> items = new ArrayList<>();
    for (String itemId : ListUtils.RESOURCE_ID) {
      String lineItem = getRequiredString(resources, resourceBase, itemId);
      if (lineItem != null) {
        items.add(lineItem);
      }
    }
    return items.toArray(new String[items.size()]);
  }

  public static String getRequiredString(Resources resources, String resourceBase) {
    return getRequiredString(resources, resourceBase, null);
  }

  private static String getRequiredString(Resources resources, String resourceBase, String resourceId) {
    String baseId;
    if (resourceId == null) {
      baseId = resourceBase;
    } else {
      baseId = resourceBase + "." + resourceId;
    }

    String resource = null;
    if (resources.supportsKey(baseId)) {
      resource = resources.getString(baseId);
    }
    return resource;
  }
}
