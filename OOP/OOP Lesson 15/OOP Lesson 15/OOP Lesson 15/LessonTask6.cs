using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace OOP_Lesson_15
{
    public class LessonTask6 : ILessonTask
    {
        private Button _computeButton;
        private TextBox _aNumberListTextBox;
        private TextBox _bNumberListTextBox;
        private TextBox _resultTextBox;

        public LessonTask6(TextBox aNumberListTextBox, TextBox bNumberListTextBox, TextBox resultTextBox, Button computeButton)
        {
            _computeButton = computeButton;
            _aNumberListTextBox = aNumberListTextBox;
            _bNumberListTextBox = bNumberListTextBox;
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
            List<int> aList = ParseToIntList(_aNumberListTextBox.Text, out bool isCorrectText1);
            if (!isCorrectText1) 
            {
                _resultTextBox.Text = "Введіть коректнo перший список!";
                return;
            }
            List<int> bList = ParseToIntList(_bNumberListTextBox.Text, out bool isCorrectText2);
            if (!isCorrectText2)
            {
                _resultTextBox.Text = "Введіть коректнo другий список!";
                return;
            }
            if (aList.Count != bList.Count)
            {
                _resultTextBox.Text = "Кількість чисел повинна бути однаковою!";
                return;
            }

            int count = aList.Count;
            for(int i = 0; i < count; i++)
            {
                bList[i] = aList[i] <= 0 ? bList[i] * 10 : 0;
            }

            _resultTextBox.Text = String.Join("; ", bList);
        }
        private List<int> ParseToIntList(string text, out bool isCorrectText)
        {
            List<int> ints = new List<int>();
            int number = 0;
            bool isParsing = false;
            bool isNegative = false;
            foreach (char c in text)
            {
                if (char.IsDigit(c))
                {
                    isParsing = true;
                    number *= 10;
                    number += c - '0';
                }
                else
                {
                    if (isParsing)
                    {
                        if (c == ';')
                        {
                            number = isNegative ? number * -1 : number;
                            ints.Add(number);
                            number = 0;
                            isParsing = false;
                            isNegative = false;
                        }
                        else
                        {
                            isCorrectText = false;
                            return ints;
                        }
                    }
                    else
                    {
                        if (c == '-')
                        {
                            isNegative = true;
                            isParsing = true;
                        }
                        else if (c != ' ')
                        {
                            isCorrectText = false;
                            return ints;
                        }
                    }
                }
            }
            if (isParsing)
            {
                number = isNegative ? number * -1 : number;
                ints.Add(number);
            }
            isCorrectText = true;
            return ints;
        }
    }
}
