using System;

namespace OOP_Lesson_17
{
    public class Complex : Pair
    {
        public double Real { get; private set; }
        public double Imaginary { get; private set; }

        public Complex(double real, double imaginary)
        {
            Real = real;
            Imaginary = imaginary;
        }
        public override Pair Add(Pair other)
        {
            if (other is Complex c)
            {
                return new Complex(Real + c.Real, Imaginary + c.Imaginary);
            }

            throw new InvalidOperationException("Invalid type for addition.");
        }
        public override Pair Subtract(Pair other)
        {
            if (other is Complex c)
            {
                return new Complex(Real - c.Real, Imaginary - c.Imaginary);
            }

            throw new InvalidOperationException("Invalid type for subtraction.");
        }
        public override Pair Multiply(Pair other)
        {
            if (other is Complex c)
            {
                return new Complex(Real * c.Real - Imaginary * c.Imaginary, Real * c.Imaginary + Imaginary * c.Real);
            }

            throw new InvalidOperationException("Invalid type for multiplication.");
        }
        public override Pair Divide(Pair other)
        {
            if (other is Complex c)
            {
                double denominator = c.Real * c.Real + c.Imaginary * c.Imaginary;
                if (denominator == 0)
                    throw new DivideByZeroException("Division by zero is not allowed.");

                double newReal = (Real * c.Real + Imaginary * c.Imaginary) / denominator;
                double newImaginary = (Imaginary * c.Real - Real * c.Imaginary) / denominator;
                return new Complex(newReal, newImaginary);
            }

            throw new InvalidOperationException("Invalid type for division.");
        }
        public override string ToString()
        {
            return $"{Real} + {Imaginary}i";
        }
    }
}
