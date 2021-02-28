package com.ssepan.application;

import java.beans.*;
import java.io.*;
//import java.io.File;
import java.util.logging.Level;
import javax.swing.JComponent;
import com.ssepan.utility.*;
//NOTE:may reference libini4j-java
import org.ini4j.*;//https://ourcodeworld.com/articles/read/839/how-to-read-parse-from-and-write-to-ini-files-easily-in-java

/**
 *
 * @author ssepan
 */
public class ModelBase
     extends JComponent
{
    // <editor-fold defaultstate="collapsed" desc="Declarations">
    protected /*final*/ PropertyChangeSupport propertyChangeSupport;
    //protected /*final*/ VetoableChangeSupport vetoableChangeSupport;
    
    
    public static String KEY_NEW = "(new)";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public ModelBase()
    {
        String sStatusMessage="";
        String sErrorMessage="";

        try {
            this.propertyChangeSupport = new PropertyChangeSupport(this);
            //this.vetoableChangeSupport = new VetoableChangeSupport(this);

            Dirty = false;
            //Key = KEY_NEW;//NOTE:if set here, will not trigger change when set in refersh in new
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Properties">
    //Note: "Default serialization will not serialize 'transient' and 'static' fields."

    private String KeyOld = "";//KEY_NEW;
    private String Key = "";//KEY_NEW;
    public String getKey()
    {
        String sStatusMessage="";
        String sErrorMessage="";
        String returnValue="";

        try {
            returnValue=Key;
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this

        }
        return returnValue;
    }
    public void setKey(String value)// throws PropertyVetoException
    {
        String sStatusMessage="";
        String sErrorMessage="";

        try {
            KeyOld = Key;
            //vetoableChangeSupport.fireVetoableChange("Key", KeyOld, value);
            Key = value;
            propertyChangeSupport.firePropertyChange("Key", KeyOld, Key);
            setDirty(true);
            System.out.println(String.format("setKey: before='%s', after='%s'",KeyOld,Key));
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this

        }
    }

    private Boolean DirtyOld = false;
    private Boolean Dirty = false;
    public Boolean isDirty()
    {
        String sStatusMessage="";
        String sErrorMessage="";
        Boolean returnValue=false;

        try {
            returnValue=Dirty;
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this

        }
        return returnValue;
    }
    public void setDirty(Boolean value)// throws PropertyVetoException
    {
        String sStatusMessage="";
        String sErrorMessage="";

        try {
            DirtyOld = Dirty;
            //vetoableChangeSupport.fireVetoableChange("Dirty", DirtyOld, value);
            Dirty = value;
            propertyChangeSupport.firePropertyChange("Dirty", DirtyOld, Dirty);
            System.out.println(String.format("setSomeBooleanField: before='%s', after='%s'",DirtyOld.toString(),Dirty.toString()));
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this

        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="PropertyChangeSupport">
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) 
    {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) 
    {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) 
    {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) 
    {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="VetoableChangeSupport">
//    @Override
//    public void addVetoableChangeListener(VetoableChangeListener listener) 
//    {
//        vetoableChangeSupport.addVetoableChangeListener(listener);
//    }
//    
//    @Override
//    public void removeVetoableChangeListener(VetoableChangeListener listener) 
//    {
//        vetoableChangeSupport.removeVetoableChangeListener(listener);
//    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="XML IO">
    public static void Write
    (
        String filepath,
        ModelBase model
    ) 
        throws FileNotFoundException
    {
        try 
        (
            XMLEncoder encoder = new XMLEncoder
            (
                new BufferedOutputStream
                (
                    new FileOutputStream(filepath)//"Beanarchive.xml"
                )
            )
        )
        { 
            encoder.writeObject(model);
        }
    }
    
    public static ModelBase Read
    (
        String filepath
    ) 
        throws FileNotFoundException
    {
        ModelBase returnValue;
        try 
        (
            XMLDecoder decoder = new XMLDecoder
            (
                new BufferedInputStream
                (
                    new FileInputStream(filepath)//"Beanarchive.xml"
                )
            )
        )
        {        
            returnValue = (ModelBase)decoder.readObject();
        }
        
        return returnValue;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="INI IO">
    public static Boolean WriteIni
    (
        String filepath,
        ModelBase model
    ) 
        throws FileNotFoundException
    {
//        Wini ini = new Wini(new File("C:\\Users\\sdkca\\Desktop\\myinifile.ini"));
//
//        ini.put("block_name", "property_name", "value");
//        ini.put("block_name", "property_name_2", 45.6);
//        ini.store();
        
        return true;
    }

    
    public static Boolean ReadIni
    (
        String filepath,
        ModelBase model
    ) 
        throws FileNotFoundException
    {
//        Wini ini = new Wini(new File("C:\\Users\\sdkca\\Desktop\\myinifile.ini"));
//        
//        int age = ini.get("owner", "age", int.class);
//        double height = ini.get("owner", "height", double.class);
//        String server = ini.get("database", "server");

        
        return true;
    }

    // </editor-fold>
}
