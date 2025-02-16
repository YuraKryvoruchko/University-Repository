namespace OOP_Lesson_16
{
    public class Polynomial
    {
        public int Degree { get; private set; }
        public double[] Coefficients { get; private set; }

        public static int Count { get; private set; }

        public Polynomial(int degree)
        {
            if (degree < 0)
            {
                throw new ArgumentException("The degree cannot be negative!");
            }
            Degree = degree;
            Coefficients = new double[degree + 1];
            Count++;
        }
        public Polynomial(params double[] coefficients)
        {
            if(coefficients.Length == 0)
            {
                throw new ArgumentException("The array must contain at least one coefficient!");
            }

            Degree = coefficients.Length - 1;
            Coefficients = coefficients;
            Count++;
        }
        public Polynomial(Polynomial other)
        {
            Degree = other.Degree;
            Coefficients = (double[])other.Coefficients.Clone();
            Count++;
        }

        public double Evaluate(double x)
        {
            double result = 0;
            for (int i = Degree; i >= 0; i--)
            {
                result = result * x + Coefficients[i];
            }
            return result;
        }

        public static Polynomial operator +(Polynomial p1, Polynomial p2)
        {
            int maxDegree = Math.Max(p1.Degree, p2.Degree);
            double[] resultCoeffs = new double[maxDegree + 1];

            for(int i = 0; i <= maxDegree; i++)
            {
                double coeff1 = i <= p1.Degree ? p1.Coefficients[i] : 0;
                double coeff2 = i <= p2.Degree ? p2.Coefficients[i] : 0;
                resultCoeffs[i] = coeff1 + coeff2;
            }

            return new Polynomial(resultCoeffs);
        }
        public static Polynomial operator -(Polynomial p1, Polynomial p2)
        {
            int maxDegree = Math.Max(p1.Degree, p2.Degree);
            double[] resultCoeffs = new double[maxDegree + 1];

            for (int i = 0; i <= maxDegree; i++)
            {
                double coeff1 = i <= p1.Degree ? p1.Coefficients[i] : 0;
                double coeff2 = i <= p2.Degree ? p2.Coefficients[i] : 0;
                resultCoeffs[i] = coeff1 - coeff2;
            }

            return new Polynomial(resultCoeffs);
        }
        public static Polynomial operator *(Polynomial p1, Polynomial p2)
        {
            int maxDegree = p1.Degree + p2.Degree;
            double[] resultCoeffs = new double[maxDegree + 1];

            for (int i = 0; i <= p1.Degree; i++)
            {
                for(int j = 0; j <= p2.Degree; j++)
                {
                    resultCoeffs[i + j] += p1.Coefficients[i] * p2.Coefficients[j];
                }
            }

            return new Polynomial(resultCoeffs);
        }

        public override string ToString()
        {
            if (Coefficients.Length == 1)
            {
                return Coefficients[0].ToString();
            }

            string newString = $"{Coefficients[Degree]}";
            for (int i = Degree; i > 0; i--)
            {
                if (i > 1)
                {
                    newString += $"x^{i}";
                }
                else if(i == 1)
                {
                    newString += $"x";
                }

                if (Coefficients[i - 1] >= 0)
                {
                    newString += " + ";
                }
                else
                {
                    newString += " - ";
                }

                newString += $"{Math.Abs(Coefficients[i - 1])}";
            }

            return newString;
        }
    }
}
