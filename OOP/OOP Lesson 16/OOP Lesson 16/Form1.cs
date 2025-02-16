namespace OOP_Lesson_16
{
    public partial class Form1 : Form
    {
        private Polynomial? _firstPolynomial;
        private Polynomial? _secondPolynomial;
        private Polynomial? _resultOfOperation;

        public Form1()
        {
            InitializeComponent();
            this.textBoxFirstPolynomial.TextChanged += (sender, args) => _firstPolynomial = null;
            this.textBoxSecondPolynomial.TextChanged += (sender, args) => _secondPolynomial = null;
        }

        private void buttonAdd_Click(object sender, EventArgs e)
        {
            if (!TrySetUpPolynomial(ref _firstPolynomial, "Check out the first polynomial!", this.textBoxFirstPolynomial, this.textBoxResultPolynomial))
            {
                return;
            }
            if (!TrySetUpPolynomial(ref _secondPolynomial, "Check out the second polynomial!", this.textBoxSecondPolynomial, this.textBoxResultPolynomial))
            {
                return;
            }
            if (_firstPolynomial == null || _secondPolynomial == null)
            {
                return;
            }

            _resultOfOperation = _firstPolynomial + _secondPolynomial;
            this.textBoxResultPolynomial.Text = _resultOfOperation.ToString();
        }
        private void buttonSubtract_Click(object sender, EventArgs e)
        {
            if (!TrySetUpPolynomial(ref _firstPolynomial, "Check out the first polynomial!", this.textBoxFirstPolynomial, this.textBoxResultPolynomial))
            {
                return;
            }
            if (!TrySetUpPolynomial(ref _secondPolynomial, "Check out the second polynomial!", this.textBoxSecondPolynomial, this.textBoxResultPolynomial))
            {
                return;
            }
            if (_firstPolynomial == null || _secondPolynomial == null)
            {
                return;
            }

            _resultOfOperation = _firstPolynomial - _secondPolynomial;
            this.textBoxResultPolynomial.Text = _resultOfOperation.ToString();
        }
        private void buttonMultiply_Click(object sender, EventArgs e)
        {
            if (!TrySetUpPolynomial(ref _firstPolynomial, "Check out the first polynomial!", this.textBoxFirstPolynomial, this.textBoxResultPolynomial))
            {
                return;
            }
            if (!TrySetUpPolynomial(ref _secondPolynomial, "Check out the second polynomial!", this.textBoxSecondPolynomial, this.textBoxResultPolynomial))
            {
                return;
            }
            if (_firstPolynomial == null || _secondPolynomial == null)
            {
                return;
            }

            _resultOfOperation = _firstPolynomial * _secondPolynomial;
            this.textBoxResultPolynomial.Text = _resultOfOperation.ToString();
        }

        private void buttonFirstToString_Click(object sender, EventArgs e)
        {
            if (!TrySetUpPolynomial(ref _firstPolynomial, "Check out the first polynomial!", this.textBoxFirstPolynomial, this.textBoxFirstString) || _firstPolynomial == null)
            {
                return;
            }
            this.textBoxFirstString.Text = _firstPolynomial.ToString();
        }
        private void buttonSecondToString_Click(object sender, EventArgs e)
        {
            if (!TrySetUpPolynomial(ref _secondPolynomial, "Check out the second polynomial!", this.textBoxSecondPolynomial, this.textBoxSecondString) || _secondPolynomial == null)
            {
                return;
            }
            this.textBoxSecondString.Text = _secondPolynomial.ToString();
        }

        private void buttonEvaluateFirst_Click(object sender, EventArgs e)
        {
            if (!TrySetUpPolynomial(ref _firstPolynomial, "Check out the first polynomial!", this.textBoxFirstPolynomial, this.textBoxResultOfEvaluating) || _firstPolynomial == null)
            {
                return;
            }

            if (double.TryParse(this.textBoxXValue.Text, out double x))
            {
                this.textBoxXValue.BackColor = Color.White;
                this.textBoxResultOfEvaluating.Text = _firstPolynomial.Evaluate(x).ToString();
            }
            else
            {
                this.textBoxXValue.BackColor = Color.Red;
                this.textBoxResultOfEvaluating.Text = "Check out the your X!";
            }
        }
        private void buttonEvaluateSecond_Click(object sender, EventArgs e)
        {
            if (!TrySetUpPolynomial(ref _secondPolynomial, "Check out the second polynomial!", this.textBoxSecondPolynomial, this.textBoxResultOfEvaluating) || _secondPolynomial == null)
            {
                return;
            }

            if (double.TryParse(this.textBoxXValue.Text, out double x))
            {
                this.textBoxXValue.BackColor = Color.White;
                this.textBoxResultOfEvaluating.Text = _secondPolynomial.Evaluate(x).ToString();
            }
            else
            {
                this.textBoxXValue.BackColor = Color.Red;
                this.textBoxResultOfEvaluating.Text = "Check out the your X!";
            }
        }
        private void buttonEvaluateFromResult_Click(object sender, EventArgs e)
        {
            if (_resultOfOperation == null)
            {
                return;
            }

            if (double.TryParse(this.textBoxXValue.Text, out double x))
            {
                this.textBoxXValue.BackColor = Color.White;
                this.textBoxResultOfEvaluating.Text = _resultOfOperation.Evaluate(x).ToString();
            }
            else
            {
                this.textBoxXValue.BackColor = Color.Red;
                this.textBoxResultOfEvaluating.Text = "Check out the your X!";
            }
        }

        private bool TrySetUpPolynomial(ref Polynomial? polynomial, string errorMessage, TextBox infoTextBox, TextBox resultTextBox)
        {
            bool isCorrect = true;
            polynomial ??= GetPolynomialFromText(infoTextBox.Text, out isCorrect);
            if (isCorrect)
            {
                infoTextBox.BackColor = Color.White;
            }
            else
            {
                infoTextBox.BackColor = Color.Red;
                resultTextBox.Text = errorMessage;
            }
            return isCorrect;
        }
        private Polynomial? GetPolynomialFromText(string text, out bool isCorrectText)
        {
            List<double> coefficients = ParseToDoubleList(text, out isCorrectText);
            coefficients.Reverse();
            if (!isCorrectText)
            {
                return null;
            }

            return new Polynomial(coefficients.ToArray());
        }
        private static List<double> ParseToDoubleList(string text, out bool isCorrectText)
        {
            List<double> result = new List<double>();
            foreach (string part in text.Split(';'))
            {
                if (double.TryParse(part, out double parseResult))
                {
                    result.Add(parseResult);
                }
                else
                {
                    isCorrectText = false;
                    return result;
                }
            }
            isCorrectText = true;
            return result;
        }

    }
}
