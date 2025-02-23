namespace OOP_Lesson_17
{
    public abstract class Pair
    {
        public abstract Pair Add(Pair other);
        public abstract Pair Subtract(Pair other);
        public abstract Pair Multiply(Pair other);
        public abstract Pair Divide(Pair other);
        public abstract override string ToString();
    }
}
