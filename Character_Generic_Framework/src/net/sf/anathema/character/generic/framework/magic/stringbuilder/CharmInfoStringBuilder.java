package net.sf.anathema.character.generic.framework.magic.stringbuilder;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMartialArtsCharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.lib.resources.IResources;

public class CharmInfoStringBuilder implements ICharmInfoStringBuilder {

  private static final String HtmlLineBreak = "<br>"; //$NON-NLS-1$
  private final IMagicInfoStringBuilder costStringBuilder;
  private final IMagicSourceStringBuilder sourceStringBuilder;
  private final IResources resources;

  public CharmInfoStringBuilder(IResources resources) {
    this.resources = resources;
    costStringBuilder = new ScreenDisplayInfoStringBuilder(resources);
    sourceStringBuilder = new MagicSourceStringBuilder(resources);
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
    builder.append(charm.getDuration().getText(resources));
    builder.append(HtmlLineBreak);
    builder.append(resources.getString("CharmTreeView.ToolTip.Type")); //$NON-NLS-1$
    builder.append(IMagicStringBuilderConstants.ColonSpace);
    builder.append(resources.getString(charm.getCharmType().getId()));
    builder.append(HtmlLineBreak);
    if (MartialArtsUtilities.isMartialArtsCharm(charm)) {
      builder.append(createMartialArtsLevelLine((IMartialArtsCharm) charm));
    }
    builder.append(createPrerequisiteLines(charm.getPrerequisites()));
    builder.append(createPrerequisiteLines(new IGenericTrait[] { charm.getEssence() }));
    builder.append(sourceStringBuilder.createSourceString(charm, true));
    builder.append("</body></html>"); //$NON-NLS-1$
    return builder.toString();

  }

  private String createMartialArtsLevelLine(IMartialArtsCharm charm) {
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