package net.sf.anathema.development.character.charm;

import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class GroupAdder {

  private File chooseFile(String confirm, Frame frame) {
    JFileChooser chooser = new JFileChooser(new File("../Character_Core/resources/data")); //$NON-NLS-1$
    chooser.setFileFilter(new XMLFilter());
    chooser.showDialog(frame, confirm);
    return chooser.getSelectedFile();
  }

  private GroupAdder() throws IOException, DocumentException {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      // Nothing to do
    }
    File sourceFile = chooseFile("Open initial XML Charm File", javax.swing.JOptionPane.getRootFrame()); //$NON-NLS-1$
    File targetFile = chooseFile("Save final XML Charm File", javax.swing.JOptionPane.getRootFrame()); //$NON-NLS-1$

    Document sourceDocument = new SAXReader().read(sourceFile);

    Element charmListElement = sourceDocument.getRootElement();
    List<Element> elementList = ElementUtilities.elements(charmListElement);
    for (Iterator<Element> iter = elementList.iterator(); iter.hasNext();) {
      Element charmElement = iter.next();
      String groupId = charmElement.element("prerequisite").element("prerequisite").attributeValue("id"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      charmElement.addAttribute("group", groupId); //$NON-NLS-1$
    }
    XMLWriter xmlWriter = new XMLWriter(new FileWriter(targetFile), new OutputFormat("  ", false)); //$NON-NLS-1$
    xmlWriter.write(sourceDocument);
    xmlWriter.flush();
    xmlWriter.close();
    System.out.println("Done adding groups!"); //$NON-NLS-1$
  }

  public static void main(String[] args) throws IOException, DocumentException {
    new GroupAdder();
  }
}