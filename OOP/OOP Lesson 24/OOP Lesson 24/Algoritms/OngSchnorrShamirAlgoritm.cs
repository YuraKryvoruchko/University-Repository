using System;
using System.Text;

namespace OOP_Lesson_24.Algoritms
{
    internal class OngSchnorrShamirAlgoritm
    {
        public ulong N { get; private set; }

        public OngSchnorrShamirAlgoritm()
        {
            Random random = new Random();
            N = (ulong)(random.Next(int.MaxValue) * random.Next(int.MaxValue));
        }

        public ulong Encrypt(string text, ulong n)
        {
            byte[] bytes = Encoding.ASCII.GetBytes(text);
            ulong result = 0;
            foreach (byte b in bytes)
            {
                result = (result * 31 + b) % n;
            }
            return result;
        }
    }
}
