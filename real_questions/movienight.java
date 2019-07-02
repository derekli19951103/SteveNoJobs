import java.util.*;
import java.text.SimpleDateFormat;

public class MovieNight {
    public static Boolean canViewAll(Collection<Movie> movies) {
        List<Movie> movies_list = new ArrayList<Movie>(movies);
        Comparator<Movie> movie = (Movie m1, Movie m2)->m1.getStart().compareTo(m2.getStart());
        Collections.sort(movies_list, movie);
        // System.out.println(movies_list.get(0).getStart());
        // System.out.println(movies_list.get(1).getStart());
        // System.out.println(movies_list.get(2).getStart());
        boolean result = false;
        for(int i = 0;i<movies_list.size()-1;i++){
            // System.out.println(movies_list.get(i).getEnd());
            // System.out.println(movies_list.get(i+1).getStart());
            // System.out.println(movies_list.get(i).getEnd().compareTo(movies_list.get(i+1).getStart()));
            int compare = movies_list.get(i).getEnd().compareTo(movies_list.get(i+1).getStart());
            if(compare == 0||compare == -1){
                result = true;
            }else{
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");

        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf.parse("2015-01-01 21:30")));
        movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf.parse("2015-01-01 23:30")));
        movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf.parse("2015-01-01 23:00")));

        System.out.println(MovieNight.canViewAll(movies));
    }
}

class Movie {
    private Date start, end;
    
    public Movie(Date start, Date end) {
        this.start = start;
        this.end = end;
    }
    
    public Date getStart() {
        return this.start;
    }
    
    public Date getEnd() {
        return this.end;
    } 
}