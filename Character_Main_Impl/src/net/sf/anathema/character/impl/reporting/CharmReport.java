package net.sf.anathema.character.impl.reporting;

import com.google.common.base.Joiner;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.MultiColumnText;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
    return resources.getString("CharacterModule.Reporting.Charms.Name"); //$NON-NLS-1$
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

  private void printCharms(MultiColumnText columnText, ICharacter character) throws DocumentException {
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
      addCharmData(charmStats, columnText);
      addCharmDescription(charmStats, charm, columnText);
    }
  }

  private void addCharmName(ICharm charm, MultiColumnText columnText) throws DocumentException {
    String charmName = resources.getString(charm.getId());
    columnText.addElement(partFactory.createCharmTitle(charmName));
  }

  private void addCharmData(CharmStats charmStats, MultiColumnText columnText) throws DocumentException {
    PdfPTable table = partFactory.createDataTable();
    table.addCell(partFactory.createDataCell("Costs: ", charmStats.getCostString(resources)));
    table.addCell(partFactory.createDataCell("Type: ", charmStats.getType(resources)));
    String details = Joiner.on(',').join(charmStats.getDetailStrings(resources));
    table.addCell(partFactory.createDoubleDataCell("Keywords: ", details));
    table.addCell(partFactory.createDoubleDataCell("Duration: ", charmStats.getDurationString(resources)));
    columnText.addElement(table);
  }

  private void addCharmDescription(CharmStats charmStats, ICharm charm, MultiColumnText columnText)
          throws DocumentException {
    CharmDescription charmDescription = getCharmDescription(charm);
    if (charmDescription.isEmpty()) {
      String text = "See: " + charmStats.getSourceString(resources);
      columnText.addElement(partFactory.createDescriptionParagraph(text));
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
