package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.AbstractMagicSourceStringBuilder;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmAttribute;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.resources.IResources;

public class CharmInfoStringBuilder implements ICharmInfoStringBuilder {

  private static final String HtmlLineBreak = "<br>"; //$NON-NLS-1$
  private final IMagicInfoStringBuilder costStringBuilder;
  private final IMagicSourceStringBuilder<ICharm> sourceStringBuilder;
  private final ICharmTypeStringBuilder typeStringBuilder;
  private final IResources resources;

  public CharmInfoStringBuilder(IResources resources) {
    this.resources = resources;
    costStringBuilder = new ScreenDisplayInfoStringBuilder(resources);
    sourceStringBuilder = new AbstractMagicSourceStringBuilder<ICharm>(resources);
    typeStringBuilder = new CharmTypeStringBuilder(resources);
  }

  public final String getInfoString(ICharm charm) {
    Ensure.ensureNotNull("Charm must not be null.", charm); //$NON-NLS-1$
    StringBuilder builder = new StringBuilder();
    builder.append("<html><body><b>"); //$NON-NLS-1$
    builder.append(resources.getString(charm.getId()));
    builder.append("</b><br>"); //$NON-NLS-1$
    builder.append(resources.getString("CharmTreeView.ToolTip.Cost")); //$NON-NLS-1$
    builder.append(IMagicStringBuilderConstants.ColonSpace);
    builder.append(costStringBuilder.createCostString(charm));
    builder.append(HtmlLineBreak);
    builder.append(resources.getString("CharmTreeView.ToolTip.Duration")); //$NON-NLS-1$
    builder.append(IMagicStringBuilderConstants.ColonSpace);
    builder.append(resources.getString("Charm.Duration." + charm.getDuration().getText())); //$NON-NLS-1$
    builder.append(HtmlLineBreak);
    builder.append(resources.getString("CharmTreeView.ToolTip.Type")); //$NON-NLS-1$
    builder.append(IMagicStringBuilderConstants.ColonSpace);
    builder.append(typeStringBuilder.createTypeString(charm.getCharmTypeModel()));
    builder.append(HtmlLineBreak);
    if (MartialArtsUtilities.isMartialArtsCharm(charm)) {
      builder.append(createMartialArtsLevelLine(charm));
    }
    builder.append(createKeywordLine(charm));
    builder.append(createPrerequisiteLines(charm.getPrerequisites()));
    builder.append(createPrerequisiteLines(new IGenericTrait[] { charm.getEssence() }));
    builder.append(resources.getString("CharmTreeView.ToolTip.Source")); //$NON-NLS-1$
    builder.append(IMagicStringBuilderConstants.ColonSpace);
    builder.append(sourceStringBuilder.createSourceString(charm));
    builder.append("</body></html>"); //$NON-NLS-1$
    return builder.toString();

  }

  private StringBuilder createKeywordLine(ICharm charm) {
    StringBuilder builder = new StringBuilder();
    for (ICharmAttribute attribute : charm.getAttributes()) {
      if (attribute.isVisualized()) {
        if (builder.length() != 0) {
          builder.append(IMagicStringBuilderConstants.CommaSpace);
        }
        builder.append(resources.getString("Keyword." + attribute.getId())); //$NON-NLS-1$
      }
    }
    if (builder.length() > 0) {
      builder.insert(0, resources.getString("CharmTreeView.ToolTip.Keywords") + IMagicStringBuilderConstants.ColonSpace); //$NON-NLS-1$
      builder.append(HtmlLineBreak);
    }
    return builder;
  }

  private String createMartialArtsLevelLine(ICharm charm) {
    MartialArtsLevel level = MartialArtsUtilities.getLevel(charm);
    String levelString = resources.getString("CharmTreeView.ToolTip.MartialArtsLevel") + IMagicStringBuilderConstants.ColonSpace; //$NON-NLS-1$
    levelString = levelString.concat(resources.getString(level.getId()));
    levelString = levelString.concat(HtmlLineBreak);
    return levelString;
  }

  private String createPrerequisiteLines(IGenericTrait[] prerequisites) {
    String prerequisiteLines = ""; //$NON-NLS-1$
    for (IGenericTrait prerequisite : prerequisites) {
      prerequisiteLines = prerequisiteLines.concat(resources.getString("CharmTreeView.ToolTip.Minimum")); //$NON-NLS-1$
      prerequisiteLines = prerequisiteLines.concat(IMagicStringBuilderConstants.Space);
      prerequisiteLines = prerequisiteLines.concat(resources.getString(prerequisite.getType().getId()));
      prerequisiteLines = prerequisiteLines.concat(IMagicStringBuilderConstants.ColonSpace);
      prerequisiteLines = prerequisiteLines.concat(String.valueOf(prerequisite.getCurrentValue()));
      prerequisiteLines = prerequisiteLines.concat(HtmlLineBreak);
    }
    return prerequisiteLines;
  }
}