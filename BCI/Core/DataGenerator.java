package BCI.Core;

import java.awt.Color;
import java.util.Calendar;
import java.util.Observable;

import BCI.Model.AffectiveData;
import BCI.Model.ExpressionData;

public class DataGenerator extends Observable implements Runnable {

  private Data data;
  private boolean stop = false; 

  public void stop() {
    this.stop = true;
  }

  public Object getObject() {
    return data;
  }
  
  public DataGenerator() {
	  this.data=new Data(0,new double[14],new AffectiveData(),new ExpressionData());
  }

  @Override 
  public void run() {
    stop = false;
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    long initialTime = calendar.getTimeInMillis();
    double timeStamp = 0;

    while (!stop) {
      System.out.println("data generator running");
      timeStamp = (System.currentTimeMillis() - initialTime) * .001;
      double[] values=new double[14];
      for(int i=0;i<values.length;i++) {
    	double val=Math.random();
        values[i]=val;
        Gui.channelLabels.get(i).setText(val+"");
        setBackGround(val,i);
       }
      data.setValues(values);
      data.setTimeStamp(timeStamp);
      createAndNotify();
      try {
        Thread.sleep(1000); 
      } catch (InterruptedException ex) {
      }
    }
  }

  private void createAndNotify() {
    System.out.println("notifying ...");
    setChanged();
    notifyObservers();
  }
  
  private void setBackGround(double val,int index) {
	  Gui.channelButtons.get(index).setOpaque(true);
	  if(val<0.2 && val>0) {
		  Gui.channelButtons.get(index).setBackground(Color.pink);
	  } else if(val<0.4 && val>0.2) {
		  Gui.channelButtons.get(index).setBackground(Color.ORANGE);
	  } else if(val<0.6 && val>0.4) {
		  Gui.channelButtons.get(index).setBackground(Color.RED);
	  } else if(val<0.8 && val>0.6) {
		  Gui.channelButtons.get(index).setBackground(Color.MAGENTA);
	  } else  {
		  Gui.channelButtons.get(index).setBackground(Color.black);
	  }
  }

}
