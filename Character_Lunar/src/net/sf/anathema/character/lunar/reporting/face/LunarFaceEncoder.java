package net.sf.anathema.character.lunar.reporting.face;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.lunar.renown.RenownTemplate;
import net.sf.anathema.character.lunar.renown.presenter.IRenownModel;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractFixedLineStatsTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.statstable.IStatsGroup;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class LunarFaceEncoder extends AbstractFixedLineStatsTableEncoder<IFaceStats> implements IPdfContentBoxEncoder {

  public LunarFaceEncoder(BaseFont baseFont, IResources resources) {
    super(baseFont);
    this.resources = resources;
  }

  private static final int LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final int columnCount = 1;
  private final IResources resources;

  @Override
  protected IStatsGroup<IFaceStats>[] createStatsGroups(IGenericCharacter character) {
    return new IStatsGroup[] { new FaceNameStatsGroup(resources), new FaceValueStatsGroup() };
  }

  @Override
  protected IFaceStats[] getPrintStats(IGenericCharacter character) {
    IRenownModel model = (IRenownModel) character.getAdditionalModel(RenownTemplate.TEMPLATE_ID);
    List<IFaceStats> stats = new ArrayList<IFaceStats>();
    for (final IGenericTrait trait : model.getAllTraits()) {
      stats.add(new IFaceStats() {
        public String getValue() {
          return String.valueOf(trait.getCurrentValue());
        }

        public IIdentificate getName() {
          return trait.getType();
        }
      });
    }
    return stats.toArray(new IFaceStats[stats.size()]);
  }

  @Override
  protected int getLineCount() {
    return 5;
  }

  public String getHeaderKey() {
    return "Lunar.Face"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    encodeTable(directContent, character, bounds);
  }
}