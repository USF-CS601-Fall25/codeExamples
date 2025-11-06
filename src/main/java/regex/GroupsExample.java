package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupsExample {
	public static void main(String[] args) {
		// example 1
		System.out.println("Example 1 ===============");
		String str = "Hello: This is a Test:1, 2, 3";

		Pattern p1 = Pattern.compile("(.*):(.*)"); // by default the matching is greedy

		Matcher m1 = p1.matcher(str);
		if (m1.find()) {
			System.out.println("Group 1 matched:" + System.lineSeparator() + m1.group(1));
			System.out.println("Group 2 matched:" + System.lineSeparator() + m1.group(2));
		}

		// example 2
		System.out.println();
		System.out.println("Example 2===============");
		Pattern p2 = Pattern.compile("(.*?):(.*?)"); // ? made this quantifier "reluctant"
		Matcher m2 = p2.matcher(str);
		while (m2.find()) {
			if (m2.group(1).isEmpty())
				System.out.println("Group 1 matched nothing");
			else
				System.out.println("Group 1 matched:" + System.lineSeparator() + m2.group(1));
			if (m2.group(2).isEmpty())
				System.out.println("Group 2 matched nothing");
			else
				System.out.println("Group 2 matched:" + System.lineSeparator() + m2.group(2));
		}

	}
}
