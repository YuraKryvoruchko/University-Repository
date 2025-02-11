using System;
using System.Windows.Forms;

namespace OOP_Lesson_15
{
    public class LessonTask7 : ILessonTask
    {
        private Button _computeButton;
        private TextBox _textBox;
        private TextBox _resultTextBox;

        public LessonTask7(TextBox textBox, TextBox resultTextBox, Button computeButton)
        {
            _computeButton = computeButton;
            _textBox = textBox;
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
            int count = 0;
            foreach(char c in _textBox.Text)
            {
                if((c == 'a') || (c == 'b') || (c == 'c'))
                {
                    count++;
                }
            }
            _resultTextBox.Text = count.ToString();
        }
    }
}
