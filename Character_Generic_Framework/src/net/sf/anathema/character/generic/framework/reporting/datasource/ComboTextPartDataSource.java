package net.sf.anathema.character.generic.framework.reporting.datasource;

import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.framework.reporting.IReportDataSource;
import net.sf.anathema.framework.reporting.encoding.TextEncoding;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;
import net.sf.anathema.lib.resources.IResources;

public class ComboTextPartDataSource implements IReportDataSource {

  private static final String SIMPLE_LINE_BREAK = "\n"; //$NON-NLS-1$
  private static final int maximumNumberOfComboTextParts = 25;
  private final ITextPart[] textParts;
  private final IResources resources;
  private final IGenericCombo[] allCombos;

  public ComboTextPartDataSource(IResources resources, IGenericCharacter character) {
    this.resources = resources;
    this.allCombos = character.getCombos();
    textParts = createComboTextParts(resources, allCombos);
  }

  public int getRowCount() {
    return textParts.length;
  }

  public Object getValue(int currentRow, String columnName) {
    return textParts[currentRow];
  }

  private static ITextPart[] createComboTextParts(IResources resources, IGenericCombo[] combos) {
    List<ITextPart> textPartList = new ArrayList<ITextPart>();
    TextFormat nameTextFormat = new TextFormat(FontStyle.PLAIN, false, 7);
    TextFormat charmTextFormat = new TextFormat(FontStyle.PLAIN, false, 6);
    for (IGenericCombo combo : combos) {
      if (textPartList.size() + combo.getCharms().length + 1 >= maximumNumberOfComboTextParts) {
        continue;
      }

      textPartList.add(new TextPart(combo.getName(), nameTextFormat));
      for (ICharm charm : combo.getCharms()) {
        textPartList.add(new TextPart("   " + resources.getString(charm.getId()), charmTextFormat)); //$NON-NLS-1$
      }
    }
    return textPartList.toArray(new ITextPart[textPartList.size()]);
  }

  public String getStyledCData() {
    if (textParts.length == 0) {
      return null;
    }
    List<ITextPart> textPartList = new ArrayList<ITextPart>();
    TextFormat nameTextFormat = new TextFormat(FontStyle.PLAIN, false, 7);
    TextFormat charmTextFormat = new TextFormat(FontStyle.PLAIN, false, 6);
    for (IGenericCombo combo : allCombos) {
      if (textPartList.size() + combo.getCharms().length + 1 >= maximumNumberOfComboTextParts) {
        continue;
      }
      if (textPartList.size() > 0) {
        textPartList.add(new TextPart(SIMPLE_LINE_BREAK, new TextFormat(FontStyle.PLAIN, false, 3)));
      }
      textPartList.add(new TextPart(combo.getName() + SIMPLE_LINE_BREAK, nameTextFormat));
      for (ICharm charm : combo.getCharms()) {
        textPartList.add(new TextPart("   " + resources.getString(charm.getId()) + SIMPLE_LINE_BREAK, charmTextFormat)); //$NON-NLS-1$
      }
    }
    ITextPart[] dynamicTextParts = textPartList.toArray(new ITextPart[textPartList.size()]);
    return TextEncoding.createDynamicStyledCData(dynamicTextParts);
  }
}