using System;
using System.Windows.Forms;

namespace OOP_Lesson_15
{
    public class LessonTask2 : ILessonTask
    {
        private Button _computeButton;
        private TextBox _aSideTextBox;
        private TextBox _bSideTextBox;
        private TextBox _degreesTextBox;
        private TextBox _resultTextBox;

        public LessonTask2(TextBox aSideTextBo, TextBox bSideTextBo, TextBox degreesTextBox, TextBox resultTextBox, Button computeButton)
        {
            _computeButton = computeButton;
            _aSideTextBox = aSideTextBo;
            _bSideTextBox = bSideTextBo;
            _degreesTextBox = degreesTextBox;
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
            if (!Double.TryParse(_aSideTextBox.Text, out double a) || a < 0)
            {
                _resultTextBox.Text = "Введіть число у змінну a(a >= 0)";
                return;
            }
            if (!Double.TryParse(_bSideTextBox.Text, out double b) || b < 0)
            {
                _resultTextBox.Text = "Введіть число у змінну b(b >= 0)";
                return;
            }
            if (!Double.TryParse(_degreesTextBox.Text, out double degrees) || degrees < 0)
            {
                _resultTextBox.Text = "Введіть число у змінну degrees(degrees >= 0)";
                return;
            }

            double angle = Math.PI * degrees / 180.0;
            _resultTextBox.Text = (0.5f * a * b * Math.Sin(angle)).ToString();
        }
    }
}
