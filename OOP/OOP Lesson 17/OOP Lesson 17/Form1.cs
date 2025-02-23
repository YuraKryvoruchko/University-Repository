using System;
using System.Drawing;
using System.Windows.Forms;

namespace OOP_Lesson_17
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void bAdd_Click(object sender, EventArgs e)
        {
            bool isCorrect;
            Pair pair1 = GetPairOrNull(this.tbFirstPair1, this.tbFirstPair2, out isCorrect);
            Pair pair2 = GetPairOrNull(this.tbSecondPair1, this.tbSecondPair2, out isCorrect);

            if (!isCorrect)
            {
                return;
            }

            this.tbResult.Text = $"Result: {pair1.Add(pair2)}";
        }
        private void bSubtract_Click(object sender, EventArgs e)
        {
            bool isCorrect;
            Pair pair1 = GetPairOrNull(this.tbFirstPair1, this.tbFirstPair2, out isCorrect);
            Pair pair2 = GetPairOrNull(this.tbSecondPair1, this.tbSecondPair2, out isCorrect);

            if (!isCorrect)
            {
                return;
            }

            this.tbResult.Text = $"Result: {pair1.Subtract(pair2)}";
        }
        private void bMultiply_Click(object sender, EventArgs e)
        {
            bool isCorrect;
            Pair pair1 = GetPairOrNull(this.tbFirstPair1, this.tbFirstPair2, out isCorrect);
            Pair pair2 = GetPairOrNull(this.tbSecondPair1, this.tbSecondPair2, out isCorrect);

            if (!isCorrect)
            {
                return;
            }

            this.tbResult.Text = $"Result: {pair1.Multiply(pair2)}";
        }
        private void bDivide_Click(object sender, EventArgs e)
        {
            bool isCorrect;
            Pair pair1 = GetPairOrNull(this.tbFirstPair1, this.tbFirstPair2, out isCorrect);
            Pair pair2 = GetPairOrNull(this.tbSecondPair1, this.tbSecondPair2, out isCorrect);

            if (!isCorrect)
            {
                return;
            }

            this.tbResult.Text = $"Result: {pair1.Divide(pair2)}"; 
        }

        private Pair GetPairOrNull(TextBox tb1, TextBox tb2, out bool isCorrect)
        {
            if (this.rbComplex.Checked)
            {
                isCorrect = true;
                if(double.TryParse(tb1.Text, out double real))
                {
                    tb1.BackColor = Color.White;
                }
                else
                {
                    tb1.BackColor = Color.Red;
                    tbResult.Text = "Invalid value";
                    isCorrect = false;
                }
                if (double.TryParse(tb2.Text, out double imag))
                {
                    tb2.BackColor = Color.White;
                }
                else
                {
                    tb2.BackColor = Color.Red;
                    tbResult.Text = "Invalid value";
                    isCorrect = false;
                }

                if (isCorrect)
                {
                    return new Complex(real, imag);
                }
                else
                {
                    return null;
                }
            }
            else if (rbRational.Checked)
            {
                isCorrect = true;
                if (int.TryParse(tb1.Text, out int num))
                {
                    tb1.BackColor = Color.White;
                }
                else
                {
                    tb1.BackColor = Color.Red;
                    tbResult.Text = "Invalid value";
                    isCorrect = false;
                }
                if (int.TryParse(tb2.Text, out int den))
                {
                    tb2.BackColor = Color.White;
                }
                else
                {
                    tb2.BackColor = Color.Red;
                    tbResult.Text = "Invalid value";
                    isCorrect = false;
                }

                if (isCorrect)
                {
                    return new Rational(num, den); 
                }
                else
                {
                    return null;
                }
            }

            throw new InvalidOperationException();
        }
    }
}
