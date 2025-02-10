using System;
using System.Windows.Forms;

namespace OOP_Lesson_15
{
    public class LessonTask1 : ILessonTask
    {
        private Button _computeButton;
        private TextBox _valueTextBox;
        private TextBox _resultTextBox;

        public LessonTask1(TextBox valueTextBox, TextBox resultTextBox, Button computeButton)
        {
            _computeButton = computeButton;
            _valueTextBox = valueTextBox;
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
            if (!Double.TryParse(_valueTextBox.Text, out double x)) 
            {
                _resultTextBox.Text = "Введіть число у змінну x";
                return;
            }

            _resultTextBox.Text = (Math.Log(Math.Abs(Math.Cos(x))) / Math.Log(1 + x * x)).ToString();
        }
    }
}
