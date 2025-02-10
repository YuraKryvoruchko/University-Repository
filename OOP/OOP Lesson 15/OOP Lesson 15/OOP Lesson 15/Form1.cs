using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace OOP_Lesson_15
{
    public partial class Form1 : Form
    {
        private Dictionary<int, ILessonTask> _lessons;

        private ILessonTask _currentTask;

        public Form1()
        {
            InitializeComponent();
            _lessons = new Dictionary<int, ILessonTask>()
            {
                { 0, new LessonTask1(this.textBox1, this.textBox3, this.button1)}
            };
            _currentTask = _lessons[0];
            _currentTask.Init();
        }

        private void tabPage1_Click(object sender, EventArgs e)
        {
            int tabIndex = tabControl1.SelectedIndex;
            _currentTask.Deinit();
            _currentTask = _lessons[tabIndex];
            _currentTask.Init();
        }
    }
}
