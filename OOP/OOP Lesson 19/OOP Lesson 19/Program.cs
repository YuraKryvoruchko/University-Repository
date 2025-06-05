using System.Text.RegularExpressions;

namespace OOP_Lesson_19
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string[] lines =
            {
                "aaaa",
                "bcbb",
                "aaaa",
                "aabc",
                "bbbb",
                "cccc",
                "aacc",
                "bcbb",
                "cccc"
            };

            Array.Sort(lines);
            Console.WriteLine("Sorted lines:");
            foreach (string line in lines)
                Console.WriteLine(line);

            int sameCount = 0;
            int lineComparing = 1;
            for (int i = 1; i < lines.Length; i++)
            {
                if (String.Compare(lines[i - 1], lines[i]) == 0)
                {
                    lineComparing++;
                }
                else
                {
                    if(lineComparing > 1)
                    {
                        sameCount += lineComparing;
                    }
                    lineComparing = 1;
                }
            }
            if (lineComparing > 1)
            {
                sameCount += lineComparing;
            }

            Console.WriteLine("\nNumber of same lines: " + sameCount);

            Console.Write("\nInput n: ");
            int n = int.Parse(Console.ReadLine());

            int regexMatchCount = 0;

            foreach (string line in lines)
            {
                if (line.Length < lineComparing)
                    continue;

                string pattern = $"^(.)\\1{{{lineComparing - 1}}}";
                if (Regex.IsMatch(line, pattern))
                {
                    regexMatchCount++;
                }
            }

            Console.WriteLine($"The number of lines starting with {lineComparing} identical characters: {regexMatchCount}");
        }
    }
}
