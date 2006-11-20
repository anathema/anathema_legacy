package net.sf.anathema.character.lunar.reporting.heartsblood;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.lunar.heartsblood.HeartsBloodTemplate;
import net.sf.anathema.character.lunar.heartsblood.presenter.IAnimalForm;
import net.sf.anathema.character.lunar.heartsblood.presenter.IHeartsBloodModel;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractFixedLineStatsTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.statstable.IStatsGroup;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class LunarHeartsBloodEncoder extends AbstractFixedLineStatsTableEncoder<IHeartsBloodStats> implements
    IPdfContentBoxEncoder {

  public LunarHeartsBloodEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  private final IResources resources;
  private static final int LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final int columnCount = 1;

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    encodeTable(directContent, character, bounds);
  }

  public String getHeaderKey() {
    return "Lunar.HeartsBlood"; //$NON-NLS-1$
  }

  @Override
  protected int getLineCount() {
    return 11;
  }

  @Override
  protected IHeartsBloodStats[] getPrintStats(IGenericCharacter character) {
    IHeartsBloodModel model = (IHeartsBloodModel) character.getAdditionalModel(HeartsBloodTemplate.TEMPLATE_ID);
    List<IHeartsBloodStats> stats = new ArrayList<IHeartsBloodStats>();
    for (final IAnimalForm form : model.getEntries()) {
      stats.add(new IHeartsBloodStats() {
        public IIdentificate getName() {
          return new Identificate(form.getName());
        }

        public String getStaminaString() {
          return String.valueOf(form.getStamina());
        }

        public String getStrengthString() {
          return String.valueOf(form.getStrength());
        }
      });
    }
    return stats.toArray(new IHeartsBloodStats[stats.size()]);
  }

  @Override
  protected IStatsGroup<IHeartsBloodStats>[] createStatsGroups(IGenericCharacter character) {
    return new IStatsGroup[] {
        new HeartsBloodNameStatsGroup(resources),
        new HeartsBloodStrengthStatsGroup(resources),
        new HeartsBloodStaminaStatsGroup(resources) };
  }
}