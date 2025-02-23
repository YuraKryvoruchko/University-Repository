using System;

namespace OOP_Lesson_17
{
    public class Rational : Pair
    {
        public int Numerator { get; private set; }
        public int Denominator { get; private set; }

        public Rational(int numerator, int denominator) 
        {
            if (denominator == 0)
            {
                throw new DivideByZeroException("Denominator cannot be zero.");
            }

            Numerator = numerator;
            Denominator = denominator;
        }

        public override Pair Add(Pair other)
        {
            if (other is Rational r)
            {
                int num = Numerator * r.Denominator + r.Numerator * Denominator;
                int den = Denominator * r.Denominator;
                return new Rational(num, den);
            }
            throw new InvalidOperationException("Invalid type for addition.");
        }
        public override Pair Subtract(Pair other)
        {
            if (other is Rational r)
            {
                int num = Numerator * r.Denominator - r.Numerator * Denominator;
                int den = Denominator * r.Denominator;
                return new Rational(num, den);
            }
            throw new InvalidOperationException("Invalid type for subtraction.");
        }
        public override Pair Multiply(Pair other)
        {
            if (other is Rational r)
                return new Rational(Numerator * r.Numerator, Denominator * r.Denominator);
            throw new InvalidOperationException("Invalid type for multiplication.");
        }
        public override Pair Divide(Pair other) 
        {
            if (other is Rational r)
                return new Rational(Numerator * r.Denominator, Denominator * r.Numerator);
            throw new InvalidOperationException("Invalid type for division.");
        }
        public override string ToString()
        {
            return $"{Numerator}/{Denominator}";
        }
    }
}
