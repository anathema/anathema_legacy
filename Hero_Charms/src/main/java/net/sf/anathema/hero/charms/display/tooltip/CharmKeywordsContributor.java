package net.sf.anathema.hero.charms.display.tooltip;

import com.google.common.base.Joiner;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.basic.Magic;
import net.sf.anathema.character.main.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class CharmKeywordsContributor implements MagicTooltipContributor {
  private final Resources resources;

  public CharmKeywordsContributor(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void buildStringForMagic(ConfigurableTooltip tooltip, Magic magic, Object details) {
    if (magic instanceof Charm) {
      Charm charm = (Charm) magic;
      List<String> attributeVisualizations = new ArrayList<>();
      for (MagicAttribute attribute : charm.getAttributes()) {
        if (attribute.isVisualized()) {
          attributeVisualizations.add(resources.getString("Keyword." + attribute.getId()));
        }
      }
      String combinedVisualization = Joiner.on(", ").join(attributeVisualizations);
      if (combinedVisualization.length() > 0) {
        String label = resources.getString("CharmTreeView.ToolTip.Keywords");
        tooltip.appendLine(label, combinedVisualization);
      }
    }
  }
}
