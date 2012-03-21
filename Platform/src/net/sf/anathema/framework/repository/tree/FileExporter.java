package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.repository.access.IRepositoryFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.lib.resources.IResources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileExporter {


  private RepositoryZipPathCreator creator;
  private ExportModel model;
  private IResources resources;

  public FileExporter(RepositoryZipPathCreator repositoryZipPathCreator, ExportModel model, IResources resources) {
    this.creator = repositoryZipPathCreator;
    this.model = model;
    this.resources = resources;
  }

  public PrintNameFile[] exportToZip(File saveFile) throws IOException {
    ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(saveFile));
    zipOutputStream.setComment(resources.getString("Anathema.Version.Numeric")); //$NON-NLS-1$
    PrintNameFile[] printNameFiles = model.getPrintNameFilesInSelection();
    for (PrintNameFile printNameFile : printNameFiles) {
      IRepositoryFileAccess access = model.getFileAccess(printNameFile);
      for (File file : access.getFiles()) {
        ZipEntry entry = createZipEntry(file, printNameFile);
        zipOutputStream.putNextEntry(entry);
        StreamUtilities.writeInputStreamToOutputStream(access.openInputStream(file), zipOutputStream);
        zipOutputStream.closeEntry();
      }
    }
    zipOutputStream.close();
    return printNameFiles;
  }

  ZipEntry createZipEntry(File file, PrintNameFile printNameFile) {
    ZipEntry entry = new ZipEntry(creator.createZipPath(file));
    entry.setComment(resources.getString(
            "Anathema.Version.Numeric") + "#" + printNameFile.getItemType() + "#" + printNameFile.getRepositoryId()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return entry;
  }
}