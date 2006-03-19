package net.sf.anathema.development.character.charm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.charmentry.util.CharmUtilities;
import net.sf.anathema.development.properties.PropertiesFilter;
import net.sf.anathema.lib.gui.file.FileChoosingUtilities;
import net.sf.anathema.lib.io.file.XMLFilter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.Ostermiller.util.CSVParser;

/**
 * Converts a comma separated variable file of Charms to the format used by Anathema. After running, the charm group
 * entries, martial arts levels and prerequisite Charms have to be added manually. In addition, words like "mote" or
 * "motes" have to be removed from the target file for Anathema to produce proper results. From the possible range of
 * permanent cost elements, only experience is properly supported, all others are added as temporary costs. Finally,
 * Martial Arts Charms should be moved to their respective XML-file. Expected CSV order is: Name,Min. Prerequisites,Min.
 * Essence,Essence Cost,Willpower Cost,Other Cost,Duration,Type,Page,Source
 */
public class CharmCSVToXMLConverter {

  private CharacterType exaltType;

  private CharmCSVToXMLConverter() throws IOException {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      // Nothing to do
    }
    File startDirectory = new File("../Character_Core/resources/data");
    File sourceFile = FileChoosingUtilities.chooseFile(
        "Open initial CSV Charm File", javax.swing.JOptionPane.getRootFrame(), new CSVFilter(), startDirectory); //$NON-NLS-1$
    File targetFile = FileChoosingUtilities.chooseFile(
        "Save final XML Charm File", javax.swing.JOptionPane.getRootFrame(), new XMLFilter(), startDirectory); //$NON-NLS-1$
    File namePropertiesFile = FileChoosingUtilities.chooseFile(
        "Save Properties File", javax.swing.JOptionPane.getRootFrame(), new PropertiesFilter(), startDirectory); //$NON-NLS-1$
    exaltType = (CharacterType) JOptionPane.showInputDialog(
        null,
        "Choose Character Type:", "Character Type", JOptionPane.QUESTION_MESSAGE, null, CharacterType.values(), null); //$NON-NLS-1$ //$NON-NLS-2$
    Properties charmNameProperties = new Properties();
    String[][] allCharms = readCharms(new FileInputStream(sourceFile));
    Document targetDocument = writeCharms(allCharms, charmNameProperties);
    XMLWriter xmlWriter = new XMLWriter(new FileWriter(targetFile), new OutputFormat("  ", true)); //$NON-NLS-1$
    xmlWriter.write(targetDocument);
    xmlWriter.flush();
    xmlWriter.close();
    charmNameProperties.store(new FileOutputStream(namePropertiesFile), "Default " + exaltType.getId() + " Charm Names"); //$NON-NLS-1$ //$NON-NLS-2$
    System.out.println("Conversion succesful"); //$NON-NLS-1$
  }

  public static void main(String[] args) throws FileNotFoundException, IOException {
    new CharmCSVToXMLConverter();
  }

  private String[][] readCharms(InputStream csvStream) throws IOException {
    CSVParser parser = new CSVParser(csvStream);
    String[][] allValues = parser.getAllValues();
    parser.close();
    return allValues;
  }

  private Document writeCharms(String[][] allCharms, Properties properties) {
    Document document = DocumentHelper.createDocument();
    Element charmlist = document.addElement("charmlist"); //$NON-NLS-1$
    for (String[] charm : allCharms) {
      Element charmElement = charmlist.addElement("charm"); //$NON-NLS-1$
      String id = CharmUtilities.createIDFromName(exaltType, charm[0]);
      properties.setProperty(id, charm[0]);
      charmElement.addAttribute("id", id); //$NON-NLS-1$
      charmElement.addAttribute("exalt", exaltType.getId()); //$NON-NLS-1$
      createCharmData(charm, charmElement);
    }
    return document;
  }

  private void createCharmData(String[] charm, Element parentElement) {
    try {
      Element prerequisiteElement = parentElement.addElement("prerequisite"); //$NON-NLS-1$
      createPrerequisiteElements(charm, prerequisiteElement);
      Element costElement = parentElement.addElement("cost"); //$NON-NLS-1$                    
      createTemporaryCostElements(charm, costElement);
      createPermanentCostElements(charm, costElement);
      Element durationElement = parentElement.addElement("duration"); //$NON-NLS-1$
      durationElement.addAttribute("duration", charm[6]); //$NON-NLS-1$
      Element typeElement = parentElement.addElement("type"); //$NON-NLS-1$
      typeElement.addAttribute("type", charm[7]); //$NON-NLS-1$    
      String source = charm[9];
      if (source != "") { //$NON-NLS-1$
        Element sourceElement = parentElement.addElement("source"); //$NON-NLS-1$
        sourceElement.addAttribute("source", source); //$NON-NLS-1$
        String page = charm[8];
        if (page != "") { //$NON-NLS-1$
          sourceElement.addAttribute("page", page); //$NON-NLS-1$
        }
      }
    }
    catch (Exception e) {
      System.out.println("Error on Charm: " + charm[0] + "."); //$NON-NLS-1$//$NON-NLS-2$
      e.printStackTrace();
    }
  }

  private void createPrerequisiteElements(String[] charm, Element parentElement) {
    String[] prereqArray = charm[1].split(","); //$NON-NLS-1$
    for (int i = 0; i < prereqArray.length; i++) {
      String currentPrereq[] = prereqArray[i].split(" "); //$NON-NLS-1$
      if (currentPrereq.length > 2) {
        throw new IllegalArgumentException("Charm " + charm[0] + " has invalid Minimum Prerequisites."); //$NON-NLS-1$//$NON-NLS-2$
      }
      Element prerequisite = parentElement.addElement("prerequisite"); //$NON-NLS-1$
      prerequisite.addAttribute("id", currentPrereq[0]); //$NON-NLS-1$
      prerequisite.addAttribute("value", currentPrereq[1]); //$NON-NLS-1$
    }
    Element essence = parentElement.addElement("essence"); //$NON-NLS-1$
    essence.addAttribute("value", charm[2].split(" ")[1]); //$NON-NLS-1$ //$NON-NLS-2$
  }

  private void createTemporaryCostElements(String[] charm, Element parentElement) {
    Element temporaryElement = parentElement.addElement("temporary"); //$NON-NLS-1$
    Element essenceElement = temporaryElement.addElement("essence"); //$NON-NLS-1$
    String[] cost = charm[3].split(" "); //$NON-NLS-1$
    setCostAttributes(essenceElement, cost);
    String willpowerCost = charm[4];
    if (willpowerCost != "") { //$NON-NLS-1$
      Element willpowerElement = temporaryElement.addElement("willpower"); //$NON-NLS-1$
      cost = willpowerCost.split(" "); //$NON-NLS-1$
      setCostAttributes(willpowerElement, cost);
    }
    String otherCost = charm[5];
    if (otherCost != "") { //$NON-NLS-1$
      if (otherCost.indexOf("health level") != -1) { //$NON-NLS-1$
        int index = otherCost.indexOf("health level"); //$NON-NLS-1$
        Element healthElement = temporaryElement.addElement("health"); //$NON-NLS-1$
        cost = otherCost.substring(0, index).split(" "); //$NON-NLS-1$
        healthElement.addAttribute("cost", cost[0]); //$NON-NLS-1$                
      }
    }
  }

  private void createPermanentCostElements(String[] charm, Element parentElement) {
    String otherCost = charm[5];
    if (otherCost != "") { //$NON-NLS-1$
      int index = otherCost.indexOf("Experience"); //$NON-NLS-1$
      if (index != -1) { //$NON-NLS-1$        
        createExperienceElement(parentElement, otherCost, index);
      }
      else {
        index = otherCost.indexOf("experience"); //$NON-NLS-1$
        if (index != -1) { //$NON-NLS-1$        
          createExperienceElement(parentElement, otherCost, index);
        }
      }
    }
  }

  private void createExperienceElement(Element parentElement, String otherCost, int index) {
    Element permanentElement = parentElement.addElement("permanent"); //$NON-NLS-1$
    Element XPElement = permanentElement.addElement("experience"); //$NON-NLS-1$
    String[] cost = otherCost.substring(0, index).split(" "); //$NON-NLS-1$
    XPElement.addAttribute("cost", cost[0]); //$NON-NLS-1$                
  }

  private void setCostAttributes(Element element, String[] splitCost) {
    element.addAttribute("cost", splitCost[0]); //$NON-NLS-1$
    if (splitCost.length > 2) {
      String text = new String();
      for (int i = 1; i < splitCost.length; i++) {
        text = text.concat(splitCost[i]);
        if (i < splitCost.length - 1) {
          text = text.concat(" "); //$NON-NLS-1$
        }
      }
      element.addAttribute("text", text); //$NON-NLS-1$
    }
  }
}