import java.io.*;
import java.lang.*;
import java.util.*;

public class PhoneToWordsDB
{
	Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

	private PhoneToWordsDB()
	{

	}

	public static PhoneToWordsDB fromDictionary(String dict)
	{
		try
		{
			Scanner s = new Scanner(new File(dict));
			ArrayList<String> words = new ArrayList<String>();

			PhoneToWordsDB db = new PhoneToWordsDB();
			while (s.hasNextLine())
			{
				String word = s.nextLine().toLowerCase();
				char[] letters = word.toCharArray();
				for (int i = word.length() - 1; i >= 0; i--)
				{
					if (letters[i] < 'a' || letters[i] > 'z')
					{
						word = word.replace(Character.toString(letters[i]), "");
					}
				}

				String num = wordToNums(word);

				if (db.map.containsKey(num))
				{
					if (!db.map.get(num).contains(num))
					{
						db.map.get(num).add(word);
					}
				}
				else
				{
					ArrayList<String> wordList = new ArrayList<String>();
					wordList.add(word);

					db.map.put(num, wordList);
				}
			}

			return db;
		}
		catch (Exception e)
		{
			return null;
		}
	}

	private static String wordToNums(String word)
	{
		word = word.replace('a', '2').replace('b', '2').replace('c', '2');
		word = word.replace('d', '3').replace('e', '3').replace('f', '3');
		word = word.replace('g', '4').replace('h', '4').replace('i', '4');
		word = word.replace('j', '5').replace('k', '5').replace('l', '5');
		word = word.replace('m', '6').replace('n', '6').replace('o', '6');
		word = word.replace('p', '7').replace('q', '7').replace('r', '7').replace('s', '7');
		word = word.replace('t', '8').replace('u', '8').replace('v', '8');
		word = word.replace('w', '9').replace('x', '9').replace('y', '9').replace('z', '9');

		return word;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, ArrayList<String>> entry : map.entrySet())
		{
			sb.append(entry.getKey() + ":\n");

			for (String word : entry.getValue())
			{
				sb.append("    " + word + "\n");
			}
		}

		return sb.toString();
	}
}