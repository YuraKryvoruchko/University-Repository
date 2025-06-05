namespace OOP_Lesson_18
{
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Task 1:");
            Task1();

            Console.WriteLine("\n\nTask 2:");
            Task2();
        }

        private static void Task1()
        {
            int[] array = { -3, 2, -1, 4, -3, 9, 5 };

            Console.WriteLine("Array:" + string.Join(", ", array));
            int negativeCount = 0;
            for(int i = 0; i < array.Length; i++)
            {
                if (array[i] < 0)
                    negativeCount++;
            }
            Console.WriteLine($"Number of negative numbers: {negativeCount}");

            int minIndex = 0;
            int minAbs = Math.Abs(array[0]);

            for (int i = 1; i < array.Length; i++)
            {
                if (Math.Abs(array[i]) < minAbs)
                {
                    minAbs = Math.Abs(array[i]);
                    minIndex = i;
                }
            }
            int sum = 0;
            for (int i = minIndex + 1; i < array.Length; i++)
            {
                sum += Math.Abs(array[i]);
            }
            Console.WriteLine($"Sum of modulus of numbers after min element: {sum}");

            for (int i = 0; i < array.Length; i++)
            {
                if (array[i] < 0)
                    array[i] *= array[i];
            }
            Console.WriteLine("Array after replacing negative elements with squares: " + string.Join(", ", array));

            int temp;
            for (int i = 0; i < array.Length - 1; i++)
            {
                for (int j = i + 1; j < array.Length; j++)
                {
                    if (array[i] > array[j])
                    {
                        temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            }
            Console.WriteLine("Sorted array: " + string.Join(", ", array));
        }
        private static void Task2()
        {
            int[,] array = new int[,]
            {
                { 4, 2, 5, 9 },
                { 1, 3, 6, 3 },
                { 2, 2, 5, 8 },
                { 6, 2, 4, 1 }
            };
            Console.WriteLine("Array:");
            Print2DimensionalArray(array);

            int maxRowIndex = array.GetLength(0) - 1, maxColumnIndex = array.GetLength(1) - 1;

            int temp = array[0, maxColumnIndex];
            array[0, maxColumnIndex] = array[maxRowIndex, 0];
            array[maxRowIndex, 0] = temp;
            Console.WriteLine("Change the elements in the upper right and lower left corners:");
            Print2DimensionalArray(array);

            temp = array[maxRowIndex, maxColumnIndex];
            array[maxRowIndex, maxColumnIndex] = array[0, 0];
            array[0, 0] = temp;
            Console.WriteLine("Change the elements in the lower right and upper left corners:");
            Print2DimensionalArray(array);
        }

        private static void Print2DimensionalArray(int[,] array)
        {
            for (int i = 0; i < array.GetLength(0); i++)
            {
                for (int j = 0; j < array.GetLength(1); j++)
                {
                    Console.Write(array[i, j] + " ");
                }
                Console.WriteLine();
            }
        }
    }
}
