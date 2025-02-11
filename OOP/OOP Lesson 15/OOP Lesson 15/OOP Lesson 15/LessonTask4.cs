using System;
using System.Windows.Forms;

namespace OOP_Lesson_15
{
    public class LessonTask4 : ILessonTask
    {
        private Button _computeButton;
        private TextBox _aNumberTextBox;
        private TextBox _bNumberTextBox;
        private TextBox _cNumberTextBox;
        private TextBox _resultTextBox;

        public LessonTask4(TextBox aNumberTextBox, TextBox bNumberTextBox, TextBox cNumberTextBox, TextBox resultTextBox, Button computeButton)
        {
            _computeButton = computeButton;
            _aNumberTextBox = aNumberTextBox;
            _bNumberTextBox = bNumberTextBox;
            _cNumberTextBox = cNumberTextBox;
            _resultTextBox = resultTextBox;
        }

        public void Init()
        {
            _computeButton.Click += Compute;
        }
        public void Deinit()
        {
            _computeButton.Click -= Compute;
        }

        private void Compute(object sender, EventArgs e)
        {
            if (!Double.TryParse(_aNumberTextBox.Text, out double a))
            {
                _resultTextBox.Text = "Введіть число у змінну a";
                return;
            }
            if (!Double.TryParse(_bNumberTextBox.Text, out double b))
            {
                _resultTextBox.Text = "Введіть число у змінну b";
                return;
            }
            if (!Double.TryParse(_cNumberTextBox.Text, out double c))
            {
                _resultTextBox.Text = "Введіть число у змінну c";
                return;
            }

            _resultTextBox.Text = ((a + b) > 0 || (a + c) > 0 || (b + c) > 0).ToString();
        }
    }
}
