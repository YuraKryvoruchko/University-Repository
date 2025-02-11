using System;
using System.Windows.Forms;

namespace OOP_Lesson_15
{
    public class LessonTask3 : ILessonTask
    {
        private Button _computeButton;
        private TextBox _aSideTextBox;
        private TextBox _bSideTextBox;
        private TextBox _cSideTextBox;
        private TextBox _dSideTextBox;
        private TextBox _resultTextBox;

        public LessonTask3(TextBox aSideTextBo, TextBox bSideTextBo, TextBox cSideTextBox, TextBox dSideTextBox, TextBox resultTextBox, Button computeButton)
        {
            _computeButton = computeButton;
            _aSideTextBox = aSideTextBo;
            _bSideTextBox = bSideTextBo;
            _cSideTextBox = cSideTextBox;
            _dSideTextBox = dSideTextBox;
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
                _resultTextBox.Text = "Введіть число у змінну a";
                return;
            }
            if (!Double.TryParse(_bSideTextBox.Text, out double b) || b < 0)
            {
                _resultTextBox.Text = "Введіть число у змінну b";
                return;
            }
            if (!Double.TryParse(_cSideTextBox.Text, out double c) || c < 0)
            {
                _resultTextBox.Text = "Введіть число у змінну c";
                return;
            }
            if (!Double.TryParse(_dSideTextBox.Text, out double d) || d < 0)
            {
                _resultTextBox.Text = "Введіть число у змінну d";
                return;
            }

            _resultTextBox.Text = ((a / c) == (b / d)).ToString();
        }
    }
}
