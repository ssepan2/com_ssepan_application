package com.ssepan.application;

import java.beans.*;
import java.io.*;
//import java.io.File;
import java.util.*; 
import java.util.logging.Level; 
//import javax.swing.JComponent;
import com.ssepan.utility.*;
//NOTE:may reference libini4j-java
import org.ini4j.*;//https://ourcodeworld.com/articles/read/839/how-to-read-parse-from-and-write-to-ini-files-easily-in-java

/**
 *
 * @author ssepan
 */
public class ModelBase
     //extends JComponent
{
    // <editor-fold defaultstate="collapsed" desc="Declarations">
    
    
    public static Boolean DIRTY_NEW = false;
    public static String KEY_NEW = "(new)";

    protected List<IPropertyChanged> Handlers;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public ModelBase()
    {
        String sStatusMessage="";
        String sErrorMessage="";

        try {
            Handlers = new ArrayList<IPropertyChanged>();

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

    private String KeyOld = KEY_NEW;
    private String Key = KEY_NEW;
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
    public void setKey(String value)
    {
        String sStatusMessage="";
        String sErrorMessage="";

        try {
            KeyOld = Key;
            Key = value;
            System.out.println(String.format("setKey: before='%s', after='%s'",KeyOld,Key));
            notifyPropertyChanged("Key");
            setDirty(true);
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this

        }
    }

    private Boolean DirtyOld = DIRTY_NEW;
    private Boolean Dirty = DIRTY_NEW;
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
    protected void setDirty(Boolean value)
    {
        String sStatusMessage="";
        String sErrorMessage="";

        try {
            DirtyOld = Dirty;
            Dirty = value;
            System.out.println(String.format("setDirty: before='%s', after='%s'",DirtyOld.toString(),Dirty.toString()));
            notifyPropertyChanged("Dirty");
        } catch (Exception ex) {
            //sErrorMessage=ex.getMessage();
            Log.write(ex,Level.ALL);
        } finally {
            //always do this

        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="PropertyChangeSupport">
        protected void notifyPropertyChanged(String propertyName ) {

          String sErrorMessage, formatResult;

          try {

               //formatResult=String.format("PropertyChanged firing: '%s'",propertyName);
               //Sytem.out.println(formatResult);

               onNotifyPropertyChanged(propertyName);

          } catch(Exception ex) {
                //sErrorMessage=ex.getMessage();
                Log.write(ex,Level.ALL);
             } finally {

                //formatResult=String.format("PropertyChanged fired: '%s'",propertyName);
                //Sytem.out.println(formatResult);
             };
        };

        public void addHandler(IPropertyChanged f) {

          String sErrorMessage, formatResult;

          
            try {
              //String.format(formatResult,"Handlers.size() (before): '%d'",Handlers.size());
              //Sytem.out.println(formatResult);

              //f("Key");
              if (Handlers.indexOf(f) == -1) {

                   Handlers.add(f);
              };

              //formatResult=String.format("Handlers.size() (after): '%d'",Handlers.size());
              //Sytem.out.println(formatResult);
            } catch (Exception ex) {
                //sErrorMessage=ex.getMessage();
                Log.write(ex,Level.ALL);
            } finally {
                 //
            };
        };

        public void removeHandler(IPropertyChanged f) {

            String sErrorMessage, formatResult;
            IPropertyChanged extracted;
            
            try {
                //String.format(formatResult,"Handlers.length (before): '%d'",Handlers.length);
                //Sytem.out.println(formatResult);

                extracted = Handlers.remove(Handlers.indexOf(f));
                if (extracted==null) {

                  formatResult=String.format("item not extracted from Handlers: '%d'",Handlers.size());
                  System.out.println(formatResult);
                } else {

                  //String.format(formatResult,"Handlers.size() (after): '%d'",Handlers.size());
                  //Sytem.out.println(formatResult);
                };
            } catch (Exception ex) {
                //sErrorMessage=ex.getMessage();
                Log.write(ex,Level.ALL);
            }  finally {
                 //
            };
        };


        protected void onNotifyPropertyChanged(String propertyName) {

          String sErrorMessage, formatResult;
//          IPropertyChanged proc;

          
            try {
              //if (propertyName=="SomeInteger") {
              //
              //  String.format(formatResult,"OnNotifyPropertyChanged: propertyName (before): '%s'",propertyName);
              //  Sytem.out.println(formatResult);
              //};

              for (IPropertyChanged i : Handlers)  {

                   i.propertyChanged(propertyName);
              };
            } catch (Exception ex) {
                sErrorMessage=ex.getMessage();
                System.out.println(sErrorMessage);
                Log.write(ex,Level.ALL);
            } finally {
               //if (propertyName=="SomeInteger") {
               //
               //formatResult=String.format("OnNotifyPropertyChanged: propertyName (after): '%s'",propertyName);
               //  Sytem.out.println(formatResult);
               //};
            };
        };

//        public void testDelegate()
//        {
//            IStringDisplay[] items = new IStringDisplay[3];
//
//            // build the delegates
//            items[0] = (IStringDisplay) ST_DEL.build(new Class1(),"show");
//            items[1] = (IStringDisplay) ST_DEL.build(new Class1()2,"display");
//            items[2] = (IStringDisplay) ST_DEL.build(Class3.class,"staticDisplay");
//
//            // call the delegates
//            for(int i = 0; i < items.length; i++) {
//                items[i].doDisplay("test");
//            }
//        }
//
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
