package paystation.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int timeBought;
    private  Map<Integer,Integer> map= new HashMap<>();
    
    
            @Override
            
    public Map<Integer, Integer> getMap(){
        return this.map;
    }

        public void PayStationImpl(){
        
        }   
        
        @Override

    public void addPayment(int coinValue)
            throws IllegalCoinException {
                Integer count=this.map.get(coinValue);
                if (count==null){
                 this.map.put(coinValue, 1);
                }else{
                    this.map.put(coinValue, 1+count);

                }
                switch (coinValue) {
                    case 5: break;
                    case 10: break;
                    case 25: break;
                    default:
                        throw new IllegalCoinException("Invalid coin: " + coinValue);
                }
        this.insertedSoFar += coinValue;
        this.timeBought = this.insertedSoFar / 5 * 2;
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        reset();
        this.map.clear();
        this.map.put(5, 0);
        this.map.put(10, 0);
        this.map.put(15, 0);
        this.map.put(25, 0);
        return r;
    }

    @Override
    public Map<Integer,Integer> cancel() {
        if (this.map==null)
                {
                 this.map.put(5, 0);
                this.map.put(10, 0);
                 this.map.put(15, 0);
                 this.map.put(25, 0);
                }
        reset();
        Map<Integer,Integer> temp=this.map;
        this.map.clear();
        return temp;
    }
    
    public int empty(){
     int len= this.map.keySet().size();
     int sum=0;
     Object[] arr=this.map.keySet().toArray();
        for(int i=0;i<len;i++){
        int vals=this.map.get((int)arr[i]);
        sum=sum+(int)arr[i]*vals;
        }
    return 0;
    }
    private void reset() {
        timeBought = insertedSoFar = 0;
    }
}
