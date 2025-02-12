namespace OOP_Lesson_16
{
    public class Polynomial
    {
        public int Degree { get; private set; }
        public double[] Coefficients { get; private set; }

        public Polynomial(int degree)
        {
            if (degree < 0)
            {
                throw new ArgumentException("The degree cannot be negative!");
            }
            Degree = degree;
            Coefficients = new double[degree + 1];
        }
        public Polynomial(params double[] coefficients)
        {
            Degree = coefficients.Length - 1;
            Coefficients = coefficients;
        }
        public Polynomial(Polynomial other)
        {
            Degree = other.Degree;
            Coefficients = (double[])other.Coefficients.Clone();
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
                    resultCoeffs[i + j] += p1.Coefficients[i] * p2.Coefficients[i];
                }
            }

            return new Polynomial(resultCoeffs);
        }

        public override string ToString()
        {
            string newString = $"{Coefficients[Degree]}x^{Degree}";
            for (int i = Degree - 1; i > 0; i--)
            {
                if (Coefficients[i] >= 0)
                {
                    newString += " + ";
                }
                else
                {
                    newString += " - ";
                }
                newString += $"{Math.Abs(Coefficients[i])}x^{i}";
            }
            if (Coefficients[0] >= 0)
            {
                newString += " + ";
            }
            else
            {
                newString += " - ";
            }
            newString += $"{Math.Abs(Coefficients[0])}";

            return newString;
        }
    }
}
