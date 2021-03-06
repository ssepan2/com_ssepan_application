package com.ssepan.application;

import java.beans.*;
import java.io.*;
import java.util.*; 
import java.util.logging.Level; 
//import javax.swing.JComponent;
import com.ssepan.utility.*;

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

    public static String C_XML_FILE = "mvcmodel_%s.xml";

    protected List<IPropertyChanged> Handlers;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public ModelBase()
    {
        //String sStatusMessage="";
        //String sErrorMessage="";

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
        //String sStatusMessage="";
        //String sErrorMessage="";
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
        //String sStatusMessage="";
        //String sErrorMessage="";

        try {
            KeyOld = Key;
            Key = value;
            //System.out.println(String.format("setKey: before='%s', after='%s'",KeyOld,Key));
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
        //String sStatusMessage="";
        //String sErrorMessage="";
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
    public void setDirty(Boolean value)
    {
        //String sStatusMessage="";
        //String sErrorMessage="";

        try {
            DirtyOld = Dirty;
            Dirty = value;
            //System.out.println(String.format("setDirty: before='%s', after='%s'",DirtyOld.toString(),Dirty.toString()));
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
        protected void notifyPropertyChanged(String propertyName) {
            //String sErrorMessage; 
            //String formatResult;

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
            //String sErrorMessage; 
            //String formatResult;
          
            try {
              //formatResult=String.format("Handlers.size() (before): '%d'",Handlers.size());
              //System.out.println(formatResult);

              //f("Key");
              if (Handlers.indexOf(f) == -1) {
                   Handlers.add(f);
              };

              //formatResult=String.format("Handlers.size() (after): '%d'",Handlers.size());
              //System.out.println(formatResult);
            } catch (Exception ex) {
                //sErrorMessage=ex.getMessage();
                Log.write(ex,Level.ALL);
            } finally {
                 //
            };
        };

        public void removeHandler(IPropertyChanged f) {
            //String sErrorMessage; 
            //String formatResult;
            IPropertyChanged extracted;
            
            try {
                //formatResult=String.format("Handlers.size() (before): '%d'",Handlers.size());
                //System.out.println(formatResult);

                extracted = Handlers.remove(Handlers.indexOf(f));
                if (extracted==null) {
                  //formatResult=String.format("item not extracted from Handlers: '%d'",Handlers.size());
                  //System.out.println(formatResult);
                } else {
                  //formatResult=String.format("Handlers.size() (after): '%d'",Handlers.size());
                  //System.out.println(formatResult);
                };
            } catch (Exception ex) {
                //sErrorMessage=ex.getMessage();
                Log.write(ex,Level.ALL);
            }  finally {
                 //
            };
        };


        protected void onNotifyPropertyChanged(String propertyName) {
          String sErrorMessage;
          //String formatResult;
          
            try {
              //  formatResult=String.format("OnNotifyPropertyChanged: propertyName (before): '%s'",propertyName);
              //  Sytem.out.println(formatResult);

              for (IPropertyChanged i : Handlers)  {
                   i.propertyChanged(propertyName);
              };
            } catch (Exception ex) {
                sErrorMessage=ex.getMessage();
                System.out.println(sErrorMessage);
                Log.write(ex,Level.ALL);
            } finally {
               //formatResult=String.format("OnNotifyPropertyChanged: propertyName (after): '%s'",propertyName);
               //Sytem.out.println(formatResult);
            };
        };

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="XML IO">
    public static Boolean WriteXml
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
        return true;
    }
    
    public static ModelBase ReadXml
    (
        String filepath,
        ModelBase model
    ) 
        throws FileNotFoundException
    {
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
            model = (ModelBase)decoder.readObject();
        }
        
        return model;
    }
    // </editor-fold>

}
