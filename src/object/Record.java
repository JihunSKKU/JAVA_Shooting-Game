package object;

/* Class of record object. Implement Comparable to use sort. */
public class Record implements Comparable<Record> {

	private String name;
	private int score;
	private int minute;
	private int second;

	/* A constructor that takes 4 variables */
	public Record(String name, int score, int minute, int second) {
		this.name = name;
		this.score = score;
		this.minute = minute;
		this.second = second;
	} // end constructor method

	/* Getter and setter */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	/* Refer to https://www.daleseo.com/java-comparable-comparator/ */
	@Override
	public int compareTo(Record o) {
		/* Override to compare by comparing score first, then comparing in minute and second order. */
		// Scores are sorted in descending order
		if (this.score != o.getScore())
			return o.getScore() - this.score;
		// Time sorted in ascending order
		else if (this.minute != o.getMinute())
			return this.minute - o.getMinute();
		else
			return this.second - o.getSecond();
	} // end method compareTo
} // end class Record
