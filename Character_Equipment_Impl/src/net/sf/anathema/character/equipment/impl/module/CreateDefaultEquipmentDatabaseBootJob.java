package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.ProxySplashscreen;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.character.equipment.impl.item.model.gson.GsonEquipmentDatabase;
import net.sf.anathema.character.equipment.impl.item.model.gson.EquipmentGson;
import net.sf.anathema.character.generic.impl.bootjob.RepositoryVersion;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.IAnathemaView;
import net.sf.anathema.initialization.BootJob;
import net.sf.anathema.initialization.IAnathemaBootJob;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.security.CodeSource;
import java.net.URL;

@BootJob
public class CreateDefaultEquipmentDatabaseBootJob implements IAnathemaBootJob {

    //private final static Logger logger = Logger.getLogger(CreateDefaultEquipmentDatabaseBootJob.class);
    
    private final String EQUIPMENT_REGEX = "^equipment/.*\\.item$";
    private GsonEquipmentDatabase database;

    @Override
    public void run(IResources resources, IAnathemaModel anathemaModel, IAnathemaView view) {
        /* Once bootjob ordering is in place, any of this class' variables, functions with
           the word 'legacy' in it, and any code that calls it, can be safely removed.
           Just make sure this runs *after* DatabaseConversionBootJob. */
        File legacyDatabaseFile = new File( anathemaModel.getRepository().getRepositoryPath() + "equipment/Equipment.yap" );
        
        database = GsonEquipmentDatabase.CreateFrom(anathemaModel);
        EquipmentGson gson = new EquipmentGson();
        
        if( !legacyDatabaseFile.exists() && isDatabaseEmpty() ) {
            ProxySplashscreen.getInstance().displayStatusMessage( resources.getString("Equipment.Bootjob.DefaultDatabaseSplashmessage")); //$NON-NLS-1$
            
            try {
                ZipInputStream jarFile = new ZipInputStream( new BufferedInputStream( getJarFileLocation().openStream() ) );
                ZipEntry file;
                while( (file = jarFile.getNextEntry()) != null ) {
                    if( file.getName().matches( EQUIPMENT_REGEX ) ){
                        String itemJSON = getStringFromStream( jarFile );
                        database.saveTemplate( gson.fromJson( itemJSON ) );
                    }
                }
            } catch ( IOException e ) {
                throw new RuntimeException("Could not create default database.");
            } catch ( NoSuchElementException e ) {
                throw new RuntimeException("Could not create default database.");
            }
        }
    }
    
    private boolean isDatabaseEmpty() {
        return database.getAllAvailableTemplateIds().length == 0;
    }
    
    private URL getJarFileLocation() {
        return this.getClass().getProtectionDomain().getCodeSource().getLocation();
    }
    
    private String getStringFromStream( InputStream stream ) {
        return (new Scanner( stream )).useDelimiter("\\A").next();
    }
}