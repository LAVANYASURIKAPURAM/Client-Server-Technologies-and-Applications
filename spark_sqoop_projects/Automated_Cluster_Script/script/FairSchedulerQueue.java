/**
 * @author Lavanya Surikapuram (A20392981)
 * @date 2/25/2018
 * @CourseNO ITMD 521
 * @FileName FairSchedulerQueue.java
 * 
 * @Purpose To Test the Fair Scheduler Queue while performing the Hadoop cluster Performance(Analysis)
 *
 */
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FairSchedulerQueue extends Thread{
	// Constants declared for thread sleep time
	private static final int MIN_VALUE = 5000;
    private static final int MAX_VALUE = 10000;

	public static void main(String[] args) throws InterruptedException {
		
		//Random class called to pick the random sleep time
		Random random = new Random();
        int myRandomNumber = random.nextInt(MAX_VALUE - MIN_VALUE) + MIN_VALUE;
		
        // Loop where various threads are initiated
		for (int x=0; x<32; x++)
	    {
		  FairSchedulerQueue temp= new FairSchedulerQueue();
		  temp.start();
		  temp.sleep(myRandomNumber);
	      System.out.println("Started Thread:" + x);
	    }
	}
	
	// Function which executes the Hadoop MR Job in Linux command line, Job is picked in a random fashion.  
	// Each thread calls this function when it is initiated
	public void run() {
		// This random is for setting the output path for the hadoop job execution
		int random = 1 + (int) (Math.random() * ((100 - 1) + 1));
		
		// This array variable holds all the input years required for the hadoop job execution
		String[] myStringArray;
		myStringArray = new String[]{"/1990/1990.txt","/1990/1990.txt.bz2","/1990/1990.txt.gz",
									"/1991/1991.txt", "/1991/1991.txt.bz2", "/1991/1991.txt.gz", 
									"/1992/1992.txt", "/1992/1992.txt.bz2", "/1992/1992.txt.gz", 
									"/1993/1993.txt", "/1993/1993.txt.bz2", "/1993/1993.txt.gz",
									"/1994/1994.txt", "/1994/1994.txt.bz2", "/1994/1994.txt.gz",
						            "/1995/1995.txt", "/1995/1995.txt.bz2", "/1995/1995.txt.gz",
						            "/1996/1996.txt", "/1996/1996.txt.bz2", "/1996/1996.txt.gz",
						            "/1998/1998.txt", "/1998/1998.txt.bz2", "/1998/1998.txt.gz",
						            "/1999/1999.txt", "/1999/1999.txt.bz2", "/1999/1999.txt.gz",
						            "/1980/1980.txt", "/1980/1980.txt.bz2", "/1980/1980.txt.gz",
						            "/1981/1981.txt", "/1981/1981.txt.bz2", "/1981/1981.txt.gz",
						            "/1982/1982.txt", "/1982/1982.txt.bz2", "/1982/1982.txt.gz",
						            "/1983/1983.txt", "/1983/1983.txt.bz2", "/1983/1983.txt.gz",
						            "/1984/1984.txt", "/1984/1984.txt.bz2", "/1984/1984.txt.gz",
						            "/1985/1985.txt", "/1985/1985.txt.bz2", "/1985/1985.txt.gz",
						            "/1986/1986.txt", "/1986/1986.txt.bz2", "/1986/1986.txt.gz",
						            "/1987/1987.txt", "/1987/1987.txt.bz2", "/1987/1987.txt.gz",
						            "/1988/1988.txt", "/1988/1988.txt.bz2", "/1988/1988.txt.gz",
						            "/1989/1989.txt", "/1989/1989.txt.bz2", "/1989/1989.txt.gz",
						            "/1970/1970.txt", "/1970/1970.txt.bz2", "/1970/1970.txt.gz",
						            "/1971/1971.txt", "/1971/1971.txt.bz2", "/1971/1971.txt.gz",
						            "/1972/1972.txt", "/1972/1972.txt.bz2", "/1972/1972.txt.gz",
						            "/1975/1975.txt", "/1975/1975.txt.bz2", "/1975/1975.txt.gz",
						            "/1976/1976.txt", "/1976/1976.txt.bz2", "/1976/1976.txt.gz",
						            "/1977/1977.txt", "/1977/1977.txt.bz2", "/1977/1977.txt.gz",
						            "/1978/1978.txt", "/1978/1978.txt.bz2", "/1978/1978.txt.gz",
						            "/1979/1979.txt", "/1979/1979.txt.bz2", "/1979/1979.txt.gz",
						            "/1960/1960.txt", "/1960/1960.txt.bz2", "/1960/1960.txt.gz",
						            "/1961/1961.txt", "/1961/1961.txt.bz2", "/1961/1961.txt.gz",
						            "/1962/1962.txt", "/1962/1962.txt.bz2", "/1962/1962.txt.gz",
						            "/1963/1963.txt", "/1963/1963.txt.bz2", "/1963/1963.txt.gz",
						            "/1964/1964.txt", "/1964/1964.txt.bz2", "/1964/1964.txt.gz",
						            "/1965/1965.txt", "/1965/1965.txt.bz2", "/1965/1965.txt.gz",
						            "/1966/1966.txt", "/1966/1966.txt.bz2", "/1966/1966.txt.gz",
						            "/1967/1967.txt", "/1967/1967.txt.bz2", "/1967/1967.txt.gz",
						            "/1968/1968.txt", "/1968/1968.txt.bz2", "/1968/1968.txt.gz",
						            "/1969/1969.txt", "/1969/1969.txt.bz2", "/1969/1969.txt.gz",
						            "/1950/1950.txt", "/1950/1950.txt.bz2", "/1950/1950.txt.gz",
						            "/1951/1951.txt", "/1951/1951.txt.bz2", "/1951/1951.txt.gz",
						            "/1952/1952.txt", "/1952/1952.txt.bz2", "/1952/1952.txt.gz",
						            "/1953/1953.txt", "/1953/1953.txt.bz2", "/1953/1953.txt.gz",
						            "/1954/1954.txt", "/1954/1954.txt.bz2", "/1954/1954.txt.gz",
						            "/1955/1955.txt", "/1955/1955.txt.bz2", "/1955/1955.txt.gz",
						            "/1956/1956.txt", "/1956/1956.txt.bz2", "/1956/1956.txt.gz",
						            "/1957/1957.txt", "/1957/1957.txt.bz2", "/1957/1957.txt.gz",
						            "/1958/1958.txt", "/1958/1958.txt.bz2", "/1958/1958.txt.gz",
						            "/1959/1959.txt", "/1959/1959.txt.bz2", "/1959/1959.txt.gz"
						           };
		
		// This variable selects random year form the input year array for each hadoop job execution
		int rnd = new Random().nextInt(myStringArray.length);
		
		// Jar file command to execute hadoop job in linux command line
		String command = "hadoop jar mv02.jar MaxTemperature /user/controller/ncdc"+myStringArray[rnd]+" /output/surikapuram/udg01"+random;
		System.out.println(command);
		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}