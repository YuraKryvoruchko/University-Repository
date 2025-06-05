namespace OOP_Lesson_20
{
    public struct ComplexNumber
    {
        public double Real;
        public double Imaginary;

        public ComplexNumber(double real, double imaginary)
        {
            Real = real;
            Imaginary = imaginary;
        }

        public override string ToString()
        {
            return $"{Real} {(Imaginary >= 0 ? '+' : '-')} {Math.Abs(Imaginary)}i";
        }
    }

    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Enter A number:");
            ComplexNumber a = ReadComplexNumberFromConsole();
            Console.WriteLine("\nEnter B number:");
            ComplexNumber b = ReadComplexNumberFromConsole();
            Console.WriteLine($"\nA: {a}");
            Console.WriteLine($"B: {b}");

            try
            {
                double denominator = b.Real * b.Real + b.Imaginary * b.Imaginary;
                if (denominator == 0)
                    throw new DivideByZeroException("Cannot divide by zero complex number.");

                double realPart = (a.Real * b.Real + a.Imaginary * b.Imaginary) / denominator;
                double imagPart = (a.Imaginary * b.Real - a.Real * b.Imaginary) / denominator;

                Console.WriteLine($"Result of the division: {new ComplexNumber(realPart, imagPart)}");
            }
            catch (DivideByZeroException exception)
            {
                ConsoleColor color = Console.ForegroundColor;
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine(exception.Message);
                Console.ForegroundColor = color;
            }
        }

        private static ComplexNumber ReadComplexNumberFromConsole()
        {
            double real, imaginary;
            Console.Write("Enter real part: ");
            while (!Double.TryParse(Console.ReadLine(), out real))
            {
                ConsoleColor color = Console.ForegroundColor;
                Console.ForegroundColor = ConsoleColor.Yellow;
                Console.WriteLine("You need to enter a real number! Please try again!");
                Console.ForegroundColor = color;
                Console.Write("Enter real part:");
            }
            Console.Write("Enter imaginary part: ");
            while (!Double.TryParse(Console.ReadLine(), out imaginary))
            {
                ConsoleColor color = Console.ForegroundColor;
                Console.ForegroundColor = ConsoleColor.Yellow;
                Console.WriteLine("You need to enter a double number! Please try again!");
                Console.ForegroundColor = color;
                Console.Write("Enter imaginary part:");
            }
            return new ComplexNumber(real, imaginary);
        }
    }
}
