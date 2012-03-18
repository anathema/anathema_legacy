package net.sf.anathema.character.impl.reporting;

import com.google.common.base.Joiner;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.MultiColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.ScreenDisplayInfoStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.source.MagicSourceStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.type.VerboseCharmTypeStringBuilder;
import net.sf.anathema.character.generic.framework.magic.view.CharmDescriptionProviderExtractor;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.description.CharmDescription;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.impl.model.advance.ExperiencePointManagement;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.CharmStats;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

public class CharmReport extends AbstractPdfReport {

  private final IResources resources;
  private final IAnathemaModel model;
  private final CharmPartFactory partFactory;

  public CharmReport(IResources resources, IAnathemaModel model) {
    this.resources = resources;
    this.model = model;
    partFactory = new CharmPartFactory(new PdfReportUtils());
  }

  @Override
  public String toString() {
    return resources.getString("CharmsReport.Name"); //$NON-NLS-1$
  }

  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    MultiColumnText columnText = new MultiColumnText(document.top() - document.bottom() - 15);
    columnText.addRegularColumns(document.left(), document.right(), 20, 2);
    ICharacter character = (ICharacter) item.getItemData();
    try {
      printCharms(columnText, character);
      writeColumnText(document, columnText);
    } catch (DocumentException e) {
      throw new ReportException(e);
    }
  }

  public void printCharms(MultiColumnText columnText, ICharacter character) throws DocumentException {
    String currentGroup = "";
    boolean isFirst = true;
    for (ICharm charm : getCurrentCharms(character)) {
      CharmStats charmStats = createCharmStats(character, charm);
      if (!currentGroup.equals(charm.getGroupId())) {
        currentGroup = charmStats.getGroupName(resources);
        columnText.addElement(
                isFirst ? partFactory.createFirstGroupTitle(currentGroup) : partFactory.createGroupTitle(currentGroup));
        isFirst = false;
      }
      addCharmName(charm, columnText);
      addCharmData(charmStats, charm, columnText);
      addCharmDescription(charm, columnText);
    }
  }

  private void addCharmName(ICharm charm, MultiColumnText columnText) throws DocumentException {
    String charmName = resources.getString(charm.getId());
    columnText.addElement(partFactory.createCharmTitle(charmName));
  }

  private void addCharmData(CharmStats charmStats, ICharm charm, MultiColumnText columnText) throws DocumentException {
    PdfPTable table = partFactory.createDataTable();
    addCostsCell(charm, table);
    addTypeCell(charm, table);
    addKeywordsRow(charmStats, table);
    addDurationRow(charmStats, table);
    columnText.addElement(table);
  }

  private void addCostsCell(ICharm charm, PdfPTable table) {
    String costsLabel = resources.getString("CharmReport.Costs.Label") + ": ";
    String costsValue = new ScreenDisplayInfoStringBuilder(resources).createCostString(charm);
    table.addCell(partFactory.createDataCell(costsLabel, costsValue));
  }

  private void addTypeCell(ICharm charm, PdfPTable table) {
    String typeLabel = resources.getString("CharmReport.Type.Label") + ": ";
    String typeValue = new VerboseCharmTypeStringBuilder(resources).createTypeString(charm.getCharmTypeModel());
    table.addCell(partFactory.createDataCell(typeLabel, typeValue));
  }

  private void addKeywordsRow(CharmStats charmStats, PdfPTable table) {
    String keywords = Joiner.on(',').join(charmStats.getDetailStrings(resources));
    String keywordsLabel = resources.getString("CharmReport.Keywords.Label") + ": ";
    table.addCell(partFactory.createDoubleDataCell(keywordsLabel, keywords));
  }

  private void addDurationRow(CharmStats charmStats, PdfPTable table) {
    String durationLabel = resources.getString("CharmReport.Duration.Label") + ": ";
    String durationString = charmStats.getDurationString(resources);
    table.addCell(partFactory.createDoubleDataCell(durationLabel, durationString));
  }

  private void addCharmDescription(ICharm charm, MultiColumnText columnText) throws DocumentException {
    CharmDescription charmDescription = getCharmDescription(charm);
    if (charmDescription.isEmpty()) {
      String sourceString = new MagicSourceStringBuilder<ICharm>(resources).createSourceString(charm);
      String sourceReference = resources.getString("CharmReport.See.Source", sourceString);
      columnText.addElement(partFactory.createDescriptionParagraph(sourceReference));
    }
    for (String paragraph : charmDescription.getParagraphs()) {
      columnText.addElement(partFactory.createDescriptionParagraph(paragraph));
    }
  }

  private CharmStats createCharmStats(ICharacter character, ICharm charm) {
    return new CharmStats(charm, createGenericCharacter(character));
  }

  private GenericCharacter createGenericCharacter(ICharacter character) {
    return new GenericCharacter(character.getStatistics(), new ExperiencePointManagement(character.getStatistics()));
  }

  private CharmDescription getCharmDescription(IMagic magic) {
    return CharmDescriptionProviderExtractor.CreateFor(model, resources).getCharmDescription(magic);
  }

  private void writeColumnText(Document document, MultiColumnText columnText) throws DocumentException {
    do {
      document.add(columnText);
      columnText.nextColumn();
    } while (columnText.isOverflow());
  }

  public boolean supports(IItem item) {
    if (item == null || !(item.getItemData() instanceof ICharacter)) {
      return false;
    }
    ICharacter character = (ICharacter) item.getItemData();
    if (!character.hasStatistics()) {
      return false;
    }
    return getCurrentCharms(character).length > 0;
  }

  private ICharm[] getCurrentCharms(ICharacter character) {
    return character.getStatistics().getCharms().getLearnedCharms(character.getStatistics().isExperienced());
  }
}
