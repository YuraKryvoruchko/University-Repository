using System;
using System.Windows.Forms;

namespace OOP_Lesson_15
{
    public class LessonTask5 : ILessonTask
    {
        private Button _computeButton;
        private TextBox _resultDayTextBox;
        private TextBox _resultYearTextBox;

        public LessonTask5(TextBox resultDayTextBox, TextBox resultYearTextBox, Button computeButton)
        {
            _computeButton = computeButton;
            _resultDayTextBox = resultDayTextBox;
            _resultYearTextBox = resultYearTextBox;
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
            int currentYear = 99;
            int day = 0;
            while(day > 31 || day <= 0)
            {
                currentYear++;
                int sum = GetSumOfQuaresOfNumberDigits(currentYear);
                day = currentYear - sum;
            }
            _resultDayTextBox.Text = day.ToString();
            _resultYearTextBox.Text = currentYear.ToString();
        }
        private int GetSumOfQuaresOfNumberDigits(int number)
        {
            int sum = 0;
            while (number > 0)
            {
                int digit = number % 10;
                sum += digit * digit;
                number /= 10;
            }
            return sum;
        }
    }
}
