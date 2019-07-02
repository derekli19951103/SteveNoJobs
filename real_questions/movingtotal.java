import java.util.*;
public class MovingTotal {
    /**
     * Adds/appends list of integers at the end of internal list.
     */
    private Map<Integer, Integer> totalMap = new HashMap<Integer, Integer>();
    private List<Integer> theList = new ArrayList<Integer>();
    
    public void append(int[] list) {
        int theListLength = this.theList.size();
        if (list.length == 0) {
        } else if (list.length == 1) {
            if(theListLength>=2){
                totalMap.put(this.theList.get(theListLength-1)+this.theList.get(theListLength-2)+list[0],1);
            }
            this.theList.add(list[0]);
        } else if (list.length == 2) {
            if(theListLength==1){
                totalMap.put(this.theList.get(theListLength-1)+list[0]+list[1],1);
            }
            if(theListLength>=2){
                totalMap.put(this.theList.get(theListLength-1)+this.theList.get(theListLength-2)+list[0],1);
                totalMap.put(list[0]+this.theList.get(theListLength-1)+list[1],1);
            }
            this.theList.add(list[0]);
            this.theList.add(list[1]);
        } else if (list.length >=3) {
            int start = 0;
            if(theListLength==0){
                totalMap.put(list[0]+list[1]+list[2],1);
                start = 1;
            }
            if(theListLength>=2){
                totalMap.put(this.theList.get(theListLength-1)+this.theList.get(theListLength-2)+list[0],1);
                totalMap.put(list[0]+this.theList.get(theListLength-1)+list[1],1);
                start = 2;
            }
            for(int i=start;i<list.length-2;i++){
                totalMap.put(list[i]+list[i+1]+list[i+2],1);
            }
            this.theList.add(list[list.length-2]);
            this.theList.add(list[list.length-1]);
        }
    }

    /**
     * Returns boolean representing if any three consecutive integers in the
     * internal list have given total.
     */
    public boolean contains(int total) {
        return totalMap.containsKey(total);
    }

    public static void main(String[] args) {
        MovingTotal movingTotal = new MovingTotal();

        movingTotal.append(new int[] { 1 });
        movingTotal.append(new int[] { 2 });
        movingTotal.append(new int[] { 3 });
        movingTotal.append(new int[] { 4,5 });

        System.out.println(movingTotal.contains(6));
        System.out.println(movingTotal.contains(9));

        movingTotal.append(new int[] { 4 });

        System.out.println(movingTotal.contains(9));
    }
}