package net.sf.anathema.character.reporting.pdf;

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
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.description.MagicDescription;
import net.sf.anathema.character.impl.generic.GenericCharacter;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.CharmStats;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.SpellStats;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.reporting.ReportException;
import net.sf.anathema.framework.reporting.pdf.AbstractPdfReport;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.lib.resources.IResources;

import static java.text.MessageFormat.format;

public class MagicReport extends AbstractPdfReport {

  private final IResources resources;
  private final IAnathemaModel model;
  private final MagicPartFactory partFactory;

  public MagicReport(IResources resources, IAnathemaModel model) {
    this.resources = resources;
    this.model = model;
    partFactory = new MagicPartFactory(new PdfReportUtils());
  }

  @Override
  public String toString() {
    return resources.getString("MagicReport.Name"); //$NON-NLS-1$
  }

  @Override
  public void performPrint(IItem item, Document document, PdfWriter writer) throws ReportException {
    MultiColumnText columnText = new MultiColumnText(document.top() - document.bottom() - 15);
    columnText.addRegularColumns(document.left(), document.right(), 20, 2);
    ICharacter character = (ICharacter) item.getItemData();
    try {
      printCharms(columnText, character);
      printSpells(columnText, character);
      writeColumnText(document, columnText);
    } catch (DocumentException e) {
      throw new ReportException(e);
    }
  }

  private void printSpells(MultiColumnText columnText, ICharacter character) throws DocumentException {
    String currentGroup = "";
    for (ISpell spell : getCurrentSpells(character)) {
      SpellStats spellStats = createSpellStats(spell);
      String nextGroupName = format("{0} {1}", spellStats.getType(resources), spellStats.getGroupName(resources));
      if (!currentGroup.equals(nextGroupName)) {
        currentGroup = nextGroupName;
        columnText.addElement(partFactory.createGroupTitle(currentGroup));
      }
      addMagicName(spell, columnText);
      addSpellCost(spell, columnText);
      addSpellTarget(spellStats, columnText);
      addCharmDescription(spell, columnText);
    }
  }

  public void printCharms(MultiColumnText columnText, ICharacter character) throws DocumentException {
    String currentGroup = "";
    for (ICharm charm : getCurrentCharms(character)) {
      CharmStats charmStats = createCharmStats(character, charm);
      if (!currentGroup.equals(charmStats.getGroupName(resources))) {
        currentGroup = charmStats.getGroupName(resources);
        columnText.addElement(partFactory.createGroupTitle(currentGroup));
      }
      addMagicName(charm, columnText);
      addCharmData(charmStats, charm, columnText);
      addCharmDescription(charm, columnText);
    }
  }

  private void addSpellCost(ISpell charm, MultiColumnText columnText) throws DocumentException {
    String costsLabel = resources.getString("MagicReport.Costs.Label") + ": ";
    String costsValue = new ScreenDisplayInfoStringBuilder(resources).createCostString(charm);
    columnText.addElement(partFactory.createDataPhrase(costsLabel, costsValue));
  }

  private void addSpellTarget(SpellStats spellStats, MultiColumnText columnText) throws DocumentException {
    String targetLabel = resources.getString("MagicReport.Target.Label") + ": ";
    String target = Joiner.on(", ").join(spellStats.getDetailStrings(resources));
    columnText.addElement(partFactory.createDataPhrase(targetLabel, target));
  }

  private void addMagicName(IMagic magic, MultiColumnText columnText) throws DocumentException {
    String charmName = resources.getString(magic.getId());
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
    String costsLabel = resources.getString("MagicReport.Costs.Label") + ": ";
    String costsValue = new ScreenDisplayInfoStringBuilder(resources).createCostString(charm);
    table.addCell(partFactory.createDataCell(costsLabel, costsValue));
  }

  private void addTypeCell(ICharm charm, PdfPTable table) {
    String typeLabel = resources.getString("MagicReport.Type.Label") + ": ";
    String typeValue = new VerboseCharmTypeStringBuilder(resources).createTypeString(charm.getCharmTypeModel());
    table.addCell(partFactory.createDataCell(typeLabel, typeValue));
  }

  private void addKeywordsRow(CharmStats charmStats, PdfPTable table) {
    String keywords = Joiner.on(", ").join(charmStats.getDetailStrings(resources));
    String keywordsLabel = resources.getString("MagicReport.Keywords.Label") + ": ";
    table.addCell(partFactory.createDoubleDataCell(keywordsLabel, keywords));
  }

  private void addDurationRow(CharmStats charmStats, PdfPTable table) {
    String durationLabel = resources.getString("MagicReport.Duration.Label") + ": ";
    String durationString = charmStats.getDurationString(resources);
    table.addCell(partFactory.createDoubleDataCell(durationLabel, durationString));
  }

  private void addCharmDescription(IMagic magic, MultiColumnText columnText) throws DocumentException {
    MagicDescription charmDescription = getCharmDescription(magic);
    if (charmDescription.isEmpty()) {
      String sourceString = new MagicSourceStringBuilder<IMagic>(resources).createSourceString(magic);
      String sourceReference = resources.getString("MagicReport.See.Source", sourceString);
      columnText.addElement(partFactory.createDescriptionParagraph(sourceReference));
    }
    for (String paragraph : charmDescription.getParagraphs()) {
      columnText.addElement(partFactory.createDescriptionParagraph(paragraph));
    }
  }

  private CharmStats createCharmStats(ICharacter character, ICharm charm) {
    return new CharmStats(charm, createGenericCharacter(character));
  }

  private SpellStats createSpellStats(ISpell spell) {
    return new SpellStats(spell);
  }

  private GenericCharacter createGenericCharacter(ICharacter character) {
    return new GenericCharacter(character.getStatistics());
  }

  private MagicDescription getCharmDescription(IMagic magic) {
    return CharmDescriptionProviderExtractor.CreateFor(model, resources).getCharmDescription(magic);
  }

  private void writeColumnText(Document document, MultiColumnText columnText) throws DocumentException {
    do {
      document.add(columnText);
      columnText.nextColumn();
    } while (columnText.isOverflow());
  }

  @Override
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

  private ISpell[] getCurrentSpells(ICharacter character) {
    return character.getStatistics().getSpells().getLearnedSpells(character.getStatistics().isExperienced());
  }

  private ICharm[] getCurrentCharms(ICharacter character) {
    return character.getStatistics().getCharms().getLearnedCharms(character.getStatistics().isExperienced());
  }
}
